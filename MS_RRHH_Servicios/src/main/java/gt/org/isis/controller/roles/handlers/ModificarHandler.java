/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.roles.handlers;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import gt.org.isis.api.AbstractRequestHandler;
import gt.org.isis.controller.dto.AccesoDto;
import gt.org.isis.controller.dto.RoleDto;
import gt.org.isis.model.AccesoRole;
import gt.org.isis.model.Role;
import gt.org.isis.model.enums.Estado;
import gt.org.isis.api.utils.EntitiesHelper;
import gt.org.isis.repository.AccesoRoleRepository;
import gt.org.isis.repository.AccesosRepository;
import gt.org.isis.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author edcracken
 */
@Service
public class ModificarHandler extends AbstractRequestHandler<RoleDto, RoleDto> {

    @Autowired
    RolesRepository roles;
    @Autowired
    AccesosRepository accesos;
    @Autowired
    AccesoRoleRepository accesosRole;

    @Override
    public RoleDto execute(final RoleDto request) {
        final Role r = roles.findOne(request.getId());
        r.setNombre(request.getNombre());
        for (AccesoRole ar : r.getAccesoRoleCollection()) {
            accesosRole.delete(ar);
        }
        r.setEstado(Estado.ACTIVO);
        final Role r2 = roles.save(r);
        r.setAccesoRoleCollection(Collections2.transform(request.getAccesos(), new Function<AccesoDto, AccesoRole>() {
            @Override
            public AccesoRole apply(AccesoDto f) {
                AccesoRole acceso = new AccesoRole();
                acceso.setFkAcceso(accesos.findOne(f.getId()));
                acceso.setFkRole(r2);
                EntitiesHelper.setDateCreatedInfo(acceso);
                return acceso;
            }
        }));
        EntitiesHelper.setDateUpdatedInfo(r2);

        return request;
    }

}
