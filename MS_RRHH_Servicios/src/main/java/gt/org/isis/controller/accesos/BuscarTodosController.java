/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.accesos;

import gt.org.isis.controller.accesos.handlers.BuscarAcTodosHandler;
import gt.org.isis.controller.dto.AccesoDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author edcracken
 */
@Controller("getAccesos")
@RequestMapping("accesos")
public class BuscarTodosController {

    @Autowired
    BuscarAcTodosHandler handler;

    @RequestMapping(value = "/get/all", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<AccesoDto> getList(@RequestParam(value = "padre", required = false) Integer padre) {
        return handler.handle(padre);
    }
}
