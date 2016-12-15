/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.accesos;

import gt.org.isis.api.requesting.BaseController;
import gt.org.isis.controller.accesos.handlers.ModificarAcHandler;
import gt.org.isis.controller.dto.AccesoDto;
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
 * @author eliud
 */
@Controller("modificarAcceso")
@RequestMapping("accesos")
public class ModificarController extends BaseController {

    @Autowired
    ModificarAcHandler handler;

    @RequestMapping(value = "/mod/{id}", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity modificar(@PathVariable("id") Integer id, @RequestBody AccesoDto acceso,
            HttpServletRequest request) {
        configureSesion(acceso, request);
        acceso.setId(id);
        handler.handle(acceso);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
