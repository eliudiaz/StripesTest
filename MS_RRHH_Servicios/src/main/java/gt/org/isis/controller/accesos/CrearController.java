/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.accesos;

import gt.org.isis.controller.accesos.handlers.CrearAcHandler;
import gt.org.isis.controller.dto.AccesoDto;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author eliud
 */
@Controller("crearAcceso")
@RequestMapping("accesos")
public class CrearController {

    @Autowired
    CrearAcHandler crear;

    @Transactional
    @RequestMapping(value = "/crea",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    public @ResponseBody
    AccesoDto crear(@RequestBody @Valid AccesoDto acceso) {
        return crear.handle(acceso);
    }
}
