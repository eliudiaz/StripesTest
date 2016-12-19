/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.converters;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import gt.org.ms.controller.dto.RoleDto;
import gt.org.ms.model.Role;
import gt.org.ms.api.utils.BeansConverter;
import gt.org.ms.model.Acceso;
import gt.org.ms.model.AccesoRole;
import java.util.ArrayList;

/**
 *
 * @author edcracken
 */
public class RoleDtoConverter extends BeansConverter<Role, RoleDto> {

    @Override
    public RoleDto toDTO(Role iA) {
        RoleDto toDTO = super.toDTO(iA);
        toDTO.setAccesos(new AccesoDtoConverter().toDTO(new ArrayList<Acceso>(Collections2
                .transform(iA.getAccesoRoleCollection(),
                        new Function<AccesoRole, Acceso>() {
                    @Override
                    public Acceso apply(AccesoRole f) {
                        return f.getFkAcceso();
                    }
                }))));
        return toDTO;
    }

}
