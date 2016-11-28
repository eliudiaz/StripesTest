/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.usuarios.handlers;

import gt.org.isis.api.jpa.DoDesactivarHandler;
import gt.org.isis.model.Usuario;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author edcracken
 */
@Service
public class DesactivarUsHandler extends DoDesactivarHandler<Usuario> {

    @Autowired
    public DesactivarUsHandler(@Qualifier("usuariosRepository") JpaRepository<Usuario, Serializable> repo) {
        super(repo);
    }

}
