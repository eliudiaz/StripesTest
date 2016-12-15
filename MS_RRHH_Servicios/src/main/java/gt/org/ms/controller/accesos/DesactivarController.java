/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.accesos;

import gt.org.ms.controller.accesos.handlers.DesactivarAcHandler;
import gt.org.ms.model.Acceso;
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
@Controller("desactivarAcceso")
@RequestMapping("accesos")
public class DesactivarController {

    @Autowired
    DesactivarAcHandler handler;

    @RequestMapping(value = "/disable/{id}", method = RequestMethod.DELETE)
    public HttpEntity modificar(@PathVariable("id") Integer id) {
        handler.handle(new Acceso(id));
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
