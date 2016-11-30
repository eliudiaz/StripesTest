/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.usuarios;

import gt.org.isis.controller.dto.UsuarioLoginDto;
import gt.org.isis.controller.usuarios.handlers.LoginUsHandler;
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
 * @author eliud
 */
@Controller
@RequestMapping("usuarios")
public class LoginUsuarioController {

    @Autowired
    LoginUsHandler handler;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @RequestMapping(value = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    public HttpEntity crear(@RequestBody @Valid UsuarioLoginDto usuario) {
        return new ResponseEntity(handler.handle(usuario), HttpStatus.OK);
    }
}
