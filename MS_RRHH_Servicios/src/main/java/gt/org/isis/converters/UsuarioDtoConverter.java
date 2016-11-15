/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.converters;

import gt.org.isis.controller.dto.UsuarioDto;
import gt.org.isis.model.Usuario;
import gt.org.isis.model.utils.BeansConverter;

/**
 *
 * @author edcracken
 */
public class UsuarioDtoConverter extends BeansConverter<Usuario, UsuarioDto> {

    @Override
    public UsuarioDto toDTO(Usuario iA) {
        UsuarioDto dto = super.toDTO(iA);
        dto.setUsuario(iA.getId());
        return dto;
    }

}
