/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.api.jpa;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author edcracken
 * @param <T>
 */
public class ManySpecificationORHandler<T> implements Specification<T> {

    private final List<Specification<T>> specs;

    public ManySpecificationORHandler(List<Specification<T>> specs) {
        this.specs = specs;
    }

    @Override
    public Predicate toPredicate(final Root<T> root, final CriteriaQuery<?> cq, final CriteriaBuilder cb) {
        return cb.or(Collections2.transform(specs, new Function<Specification, Predicate>() {
            @Override
            public Predicate apply(Specification f) {
                return f.toPredicate(root, cq, cb);
            }
        }).toArray(new Predicate[specs.size()]));
    }

}
