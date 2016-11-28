/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.usuarios;

import gt.org.isis.controller.usuarios.handlers.DesactivarUsHandler;
import gt.org.isis.model.Usuario;
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
 * @author edcracken
 */
@Controller
@RequestMapping("usuarios")
public class DesactivarUsuarioController {

    @Autowired
    DesactivarUsHandler handler;

    @RequestMapping(value = "/disable/{id}",
            method = RequestMethod.DELETE)
    public HttpEntity modificar(@PathVariable("id") String id) {
        handler.handle(new Usuario(id));
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
