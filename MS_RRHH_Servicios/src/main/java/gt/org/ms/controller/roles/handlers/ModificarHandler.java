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
import gt.org.ms.model.AccesoRole;
import gt.org.ms.model.Role;
import gt.org.ms.model.enums.Estado;
import gt.org.ms.repository.AccesoRoleRepository;
import gt.org.ms.repository.AccesosRepository;
import gt.org.ms.repository.RolesRepository;
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
        accesosRole.deleteInBatch(r.getAccesoRoleCollection());

        r.setNombre(request.getNombre());
        r.setAccesoRoleCollection(null);
        r.setEstado(Estado.ACTIVO);
        final Role r2 = roles.save(r);
        r2.setAccesoRoleCollection(Collections2.transform(request.getAccesos(), new Function<AccesoDto, AccesoRole>() {
            @Override
            public AccesoRole apply(AccesoDto f) {
                AccesoRole acceso = new AccesoRole();
                acceso.setFkAcceso(accesos.findOne(f.getId()));
                acceso.setFkRole(r2);
                setCreateInfo(acceso);
                return acceso;
            }
        }));
        accesosRole.save(r2.getAccesoRoleCollection());
        return request;
    }

}
