/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.api.jpa;

import gt.org.isis.api.AbstractRequestHandler;
import static gt.org.isis.api.ValidationsHelper.isNull;
import gt.org.isis.api.entities.DesactivableEntity;
import gt.org.isis.model.enums.Estado;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author edcracken
 */
public class DoDesactivarHandler<T extends DesactivableEntity>
        extends AbstractRequestHandler<T, Boolean> {

    private JpaRepository<T, Serializable> repo;

    public DoDesactivarHandler(JpaRepository<T, Serializable> repo) {
        this.repo = repo;
    }

    @Override
    public Boolean execute(T e) {
        T r = repo.findOne(e.getId());
        if (!isNull(r)) {
            r.setEstado(Estado.INACTIVO);
            setUpdateInfo(r);
            repo.save(r);
        }
        return true;
    }

}
