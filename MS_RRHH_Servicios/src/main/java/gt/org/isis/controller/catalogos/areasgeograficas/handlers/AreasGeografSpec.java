/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.catalogos.areasgeograficas.handlers;

import gt.org.isis.api.jpa.SpecificationBuilder;
import gt.org.isis.controller.dto.CatalogosRequestDto;
import gt.org.isis.model.AreaGeografica;
import gt.org.isis.model.AreaGeografica_;
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
public class AreasGeografSpec implements SpecificationBuilder<CatalogosRequestDto, AreaGeografica> {

    @Override
    public Specification<AreaGeografica> build(final CatalogosRequestDto param) {
        return new Specification<AreaGeografica>() {
            @Override
            public Predicate toPredicate(Root<AreaGeografica> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                if (param.getCodigoPadre() != null) {
                    predicates.add(cb.equal(root.get(AreaGeografica_.codigoPadre), param.getCodigoPadre()));
                }
                if (param.getTipo() != null) {
                    predicates.add(cb.like(cb.lower(root.get(AreaGeografica_.tipo)),
                            param.getTipo().toLowerCase()));
                }
                if (param.getValor() != null) {
                    predicates.add(cb.like(cb.lower(root.get(AreaGeografica_.valor)),
                            param.getValor().toLowerCase()));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
