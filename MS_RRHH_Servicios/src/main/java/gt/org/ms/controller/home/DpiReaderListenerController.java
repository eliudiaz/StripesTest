/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.home;

import gt.org.ms.controller.home.reader.PersonaDPIDto;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author edcracken
 */
@Controller
@RequestMapping("dpi")
public class DpiReaderListenerController {

    private static final Map<String, PersonaDPIDto> CARDS
            = new HashMap<String, PersonaDPIDto>();

    @RequestMapping(value = "/push",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    public HttpEntity push(@RequestBody PersonaDPIDto persona) {
        System.out.println(">> pushing >> session >> " + persona.getSession());
        CARDS.put(persona.getSession(), persona);
        return new ResponseEntity(HttpStatus.OK);
    }
}
