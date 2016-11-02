/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.usuarios.handlers;

import gt.org.isis.api.AbstractRequestHandler;
import gt.org.isis.controller.dto.UsuarioDto;
import gt.org.isis.converters.UsuarioDtoConverter;
import gt.org.isis.model.Persona;
import gt.org.isis.model.Role;
import gt.org.isis.model.Usuario;
import gt.org.isis.model.utils.EntitiesHelper;
import gt.org.isis.repository.PersonasRepository;
import gt.org.isis.repository.RolesRepository;
import gt.org.isis.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 *
 * @author edcracken
 */
@Service
public class CrearUsHandler extends AbstractRequestHandler<UsuarioDto, UsuarioDto> {

    @Autowired
    RolesRepository roles;
    @Autowired
    UsuariosRepository usuarios;
    @Autowired
    PersonasRepository personas;

    @Override
    public UsuarioDto execute(final UsuarioDto request) {
//        Persona persona = personas.findOne(request.getCui());
        Role role = roles.findOne(request.getRoleId());
        UsuarioDtoConverter bc;
        final Usuario r = (bc = new UsuarioDtoConverter()).toEntity(request);
        r.setClave(new String(DigestUtils.md5Digest(request.getClave().getBytes())));
//        r.setFkPersona(persona);
        r.setFkRole(role);
        r.setId(request.getUsuario());
        EntitiesHelper.setDateCreateRef(r);
        r.setCreadoPor("test");
        UsuarioDto us = bc.toDTO(usuarios.save(r));
        us.setClave(null);
        us.setConfirmacionClave(null);
        return us;
    }

}
