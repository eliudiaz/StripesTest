/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.personas;

import gt.org.isis.controller.dto.NuevaPersonaDto;
import gt.org.isis.controller.dto.PersonaDto;
import gt.org.isis.controller.personas.handlers.PersonaCrearHandler;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author edcracken
 */
@Controller
@RequestMapping("personas")
public class Crear {

    @Autowired
    PersonaCrearHandler handler;

    @RequestMapping(value = "/crea", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity crear(@RequestBody @Valid NuevaPersonaDto persona) {
        handler.handle(persona);
        return new ResponseEntity(HttpStatus.OK);
    }
}
