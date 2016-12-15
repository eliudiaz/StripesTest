/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.home;

import gt.org.ms.api.web.DownloadSupportController;
import gt.org.ms.controller.dto.BusquedaAvanzadaDto;
import gt.org.ms.controller.dto.PersonaDto;
import gt.org.ms.controller.home.handlers.BusquedaAvanzadaHandler;
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
    public ResponseEntity crear(@RequestBody @Valid BusquedaAvanzadaDto filtro,
            @RequestParam(value = "download", required = false, defaultValue = "false") boolean download,
            HttpServletResponse response) {
        List<PersonaDto> out = handler.handle(filtro);
        if (download) {
            produceResponseContent(response, out);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(out, HttpStatus.OK);

    }
}
