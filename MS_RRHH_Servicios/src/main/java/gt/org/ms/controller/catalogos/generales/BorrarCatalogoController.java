/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.catalogos.generales;

import gt.org.ms.controller.catalogos.generales.handlers.EliminarCatalogoGeneralHandler;
import gt.org.ms.model.Catalogos;
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
@RequestMapping("catalogos")
public class BorrarCatalogoController {

    @Autowired
    private EliminarCatalogoGeneralHandler handler;

    @RequestMapping(value = "/delete/{id}",
            method = RequestMethod.DELETE)
    public HttpEntity modificar(@PathVariable("id") Integer id) {
        handler.handle(new Catalogos(id));
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
