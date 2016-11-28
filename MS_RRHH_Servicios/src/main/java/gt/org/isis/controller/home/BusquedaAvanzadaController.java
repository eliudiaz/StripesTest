/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.home;

import gt.org.isis.controller.dto.BusquedaAvanzadaDto;
import gt.org.isis.controller.dto.PersonaDto;
import gt.org.isis.controller.home.handlers.BusquedaAvanzadaHandler;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author eliud
 */
@Controller
@RequestMapping("home")
public class BusquedaAvanzadaController extends DownloadSupportController {

    @Autowired
    BusquedaAvanzadaHandler handler;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @RequestMapping(value = "/busquedaAvanzada",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    public @ResponseBody
    List<PersonaDto> crear(@RequestBody @Valid BusquedaAvanzadaDto filtro, @RequestParam(value = "download", required = false, defaultValue = "false") boolean download,
            HttpServletResponse response) {
        List<PersonaDto> out = handler.handle(filtro);
        if (download) {
            processDownload(out, response);
            new ResponseEntity(HttpStatus.OK);
        }
        return handler.handle(filtro);
    }
}
