/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.home;

import gt.org.isis.controller.dto.BusquedaNormalDto;
import gt.org.isis.controller.dto.PersonaDto;
import gt.org.isis.controller.home.handlers.BusquedaNormalHandler;
import gt.org.isis.converters.PersonaRowsFileDtoConverter;
import java.util.List;
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
 * @author edcracken
 */
@Controller
@RequestMapping("home")
public class BusquedaNormalController {

    @Autowired
    BusquedaNormalHandler handler;
    @Autowired
    FileContentQueue queue;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @RequestMapping(value = "/busquedaNormal",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = {MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_OCTET_STREAM_VALUE},
            method = RequestMethod.POST)
    public ResponseEntity buscar(@RequestBody @Valid BusquedaNormalDto filtro,
            @RequestParam(value = "sessionId", required = false, defaultValue = "0") String sessionId) {
        List<PersonaDto> out = handler.handle(filtro);
        queue.push(sessionId, new PersonaRowsFileDtoConverter().toEntity(out));

        return new ResponseEntity(handler.handle(filtro), HttpStatus.OK);
    }
}
