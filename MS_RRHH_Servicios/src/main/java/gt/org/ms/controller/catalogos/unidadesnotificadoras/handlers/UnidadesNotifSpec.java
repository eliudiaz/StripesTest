/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.catalogos.unidadesnotificadoras.handlers;

import gt.org.ms.api.jpa.SpecificationBuilder;
import gt.org.ms.controller.dto.CatalogosRequestDto;
import gt.org.ms.model.UnidadNotificadora;
import gt.org.ms.model.UnidadNotificadora_;
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
public class UnidadesNotifSpec implements SpecificationBuilder<CatalogosRequestDto, UnidadNotificadora> {

    @Override
    public Specification<UnidadNotificadora> build(final CatalogosRequestDto param) {
        return new Specification<UnidadNotificadora>() {
            @Override
            public Predicate toPredicate(Root<UnidadNotificadora> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                if (param.getCodigoPadre() != null) {
                    predicates.add(cb.equal(root.get(UnidadNotificadora_.codigoPadre), param.getCodigoPadre()));
                } else {
                    predicates.add(cb.isNull(root.get(UnidadNotificadora_.codigoPadre)));
                }
                if (param.getTipo() != null) {
                    predicates.add(cb.equal(root.get(UnidadNotificadora_.tipo), param.getTipo()));
                }
                if (param.getValor() != null) {
                    predicates.add(cb.like(root.get(UnidadNotificadora_.valor),
                            param.getValor()));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
