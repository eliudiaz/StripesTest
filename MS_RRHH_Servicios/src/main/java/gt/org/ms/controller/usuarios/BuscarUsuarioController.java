/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.usuarios;

import gt.org.ms.controller.dto.UsuarioDto;
import gt.org.ms.controller.usuarios.handlers.BuscarUsHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author edcracken
 */
@Controller
@RequestMapping("usuarios")
public class BuscarUsuarioController {

    @Autowired
    BuscarUsHandler handler;

    @RequestMapping("/get/{id}")
    public @ResponseBody
    UsuarioDto getUsuario(@PathVariable("id") String id) {

        return handler.handle(new UsuarioDto(id));
    }
}
