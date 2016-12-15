/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.roles;

import gt.org.ms.api.requesting.BaseController;
import gt.org.ms.controller.dto.RoleDto;
import gt.org.ms.controller.roles.handlers.CrearHandler;
import gt.org.ms.converters.RoleDtoConverter;
import javax.servlet.http.HttpServletRequest;
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
@Controller("crearRoles")
@RequestMapping("roles")
public class CrearController extends BaseController {

    @Autowired
    CrearHandler crear;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @RequestMapping(value = "/crea",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    public @ResponseBody
    RoleDto crear(@RequestBody RoleDto item,
            HttpServletRequest request) {
        configureSesion(item, request);
        return new RoleDtoConverter().toDTO(crear.handle(item));
    }
}
