/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.accesos;

import gt.org.ms.controller.accesos.handlers.BuscarAcHandler;
import gt.org.ms.controller.dto.AccesoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author edcracken
 */
@Controller("getAcceso")
@RequestMapping("accesos")
public class BuscarController {

    @Autowired
    BuscarAcHandler handler;

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    AccesoDto getRole(@PathVariable("id") Integer id) {
        return handler.handle(new AccesoDto(id));
    }
}
