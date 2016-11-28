/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.personas.handlers;

import gt.org.isis.api.jpa.DoDesactivarHandler;
import gt.org.isis.model.Persona;
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
public class PersonasDesactivarHandler extends DoDesactivarHandler<Persona> {

    @Autowired
    public PersonasDesactivarHandler(@Qualifier("personasRepository") JpaRepository<Persona, Serializable> repo) {
        super(repo);
    }

}
