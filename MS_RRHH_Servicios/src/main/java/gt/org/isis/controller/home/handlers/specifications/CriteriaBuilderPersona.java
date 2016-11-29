/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.home.handlers.specifications;

import gt.org.isis.api.misc.exceptions.ExceptionsManager;
import gt.org.isis.controller.dto.BusquedaNormalDto;
import gt.org.isis.model.Persona;
import gt.org.isis.model.Persona_;
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
public class CriteriaBuilderPersona {

    private List<Predicate> criteria;
    private BusquedaNormalDto normal;
    private Root<Persona> rootPersona;
    private CriteriaQuery<?> cq;
    private CriteriaBuilder cb;

    public CriteriaBuilderPersona withCq(CriteriaQuery cq) {
        this.cq = cq;
        return this;
    }

    public CriteriaBuilderPersona withCB(CriteriaBuilder cq) {
        this.cb = cq;
        return this;
    }

    public CriteriaBuilderPersona withRoot(Root<Persona> root) {
        this.rootPersona = root;
        return this;
    }

    public CriteriaBuilderPersona() {
        criteria = new ArrayList<Predicate>();
    }

    public static CriteriaBuilderPersona get() {
        return new CriteriaBuilderPersona();
    }

    public CriteriaBuilderPersona withDto(BusquedaNormalDto filter) {
        this.normal = filter;
        return this;
    }

    private String likeExpr(String val) {
        return "%".concat(val.toLowerCase()).concat("%");
    }

    public Predicate build() {
        if (normal.getCui() != null) {
            criteria.add(cb.equal(rootPersona.get(Persona_.cui),
                    normal.getCui()));
        }
        if (normal.getPrimerNombre() != null) {
            criteria.add(cb.like(cb.lower(rootPersona.get(Persona_.primerNombre)),
                    likeExpr(normal.getPrimerNombre())));
        }
        if (normal.getSegundoNombre() != null) {
            criteria.add(cb.like(cb.lower(rootPersona.get(Persona_.segundoNombre)),
                    likeExpr(normal.getSegundoNombre())));
        }
        if (normal.getPrimerApellido() != null) {
            criteria.add(cb.like(cb.lower(rootPersona.get(Persona_.primerApellido)),
                    likeExpr(normal.getPrimerNombre())));
        }
        if (normal.getSegundoApellido() != null) {
            criteria.add(cb.like(cb.lower(rootPersona.get(Persona_.segundoApellido)),
                    likeExpr(normal.getSegundoApellido())));
        }
        if (normal.getSexo() != null) {
            criteria.add(cb.equal(rootPersona.get(Persona_.sexo),
                    normal.getSexo()));
        }
        if (normal.getPueblo() != null) {
            criteria.add(cb.equal(rootPersona.get(Persona_.fkPueblo),
                    normal.getPueblo()));
        }
        if (normal.getFechaNacInicio() != null && normal.getFechaNacFin() != null) {
            criteria.add(cb.and(cb.greaterThanOrEqualTo(rootPersona.get(Persona_.fechaNacimiento),
                    normal.getFechaNacInicio()),
                    cb.lessThanOrEqualTo(rootPersona.get(Persona_.fechaNacimiento),
                            normal.getFechaNacFin())));
        } else if (normal.getFechaNacInicio() != null || normal.getFechaNacFin() != null) {
            throw ExceptionsManager.newValidationException("parametro_invalido",
                    new String[]{"rango_fechas_nacimiento,El rango de fechas debe tener inicio y fin!"});
        }
        if (normal.getEdad() != null && normal.getEdad() >= 0) {
            criteria.add(cb.equal(rootPersona.get(Persona_.edad),
                    normal.getEdad()));
        }
        return cb.or(criteria.toArray(new Predicate[criteria.size()]));
    }
}
