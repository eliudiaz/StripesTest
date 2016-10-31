/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.unidadesejecutoras;

import gt.org.isis.api.C;
import gt.org.isis.controller.dto.UnidadEjecutoraDto;
import gt.org.isis.controller.unidadesejecutoras.handlers.UnEjecBuscarTodosHandler;
import gt.org.isis.web.annotation.CrossOrigin;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author edcracken
 */
@Controller
@RequestMapping("unidades-ejecutoras")
public class UnidEjecBuscarTodosController {

    @Autowired
    UnEjecBuscarTodosHandler handler;

    @CrossOrigin
    @RequestMapping(value = "/get/all", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<UnidadEjecutoraDto> getList() {
        return handler.handle(C.EMPTY);
    }
}
