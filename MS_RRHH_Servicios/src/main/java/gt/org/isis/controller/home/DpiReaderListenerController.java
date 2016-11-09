/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.home;

import gt.org.isis.controller.home.reader.PersonaDPIDto;
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
@RequestMapping("home")
public class DpiReaderListenerController {

    private static final Map<String, PersonaDPIDto> cola
            = new HashMap<String, PersonaDPIDto>();

    @RequestMapping(value = "/push",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    public HttpEntity push(@RequestBody PersonaDPIDto filtro) {
        cola.put("11092016", filtro);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/pull",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    public HttpEntity pull() {
        return new ResponseEntity(cola.get("11092016"), HttpStatus.OK);
    }
}
