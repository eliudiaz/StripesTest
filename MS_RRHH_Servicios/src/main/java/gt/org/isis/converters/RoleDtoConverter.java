/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.converters;

import gt.org.isis.controller.dto.RoleDto;
import gt.org.isis.model.Role;
import gt.org.isis.api.utils.BeansConverter;
import java.util.List;

/**
 *
 * @author edcracken
 */
public class RoleDtoConverter extends BeansConverter<Role, RoleDto> {

    @Override
    public RoleDto toDTO(Role iA) {
        RoleDto toDTO = super.toDTO(iA);
        toDTO.setAccesos(new AccesoDtoConverter().toDTO((List) iA.getAccesoRoleCollection()));
        return toDTO;
    }

}
