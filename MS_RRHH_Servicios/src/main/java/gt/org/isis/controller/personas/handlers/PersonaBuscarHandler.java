/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.personas.handlers;

import gt.org.isis.controller.roles.handlers.*;
import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import gt.org.isis.api.AbstractRequestHandler;
import gt.org.isis.controller.dto.AccesoDto;
import gt.org.isis.controller.dto.RoleDto;
import gt.org.isis.converters.AccesoDtoConverter;
import gt.org.isis.converters.RoleDtoConverter;
import gt.org.isis.model.AccesoRole;
import gt.org.isis.model.Role;
import gt.org.isis.repository.AccesoRoleRepository;
import gt.org.isis.repository.AccesosRepository;
import gt.org.isis.repository.RolesRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author edcracken
 */
@Service
public class PersonaBuscarHandler extends AbstractRequestHandler<RoleDto, RoleDto> {

    @Autowired
    RolesRepository roles;
    @Autowired
    AccesosRepository accesos;
    @Autowired
    AccesoRoleRepository accesosRole;

    @Override
    public RoleDto execute(final RoleDto request) {
        Role r;
        RoleDto rd = new RoleDtoConverter().toDTO(r = roles.findOne(request.getId()));
        rd.setAccesos(new ArrayList(Collections2.transform(r.getAccesoRoleCollection(),
                new Function<AccesoRole, AccesoDto>() {
            @Override
            public AccesoDto apply(AccesoRole f) {
                return new AccesoDtoConverter().toDTO(f.getFkAcceso());
            }
        })));
        return rd;
    }

}