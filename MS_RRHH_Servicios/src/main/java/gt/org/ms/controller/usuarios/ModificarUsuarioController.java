/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.usuarios;

import gt.org.ms.api.requesting.BaseController;
import gt.org.ms.controller.dto.UsuarioDto;
import gt.org.ms.controller.usuarios.handlers.ModificarUsHandler;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
@Controller
@RequestMapping("usuarios")
public class ModificarUsuarioController extends BaseController {

    @Autowired
    ModificarUsHandler handler;

    @RequestMapping(value = "/mod/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.PUT)
    public HttpEntity modificar(@PathVariable("id") String id, @RequestBody UsuarioDto usuario,
            HttpServletRequest request) {
        configureSesion(usuario, request);
        usuario.setUsuario(id);
        handler.handle(usuario);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
