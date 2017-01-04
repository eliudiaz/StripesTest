/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.catalogos.generales.handlers;

import gt.org.ms.api.jpa.SpecificationBuilder;
import static gt.org.ms.api.requesting.ValidationsHelper.isNull;
import gt.org.ms.controller.dto.CatalogosRequestDto;
import gt.org.ms.model.Catalogos;
import gt.org.ms.model.Catalogos_;
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
public class CatalogosSpec implements SpecificationBuilder<CatalogosRequestDto, Catalogos> {

    @Override
    public Specification<Catalogos> build(final CatalogosRequestDto param) {
        return new Specification<Catalogos>() {
            @Override
            public Predicate toPredicate(Root<Catalogos> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();

                if (!isNull(param.getTipo())) {
                    predicates.add(cb.like(cb.lower(root.get(Catalogos_.tipo)),
                            param.getTipo().toLowerCase()));
                }
                if (!isNull(param.getCodigoPadre())
                        && param.getCodigoPadre().intValue() >= 0) {
                    predicates.add(cb.equal(root.get(Catalogos_.codigoPadre), param.getCodigoPadre()));
                } else if (param.getCodigoPadre().intValue() >= 0) {
                    predicates.add(cb.isNull(root.get(Catalogos_.codigoPadre)));
                }

                if (!isNull(param.getValor())) {
                    predicates.add(cb.like(cb.lower(root.get(Catalogos_.valor)),
                            param.getValor().toLowerCase()));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
