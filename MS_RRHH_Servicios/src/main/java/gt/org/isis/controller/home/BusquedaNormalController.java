/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.home;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import gt.org.isis.controller.dto.BusquedaNormalDto;
import gt.org.isis.controller.dto.PersonaDto;
import gt.org.isis.controller.dto.PersonaRowsFileDto;
import gt.org.isis.controller.home.handlers.BusquedaNormalHandler;
import gt.org.isis.controller.home.handlers.PersonaCompletarDatosHandler;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
    PersonaCompletarDatosHandler completarHandler;
    @Autowired
    FileContentQueue queue;

    @RequestMapping(value = "/busquedaNormal",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    public HttpEntity buscar(@RequestBody @Valid BusquedaNormalDto filtro,
            @RequestParam(value = "download", required = false, defaultValue = "false") boolean download) {
        List<PersonaDto> out = handler.handle(filtro);
        if (download) {
            queue.push((int) System.currentTimeMillis(), new ArrayList(Collections2.transform(out, new Function<PersonaDto, PersonaRowsFileDto>() {
                @Override
                public PersonaRowsFileDto apply(PersonaDto f) {
                    return completarHandler.handle(f);
                }
            })));
        }
        return new ResponseEntity(handler.handle(filtro), HttpStatus.OK);
    }
}
