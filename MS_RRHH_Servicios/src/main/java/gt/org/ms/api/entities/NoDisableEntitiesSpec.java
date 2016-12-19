/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.api.entities;

import gt.org.ms.model.enums.Estado;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author eliud
 */
public class NoDisableEntitiesSpec<T extends DesactivableEntity> implements Specification<T> {

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        return cb.notEqual(root.get("estado"), Estado.INACTIVO);
    }

}
