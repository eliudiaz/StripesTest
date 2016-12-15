/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.home.handlers.specifications;

import gt.org.ms.controller.dto.FiltroAvanzadoDto;
import gt.org.ms.model.Puesto;
import gt.org.isis.model.Puesto_;
import gt.org.ms.model.Puestos;
import gt.org.isis.model.Puestos_;
import gt.org.ms.model.enums.ComparadorBusqueda;
import gt.org.ms.model.enums.TipoPuestosCatalogo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author edcracken
 */
public class PuestoPorRenglonQSpec implements Specification<Puesto> {

    private final FiltroAvanzadoDto f;

    public PuestoPorRenglonQSpec(FiltroAvanzadoDto f) {
        this.f = f;
    }

    @Override
    public Predicate toPredicate(Root<Puesto> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        List<Predicate> ls = new ArrayList<Predicate>();

        Subquery<Integer> sqPuestos = cq.subquery(Integer.class);
        Root<Puestos> rPuestos = sqPuestos.from(Puestos.class);
        sqPuestos.select(rPuestos.get(Puestos_.id))
                .where(cb.and(cb.equal(rPuestos.get(Puestos_.codigoPadre),
                        Integer.valueOf(f.getValor1())),
                        cb.equal(rPuestos.get(Puestos_.tipo),
                                TipoPuestosCatalogo.PUESTO_NOMINAL)));

        if (f.getComparador().equals(ComparadorBusqueda.IGUAL)) { //in puestos by renglon
            ls.add(root.get(Puesto_.fkPuestoNominal)
                    .in(sqPuestos));
        } else {
            ls.add(cb.not(root.get(Puesto_.fkPuestoNominal)
                    .in(sqPuestos)));
        }

        return cb.or(ls.toArray(new Predicate[ls.size()]));
    }

}
