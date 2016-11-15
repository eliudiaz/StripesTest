/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.roles;

import gt.org.isis.controller.dto.RoleDto;
import gt.org.isis.controller.roles.handlers.BuscarHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author edcracken
 */
@Controller("getRole")
@RequestMapping("roles")
public class BuscarController {

    @Autowired
    BuscarHandler handler;

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    RoleDto getRole(@PathVariable("id") Integer id) {
        return handler.handle(new RoleDto(id));
    }
}
