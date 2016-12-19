/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.roles.handlers;

import gt.org.ms.api.jpa.DoDesactivarHandler;
import gt.org.ms.model.Role;
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
public class DesactivarHandler extends DoDesactivarHandler<Role> {

    @Autowired
    public DesactivarHandler(@Qualifier("rolesRepository") JpaRepository<Role, Serializable> repo) {
        super(repo);
    }

}
