/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.usuarios;

import gt.org.isis.controller.dto.UsuarioDto;
import gt.org.isis.controller.usuarios.handlers.ModificarUsHandler;
import gt.org.isis.web.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author edcracken
 */
@Controller("usuariosModificar")
@RequestMapping("usuarios")
public class ModificarUsuario {

    @Autowired
    ModificarUsHandler handler;

    @CrossOrigin
    @RequestMapping(value = "/mod/{id}", method = RequestMethod.PUT, headers = "Content-Type=application/json")
    public HttpEntity modificar(@PathVariable("id") String id, @RequestBody UsuarioDto usuario) {
        usuario.setUsuario(id);
        handler.handle(usuario);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
