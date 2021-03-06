/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.home.handlers.specifications;

import gt.org.ms.model.Puestos;
import gt.org.ms.model.Puestos_;
import gt.org.ms.model.enums.TipoPuestosCatalogo;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author edcracken
 */
public class PuestosPorPadreQSpec implements Specification<Puestos> {

    final private Integer codigoPadre;
    final private TipoPuestosCatalogo tipoPuestosCatalogo;

    public PuestosPorPadreQSpec(Integer filtro, TipoPuestosCatalogo tipoPuestosCatalogo) {
        this.codigoPadre = filtro;
        this.tipoPuestosCatalogo = tipoPuestosCatalogo;
    }

    @Override
    public Predicate toPredicate(Root<Puestos> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        return cb.and(
                cb.equal(root.get(Puestos_.codigoPadre), codigoPadre),
                cb.equal(root.get(Puestos_.tipo), tipoPuestosCatalogo));
    }

}
