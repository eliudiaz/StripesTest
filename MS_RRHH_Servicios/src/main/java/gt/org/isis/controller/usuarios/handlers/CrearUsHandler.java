/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.usuarios.handlers;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import gt.org.isis.api.AbstractRequestHandler;
import static gt.org.isis.api.ValidationsHelper.isNull;
import gt.org.isis.api.misc.exceptions.ExceptionsManager;
import gt.org.isis.controller.dto.RoleDto;
import gt.org.isis.controller.dto.UsuarioDto;
import gt.org.isis.converters.UsuarioDtoConverter;
import gt.org.isis.model.Persona;
import gt.org.isis.model.Role;
import gt.org.isis.model.Usuario;
import gt.org.isis.model.UsuarioRoles;
import gt.org.isis.api.utils.EntitiesHelper;
import gt.org.isis.repository.PersonasRepository;
import gt.org.isis.repository.RolesRepository;
import gt.org.isis.repository.UsuariosRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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
        if (request.getUsuario() == null) {
            throw ExceptionsManager.newValidationException("invalid_user",
                    new String[]{"usuario,Usuario es requerido!"});
        }
        if (request.getClave() == null || request.getClave().isEmpty()) {
            throw ExceptionsManager.newValidationException("clave_vacia",
                    new String[]{"usuario,Clave no puede ser vacia!"});
        }
        if (request.getConfirmacionClave() == null || request.getConfirmacionClave().isEmpty()) {
            throw ExceptionsManager.newValidationException("clave_vacia",
                    new String[]{"usuario,Clave confirmacion no puede ser vacia"});
        }
        if (!request.getConfirmacionClave().equalsIgnoreCase(request.getClave())) {
            throw ExceptionsManager.newValidationException("clave_no_coincide",
                    new String[]{"usuario,Clave confirmacion y clave deben coincidir!"});
        }
        List<Usuario> ls = usuarios.findAll(new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery cq, CriteriaBuilder cb) {
                return cb.equal(root.get("id"), request.getUsuario());
            }
        });
        if (!ls.isEmpty()) {
            throw ExceptionsManager.newValidationException("already_exits",
                    new String[]{"usuario,Usuario ya existe!"});
        }

        UsuarioDtoConverter bc;
        final Usuario r = (bc = new UsuarioDtoConverter()).toEntity(request);

        r.setClave(EntitiesHelper.md5Gen(request.getClave()));

        r.setId(request.getUsuario());
        EntitiesHelper.setDateCreatedInfo(r);
        r.setCreadoPor("test");
        r.setSuperUsuario(request.isRoot());
        if (request.getRoles() != null && !request.getRoles().isEmpty()) {
            List<UsuarioRoles> lsRoles = new ArrayList<UsuarioRoles>(Collections2
                    .transform(request.getRoles(),
                            new Function<RoleDto, UsuarioRoles>() {
                        @Override
                        public UsuarioRoles apply(RoleDto f) {
                            Role role = roles.findOne(f.getId());
                            UsuarioRoles urs = new UsuarioRoles();
                            urs.setFkRole(role);
                            urs.setFkUsuario(r);
                            if (isNull(role)) {
                                throw ExceptionsManager.newValidationException("invalid_role",
                                        new String[]{"role,Role es invalido o no existe"});
                            }
                            return urs;
                        }
                    }));
            r.setUsuarioRolesCollection((Collection) lsRoles);
        }
        RuntimeException re = ExceptionsManager.newValidationException("cui_persona",
                new String[]{"persona_invalida,CUI asignado a usuario no existe o es invalido!"});

        if (!request.isRoot() && (request.getCui() == null || request.getCui().length() < 13)) {
            throw re;
        }
        if (!request.isRoot()) {
            Persona persona = personas.findOne(request.getCui());

            if (persona == null) {
                throw re;
            }
            r.setFkPersona(persona);
        }

        UsuarioDto us = bc.toDTO(usuarios.save(r));
        us.setRoot(request.isRoot());
        us.setClave(null);
        us.setConfirmacionClave(null);

        return us;
    }

}
