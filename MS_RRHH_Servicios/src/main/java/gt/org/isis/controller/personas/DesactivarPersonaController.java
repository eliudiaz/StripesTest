/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.personas;

import gt.org.isis.controller.personas.handlers.PersonasDesactivarHandler;
import gt.org.isis.model.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author eliud
 */
@Controller
@RequestMapping("personas")
public class DesactivarPersonaController {

    @Autowired
    PersonasDesactivarHandler handler;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @RequestMapping(value = "/disable/{cui}",
            method = RequestMethod.DELETE)
    public HttpEntity modificar(@PathVariable("cui") String cui) {
        handler.handle(new Persona(cui));
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
