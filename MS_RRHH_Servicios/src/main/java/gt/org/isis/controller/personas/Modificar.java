/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.personas;

import gt.org.isis.controller.dto.ReqModPersonaDto;
import gt.org.isis.controller.personas.handlers.PersonaModificarHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author eliud
 */
@Controller
@RequestMapping("personas")
public class Modificar {

    @Autowired
    PersonaModificarHandler handler;

    @RequestMapping(value = "/mod/{cui}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void modificar(@PathVariable("cui") String cui, @RequestBody ReqModPersonaDto persona) {
        persona.setCui(cui);
        handler.handle(persona);
    }
}
