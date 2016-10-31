/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.accesos;

import gt.org.isis.controller.accesos.handlers.ModificarAcHandler;
import gt.org.isis.controller.dto.AccesoDto;
import gt.org.isis.web.annotation.CrossOrigin;
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
@Controller("modificarRole")
@RequestMapping("accesos")
public class ModificarController {

    @Autowired
    ModificarAcHandler handler;

    @CrossOrigin
    @RequestMapping(value = "/mod/{id}", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity modificar(@PathVariable("id") Integer id, @RequestBody AccesoDto acceso) {
        acceso.setId(id);
        handler.handle(acceso);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
