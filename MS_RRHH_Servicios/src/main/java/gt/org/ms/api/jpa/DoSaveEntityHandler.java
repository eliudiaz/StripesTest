/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.api.jpa;

import gt.org.ms.api.requesting.AbstractRequestHandler;
import static gt.org.ms.api.requesting.ValidationsHelper.isNull;
import gt.org.ms.api.global.exceptions.ExceptionsManager;
import gt.org.ms.repository.CustomRepository;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import gt.org.ms.api.entities.PersistentEntity;

/**
 *
 * @author edcracken
 * @param <T>
 */
public class DoSaveEntityHandler<T extends PersistentEntity>
        extends AbstractRequestHandler<T, Boolean> {

    private final JpaRepository<T, Serializable> repo;
    private Specification<T> customSpec;

    public DoSaveEntityHandler(JpaRepository<T, Serializable> repo) {
        this.repo = repo;
    }

    public void setCustomSpec(Specification<T> customSpec) {
        this.customSpec = customSpec;
    }

    @Override
    public Boolean execute(T e) {
        if (!isNull(e.getId())) {
            RuntimeException duplicatedEntityEx
                    = ExceptionsManager
                            .newValidationException("entity_exists", "Ya existe una entidad con ese ID");
            if (!isNull(customSpec) && repo instanceof CustomRepository) {
                List<T> l = ((CustomRepository) repo).findAll(customSpec);
                if (!isNull(l) && !l.isEmpty()) {
                    throw duplicatedEntityEx;
                }
            } else {
                e = repo.findOne(e.getId());
            }
        }
        if (!isNull(e)) {
            repo.save(e);
        } else {
            throw ExceptionsManager.newValidationException("wrong_content", "La esta vacia o nula!");
        }
        return true;
    }

}
