/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.home.handlers.specifications;

import gt.org.ms.controller.dto.FiltroAvanzadoDto;
import gt.org.ms.model.Puesto;
import gt.org.ms.model.enums.ComparadorBusqueda;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author edcracken
 */
public class PuestoPorFiltroAvanzadoQSpec implements Specification<Puesto> {

    final private FiltroAvanzadoDto f;
    final private SingularAttribute attribute;

    public PuestoPorFiltroAvanzadoQSpec(FiltroAvanzadoDto f, SingularAttribute attribute) {
        this.f = f;
        this.attribute = attribute;
    }

    @Override
    public Predicate toPredicate(Root<Puesto> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        List<Predicate> ls = new ArrayList<Predicate>();
        if (f.getComparador().equals(ComparadorBusqueda.DIFERENTE)) {
            ls.add(cb.notEqual(root.get(attribute),
                    Integer.valueOf(f.getValor1())));
        }
        if (f.getComparador().equals(ComparadorBusqueda.IGUAL)) {
            ls.add(cb.equal(root.get(attribute),
                    Integer.valueOf(f.getValor1())));
        }

        return cb.or(ls.toArray(new Predicate[ls.size()]));
    }

}
