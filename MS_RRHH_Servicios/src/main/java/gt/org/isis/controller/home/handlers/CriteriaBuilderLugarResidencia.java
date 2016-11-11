/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.home.handlers;

import gt.org.isis.controller.dto.BusquedaNormalDto;
import gt.org.isis.model.LugarResidencia;
import gt.org.isis.model.LugarResidencia_;
import gt.org.isis.model.enums.EstadoVariable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author edcracken
 */
public class CriteriaBuilderLugarResidencia {

    private List<Predicate> criteria;
    private BusquedaNormalDto normal;
    private Root<LugarResidencia> rootLugar;
    private CriteriaQuery<?> cq;
    private CriteriaBuilder cb;

    public CriteriaBuilderLugarResidencia withCq(CriteriaQuery cq) {
        this.cq = cq;
        return this;
    }

    public CriteriaBuilderLugarResidencia withCB(CriteriaBuilder cq) {
        this.cb = cq;
        return this;
    }

    public CriteriaBuilderLugarResidencia withRoot(Root<LugarResidencia> root) {
        this.rootLugar = root;
        return this;
    }

    public CriteriaBuilderLugarResidencia() {
        criteria = new ArrayList<Predicate>();
    }

    public static CriteriaBuilderLugarResidencia get() {
        return new CriteriaBuilderLugarResidencia();
    }

    public CriteriaBuilderLugarResidencia withDto(BusquedaNormalDto filtro) {
        this.normal = filtro;
        return this;
    }

    private String likeExpr(String val) {
        return "%".concat(val).concat("%");
    }

    public Predicate build() {
        if (normal.getDireccion() != null && !normal.getDireccion().isEmpty()) {
            criteria.add(cb.like(
                    rootLugar.get(LugarResidencia_.direccion),
                    likeExpr(normal.getDireccion())
            ));
        }
        if (normal.getMunicipio() != null) {
            criteria.add(cb.equal(
                    rootLugar.get(LugarResidencia_.fkMunicipio),
                    normal.getMunicipio()));
        }
        cq.where(cb.and(cb.equal(
                rootLugar.get(LugarResidencia_.estado),
                EstadoVariable.ACTUAL), cb.or(criteria.toArray(new Predicate[criteria.size()]))));
        return cb.or(criteria.toArray(new Predicate[criteria.size()]));
    }
}
