/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.roles.handlers;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import gt.org.ms.api.requesting.AbstractRequestHandler;
import gt.org.ms.controller.dto.AccesoDto;
import gt.org.ms.controller.dto.RoleDto;
import gt.org.ms.converters.AccesoDtoConverter;
import gt.org.ms.converters.RoleDtoConverter;
import gt.org.ms.model.AccesoRole;
import gt.org.ms.model.Role;
import gt.org.ms.repository.AccesoRoleRepository;
import gt.org.ms.repository.AccesosRepository;
import gt.org.ms.repository.RolesRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author edcracken
 */
@Service
public class BuscarHandler extends AbstractRequestHandler<RoleDto, RoleDto> {

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
