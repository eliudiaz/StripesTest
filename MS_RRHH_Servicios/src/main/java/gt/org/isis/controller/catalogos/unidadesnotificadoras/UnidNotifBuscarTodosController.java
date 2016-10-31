/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.catalogos.unidadesnotificadoras;

import gt.org.isis.controller.catalogos.unidadesnotificadoras.handlers.UnidNotifBuscarTodosHandler;
import gt.org.isis.controller.dto.CatalogosRequestDto;
import gt.org.isis.controller.dto.PuestoDto;
import gt.org.isis.controller.dto.UnidadNotificadoraDto;
import gt.org.isis.web.annotation.CrossOrigin;
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
@Controller("getUnidadesNotificadoras")
@RequestMapping("unidades-notificadoras")
public class UnidNotifBuscarTodosController {

    @Autowired
    UnidNotifBuscarTodosHandler handler;

    @CrossOrigin
    @RequestMapping(value = "/get/all", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<UnidadNotificadoraDto> getList(@RequestParam("padre") Integer padre,
            @RequestParam("valor") String valor,
            @RequestParam("padres") Integer tipo) {

        return handler.handle(new CatalogosRequestDto(padre, tipo, valor));
    }
}
