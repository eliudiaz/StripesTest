/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.personas;

import gt.org.isis.controller.dto.PersonaDto;
import gt.org.isis.controller.personas.handlers.PersonaBuscarHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author edcracken
 */
@Controller
@RequestMapping("personas")
public class BusquedaSimple {

    @Autowired
    PersonaBuscarHandler handler;

    @RequestMapping(value = "/get", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity getPersona(@RequestParam("cui") String cui) {
        return new ResponseEntity<PersonaDto>(handler.handle(new PersonaDto(cui)), HttpStatus.OK);
    }
}
