/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.home.handlers.specifications;

import gt.org.ms.controller.dto.FiltroAvanzadoDto;
import gt.org.ms.model.UnidadEjecutora;
import gt.org.ms.model.UnidadEjecutora_;
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
