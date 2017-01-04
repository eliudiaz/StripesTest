/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.api.jpa;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author eliud
 * @param <T>
 * @param <Q>
 */
public class SingleFieldSpecification<T, Q> implements Specification<T> {

    private final SingularAttribute<T, Q> attr;
    private final Q param;

    public SingleFieldSpecification(SingularAttribute<T, Q> attr, Q param) {
        this.attr = attr;
        this.param = param;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        return cb.equal(root.get(attr), param);
    }
}
