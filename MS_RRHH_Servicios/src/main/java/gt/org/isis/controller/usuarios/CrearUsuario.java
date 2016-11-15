/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.usuarios;

import gt.org.isis.controller.dto.UsuarioDto;
import gt.org.isis.controller.usuarios.handlers.CrearUsHandler;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author eliud
 */
@Controller("usuariosCrear")
@RequestMapping("usuarios")
public class CrearUsuario {

    @Autowired
    CrearUsHandler handler;

    @RequestMapping(value = "/crea",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    public @ResponseBody
    UsuarioDto crear(@RequestBody @Valid UsuarioDto usuario) {
        return handler.handle(usuario);
    }
}
