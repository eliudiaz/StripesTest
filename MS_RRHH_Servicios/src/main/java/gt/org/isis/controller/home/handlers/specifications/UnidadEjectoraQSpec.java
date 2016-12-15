/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.home.handlers.specifications;

import gt.org.isis.controller.dto.FiltroAvanzadoDto;
import gt.org.isis.model.Puesto;
import gt.org.isis.model.Puesto_;
import gt.org.isis.model.UnidadEjecutora;
import gt.org.isis.model.UnidadEjecutora_;
import gt.org.isis.model.enums.ComparadorBusqueda;
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
public class UnidadEjectoraQSpec implements Specification<UnidadEjecutora> {

    private final Integer unidadEjecutora;
    private final FiltroAvanzadoDto fa;

    public UnidadEjectoraQSpec(Integer unidadEjecutora, FiltroAvanzadoDto fa) {
        this.unidadEjecutora = unidadEjecutora;
        this.fa = fa;
    }

    @Override
    public Predicate toPredicate(Root<UnidadEjecutora> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        List<Predicate> ls = new ArrayList<Predicate>();
        if (unidadEjecutora != null) {
            if (fa.getComparador().equals(ComparadorBusqueda.IGUAL)) {
                ls.add(cb.equal(root.get(UnidadEjecutora_.id), unidadEjecutora));
            }
            if (fa.getComparador().equals(ComparadorBusqueda.DIFERENTE)) {
                ls.add(cb.notEqual(root.get(UnidadEjecutora_.id), unidadEjecutora));
            }
        }

        return cb.or(ls.toArray(new Predicate[ls.size()]));
    }

}
