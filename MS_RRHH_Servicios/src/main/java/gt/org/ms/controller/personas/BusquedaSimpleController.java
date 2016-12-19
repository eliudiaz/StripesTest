/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.personas;

import gt.org.ms.controller.dto.PersonaDto;
import gt.org.ms.controller.personas.handlers.PersonaBusquedaSimpleHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author edcracken
 */
@Controller
@RequestMapping("personas")
public class BusquedaSimpleController {

    @Autowired
    PersonaBusquedaSimpleHandler handler;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @RequestMapping(value = "/get", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity getPersona(@RequestParam("cui") String cui) {
        return new ResponseEntity<PersonaDto>(handler.handle(new PersonaDto(cui)), HttpStatus.OK);
    }
}
