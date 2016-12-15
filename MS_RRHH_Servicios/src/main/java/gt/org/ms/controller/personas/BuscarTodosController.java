/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.personas;

import gt.org.ms.api.web.DownloadSupportController;
import gt.org.ms.controller.dto.PersonaDto;
import gt.org.ms.controller.personas.handlers.PersonaBuscarTodosHandler;
import gt.org.ms.converters.PersonaRowsFileDtoConverter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author edcracken
 */
@Controller
@RequestMapping("personas")
public class BuscarTodosController extends DownloadSupportController {

    @Autowired
    PersonaBuscarTodosHandler handler;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @RequestMapping(value = "/todos", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_OCTET_STREAM_VALUE})
    public HttpEntity getPersona(@RequestParam(value = "download", required = false, defaultValue = "false") boolean download,
            HttpServletResponse response) {
        List<PersonaDto> out = handler.handle(null);
        if (download) {
            produceResponseContent(response, out);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity<List<PersonaDto>>(out, HttpStatus.OK);
    }
}
