/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.personas;

import gt.org.isis.api.requesting.BaseController;
import gt.org.isis.controller.dto.RequestCreatePersonaDto;
import gt.org.isis.controller.personas.handlers.PersonaCrearHandler;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author edcracken
 */
@Controller
@RequestMapping("personas")
public class CrearPersonaController extends BaseController {

    @Autowired
    PersonaCrearHandler handler;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @RequestMapping(value = "/crea", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity crear(@RequestBody @Valid RequestCreatePersonaDto persona, HttpServletRequest request) {
        configureSesion(persona, request);
        handler.handle(persona);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
