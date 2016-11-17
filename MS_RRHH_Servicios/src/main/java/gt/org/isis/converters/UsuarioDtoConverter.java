/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.converters;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import gt.org.isis.controller.dto.RoleDto;
import gt.org.isis.controller.dto.UsuarioDto;
import gt.org.isis.model.Usuario;
import gt.org.isis.model.UsuarioRoles;
import gt.org.isis.model.utils.BeansConverter;
import java.util.ArrayList;

/**
 *
 * @author edcracken
 */
public class UsuarioDtoConverter extends BeansConverter<Usuario, UsuarioDto> {

    @Override
    public UsuarioDto toDTO(Usuario iA) {
        UsuarioDto dto = super.toDTO(iA);
        if (iA.getFkPersona() != null) {
            //dto.setPersona(new PersonaDtoConverter().toDTO(iA.getFkPersona()));
            dto.setCui(iA.getFkPersona().getCui());
        }

        dto.setRoot(iA.getSuperUsuario() != null && iA.getSuperUsuario());
        dto.setUsuario(iA.getId());
        dto.setRoles(new ArrayList<RoleDto>());
        dto.getRoles().addAll(Collections2.transform(iA.getUsuarioRolesCollection(),
                new Function<UsuarioRoles, RoleDto>() {
            @Override
            public RoleDto apply(UsuarioRoles f) {
                return new RoleDtoConverter().toDTO(f.getFkRole());
            }
        }));
        return dto;
    }

}
