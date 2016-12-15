/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.usuarios;

import gt.org.ms.api.requesting.BaseController;
import gt.org.ms.controller.dto.UsuarioDto;
import gt.org.ms.controller.usuarios.handlers.CrearUsHandler;
import javax.servlet.http.HttpServletRequest;
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
@Controller
@RequestMapping("usuarios")
public class CrearUsuarioController extends BaseController {

    @Autowired
    CrearUsHandler handler;

    @RequestMapping(value = "/crea",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    public @ResponseBody
    UsuarioDto crear(@RequestBody @Valid UsuarioDto usuario,
            HttpServletRequest request) {
        configureSesion(usuario, request);
        return handler.handle(usuario);
    }
}
