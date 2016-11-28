/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.roles;

import gt.org.isis.controller.dto.RoleDto;
import gt.org.isis.controller.roles.handlers.ModificarHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author eliud
 */
@Controller("desactivarRole")
@RequestMapping("roles")
public class DesactivarController {

    @Autowired
    ModificarHandler handler;

    @RequestMapping(value = "/disable/{id}", method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity modificar(@PathVariable("id") Integer id) {

        handler.handle(new RoleDto());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
