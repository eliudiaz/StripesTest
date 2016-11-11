/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.accesos.handlers;

import gt.org.isis.api.jpa.SpecificationBuilder;
import gt.org.isis.controller.dto.CatalogosRequestDto;
import gt.org.isis.model.Acceso;
import gt.org.isis.model.Acceso_;
import gt.org.isis.model.Catalogos;
import gt.org.isis.model.Catalogos_;
import java.util.ArrayList;
import java.util.List;
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
