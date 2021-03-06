/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.home.handlers.specifications;

import gt.org.ms.controller.dto.FiltroAvanzadoDto;
import gt.org.ms.model.Puesto;
import gt.org.ms.model.Puesto_;
import gt.org.ms.model.enums.ComparadorBusqueda;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author edcracken
 */
public class PuestoQSpec implements Specification<Puesto> {

    private final List<Integer> puestosNominales;
    private final FiltroAvanzadoDto fa;

    public PuestoQSpec(List<Integer> puestosNominales, FiltroAvanzadoDto fa) {
        this.puestosNominales = puestosNominales;
        this.fa = fa;
    }

    @Override
    public Predicate toPredicate(Root<Puesto> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        List<Predicate> ls = new ArrayList<Predicate>();

        if (puestosNominales != null) {
            if (fa.getComparador().equals(ComparadorBusqueda.IGUAL)) {
                ls.add(root.get(Puesto_.fkPuestoNominal).in(puestosNominales));
            }
            if (fa.getComparador().equals(ComparadorBusqueda.DIFERENTE)) {
                ls.add(cb.not(root.get(Puesto_.fkPuestoNominal).in(puestosNominales)));
            }
        }

        return cb.or(ls.toArray(new Predicate[ls.size()]));
    }

}
