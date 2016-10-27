/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ms.rrhh.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import org.ms.rrhh.domain.model.Persona;
import org.ms.rrhh.rest.dto.BusquedaNormalDto;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author edcracken
 */
@Repository
public class PersonasDaoImpl implements PersonasDao {

    protected EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public List<Persona> busquedaNormal(BusquedaNormalDto normal) throws DataAccessException {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Persona> c = cb.createQuery(Persona.class);
        Root<Persona> root = c.from(Persona.class);
        EntityType<Persona> type = getEntityManager().getMetamodel().entity(Persona.class);

        List<Predicate> criteria = new ArrayList<Predicate>();
        if (normal.getPrimerNombre() != null) {
            criteria.add(cb.like(root.get(type.getSingularAttribute("primerNombre", String.class)),
                    normal.getPrimerNombre()));
        }
        if (normal.getSegundoNombre() != null) {
            criteria.add(cb.like(root.get(type.getSingularAttribute("segundoNombre", String.class)),
                    normal.getSegundoNombre()));
        }
        if (normal.getPrimerApellido()!= null) {
            criteria.add(cb.like(root.get(type.getSingularAttribute("primerApellido", String.class)),
                    normal.getPrimerNombre()));
        }
        if (normal.getSegundoApellido()!= null) {
            criteria.add(cb.like(root.get(type.getSingularAttribute("segundoApellido", String.class)),
                    normal.getPrimerNombre()));
        }
        if (normal.getSexo() != null) {
            criteria.add(cb.like(root.get(type.getSingularAttribute("sexo", String.class)),
                    normal.getSexo().getValue()));
        }
        if (normal.getFechaNacInicio()!= null) {
            criteria.add(cb.greaterThanOrEqualTo(root.get(type.getSingularAttribute("sexo", String.class)),
                    normal.getPrimerNombre()));
        }
        if (normal.getFechaNacFin()!= null) {
            criteria.add(cb.lessThanOrEqualTo(root.get(type.getSingularAttribute("sexo", String.class)),
                    normal.getPrimerNombre()));
        }
        if (normal.getEdad()!= null) {
            criteria.add(cb.equal(root.get(type.getSingularAttribute("sexo", String.class)),
                    normal.getPrimerNombre()));
        }
        if (normal.getDireccion()!= null) {
            criteria.add(cb.like(root.get(type.getSingularAttribute("sexo", String.class)),
                   likeExpr(normal.getPrimerNombre())));
        }        
        if (normal.getMunicipio()!= null) {
            criteria.add(cb.equal(root.get(type.getSingularAttribute("sexo", String.class)),
                   likeExpr(normal.getPrimerNombre())));
        }
        c.where(cb.or(criteria.toArray(new Predicate[criteria.size()])));

        return getEntityManager().createQuery(c).getResultList();
    }
    
    private String likeExpr(String val){
        return "%".concat(val).concat("%");
    }

    @Transactional
    @Override
    public List<Persona> getAll() throws DataAccessException {
        Query query = getEntityManager().createQuery("select c from Persona c");
        List<Persona> resultList = query.getResultList();
        return resultList;
    }

    @Transactional
    @Override
    public Persona getOne(Long carId) throws DataAccessException {
        return getEntityManager().find(Persona.class, carId);
    }

}
