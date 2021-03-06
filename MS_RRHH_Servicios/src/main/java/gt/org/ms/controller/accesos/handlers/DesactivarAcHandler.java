/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.accesos.handlers;

import gt.org.ms.api.jpa.DoDeleteEntityHandler;
import gt.org.ms.model.Acceso;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author edcracken
 */
@Service("accesosDesactivar")
public class DesactivarAcHandler extends DoDeleteEntityHandler<Acceso> {

    @Autowired
    public DesactivarAcHandler(@Qualifier("accesosRepository") JpaRepository<Acceso, Serializable> repo) {
        super(repo);
    }

}
