/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.roles;

import gt.org.ms.controller.roles.handlers.DesactivarHandler;
import gt.org.ms.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
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
    DesactivarHandler handler;

    @RequestMapping(value = "/disable/{id}", method = RequestMethod.DELETE)
    public HttpEntity modificar(@PathVariable("id") Integer id) {
        handler.handle(new Role(id));
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
