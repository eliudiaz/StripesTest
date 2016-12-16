/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.accesos.handlers;

import gt.org.ms.api.jpa.SpecificationBuilder;
import gt.org.ms.model.Acceso;
import gt.org.ms.model.Acceso_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

/**
 *
 * @author edcracken
 */
@Component
public class AccesosSpec implements SpecificationBuilder<Integer, Acceso> {

    @Override
    public Specification<Acceso> build(final Integer param) {
        return new Specification<Acceso>() {
            @Override
            public Predicate toPredicate(Root<Acceso> root, CriteriaQuery<?> cq,
                    CriteriaBuilder cb) {
                return cb.equal(root.get(Acceso_.codigoPadre), param);
            }
        };
    }
}
