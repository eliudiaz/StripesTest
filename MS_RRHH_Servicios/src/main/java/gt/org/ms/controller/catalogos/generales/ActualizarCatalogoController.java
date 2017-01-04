/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.catalogos.generales;

import gt.org.ms.controller.catalogos.generales.handlers.ActualizarCatalogoGeneralHandler;
import gt.org.ms.controller.dto.CatalogoDto;
import gt.org.ms.converters.CatalogosDtoConverter;
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
@Controller
@RequestMapping("catalogos")
public class ActualizarCatalogoController {

    @Autowired
    private ActualizarCatalogoGeneralHandler handler;

    @RequestMapping(value = "/update/{id}",
            method = RequestMethod.PUT)
    public HttpEntity modificar(@PathVariable("id") Integer id, @RequestBody CatalogoDto catalogo) {
        catalogo.setId(id);
        handler.handle(new CatalogosDtoConverter().toEntity(catalogo));
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
