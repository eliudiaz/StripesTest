/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.roles;

import gt.org.isis.controller.dto.RoleDto;
import gt.org.isis.controller.roles.handlers.CrearHandler;
import gt.org.isis.converters.RoleDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author eliud
 */
@Controller("crearAccesos")
@RequestMapping("roles")
public class CrearController {

    @Autowired
    CrearHandler crear;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @RequestMapping(value = "/crea",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    public @ResponseBody
    RoleDto crear(@RequestBody RoleDto persona) {
        return new RoleDtoConverter().toDTO(crear.handle(persona));
    }
}
