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
public class DoUpdateEntityHandler<T extends PersistentEntity>
        extends AbstractRequestHandler<T, Boolean> {

    private final JpaRepository<T, Serializable> repo;
    private Specification<T> customSpec;

    public DoUpdateEntityHandler(JpaRepository<T, Serializable> repo) {
        this.repo = repo;
    }

    public void setCustomSpec(Specification<T> customSpec) {
        this.customSpec = customSpec;
    }

    @Override
    public Boolean execute(T e) {
        T r;
        if (!isNull(customSpec) && repo instanceof CustomRepository) {
            List<T> l = ((CustomRepository) repo).findAll(customSpec);
            if (!isNull(l) && !l.isEmpty()) {
                r = l.get(0);
            } else {
                throw ExceptionsManager.newValidationException("entity_not_found", "No existe registro con ese ID");
            }
        } else {
            r = repo.findOne(e.getId());
        }
        if (!isNull(r)) {
            repo.save(r);
        } else {
            throw ExceptionsManager.newValidationException("entity_not_found", "No existe registro con ese ID");
        }
        return true;
    }

}
