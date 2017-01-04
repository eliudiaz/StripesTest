/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.catalogos.generales;

import gt.org.ms.controller.catalogos.generales.handlers.BuscarGenTodosHandler;
import gt.org.ms.controller.dto.CatalogoDto;
import gt.org.ms.controller.dto.CatalogosRequestDto;
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
@Controller("getCatalos")
@RequestMapping("catalogos")
public class BuscarTodosCatalogosController {

    @Autowired
    BuscarGenTodosHandler handler;

    @RequestMapping(value = "/get/all", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<CatalogoDto> getList(@RequestParam(value = "padre", required = false) Integer padre,
            @RequestParam(value = "valor", required = false) String valor,
            @RequestParam("tipo") String tipo) {

        return handler.handle(new CatalogosRequestDto(padre, tipo, valor));
    }
}
