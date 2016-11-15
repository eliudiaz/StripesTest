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
import gt.org.isis.converters.RoleDtoConverter;
import gt.org.isis.model.AccesoRole;
import gt.org.isis.model.Role;
import gt.org.isis.model.enums.Estado;
import gt.org.isis.model.utils.EntitiesHelper;
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
public class CrearHandler extends AbstractRequestHandler<RoleDto, Role> {

    @Autowired
    RolesRepository roles;
    @Autowired
    AccesosRepository accesos;
    @Autowired
    AccesoRoleRepository accesosRole;

    @Override
    public Role execute(final RoleDto request) {
        final Role r = new RoleDtoConverter().toEntity(request);
        r.setAccesoRoleCollection(Collections2.transform(request.getAccesos(), new Function<AccesoDto, AccesoRole>() {
            @Override
            public AccesoRole apply(AccesoDto f) {
                AccesoRole acceso = new AccesoRole();
                acceso.setFkAcceso(accesos.findOne(f.getId()));
                acceso.setFkRole(r);
                acceso.setCreadoPor(request.getUsuario());
                EntitiesHelper.setDateCreateRef(acceso);
                EntitiesHelper.setDateUpdateRef(acceso);
                return acceso;
            }
        }));
        EntitiesHelper.setDateCreateRef(r);
        r.setEstado(Estado.ACTIVO);
        roles.save(r);
        return r;
    }

}
