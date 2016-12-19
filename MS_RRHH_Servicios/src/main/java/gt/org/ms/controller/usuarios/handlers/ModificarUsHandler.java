/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.usuarios.handlers;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import gt.org.ms.api.requesting.AbstractValidationsRequestHandler;
import static gt.org.ms.api.requesting.ValidationsHelper.isNull;
import gt.org.ms.api.global.exceptions.ExceptionsManager;
import gt.org.ms.controller.dto.RoleDto;
import gt.org.ms.controller.dto.UsuarioDto;
import gt.org.ms.converters.UsuarioDtoConverter;
import gt.org.ms.model.Role;
import gt.org.ms.model.Usuario;
import gt.org.ms.model.UsuarioRoles;
import gt.org.ms.model.enums.Estado;
import gt.org.ms.api.utils.EntitiesHelper;
import gt.org.ms.repository.PersonasRepository;
import gt.org.ms.repository.RolesRepository;
import gt.org.ms.repository.UsuarioRolesRepository;
import gt.org.ms.repository.UsuariosRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 *
 * @author edcracken
 */
@Service
public class ModificarUsHandler extends AbstractValidationsRequestHandler<UsuarioDto, UsuarioDto> {

    @Autowired
    RolesRepository roles;
    @Autowired
    UsuarioRolesRepository uRoles;
    @Autowired
    UsuariosRepository usuarios;
    @Autowired
    PersonasRepository personas;

    @Override
    public UsuarioDto execute(final UsuarioDto request) {
        List<Usuario> ls = usuarios.findAll(new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery cq, CriteriaBuilder cb) {
                return cb.equal(root.get("id"), request.getUsuario());
            }
        });
        if (ls.isEmpty()) {
            throw ExceptionsManager.newValidationException("usuario_no_existe",
                    new String[]{"usuario,El usuario no existe!"});
        }
        if (request.isResetClave() && (request.getClave() == null || request.getClave().isEmpty())) {
            throw ExceptionsManager.newValidationException("clave_vacia",
                    new String[]{"usuario,Clave no puede ser vacia!"});
        }
        if (request.isResetClave() && (request.getConfirmacionClave() == null || request.getConfirmacionClave().isEmpty())) {
            throw ExceptionsManager.newValidationException("clave_vacia",
                    new String[]{"usuario,Clave confirmacion no puede ser vacia"});
        }
        if (request.isResetClave() && !request.getConfirmacionClave().equalsIgnoreCase(request.getClave())) {
            throw ExceptionsManager.newValidationException("clave_no_coincide",
                    new String[]{"usuario,Clave confirmacion y clave deben coincidir!"});
        }

        UsuarioDtoConverter bc = new UsuarioDtoConverter();
        final Usuario dbUser = ls.get(0); //usuarios.findOne(request.getUsuario());
        if (!isNull(request.getCui()) && !request.getCui().isEmpty()) {
            dbUser.setFkPersona(personas.findOne(request.getCui()));
        }
        List<UsuarioRoles> lsRoles = new ArrayList<UsuarioRoles>(Collections2.transform(request.getRoles(),
                new Function<RoleDto, UsuarioRoles>() {
            @Override
            public UsuarioRoles apply(RoleDto f) {
                Role role = roles.findOne(f.getId());
                UsuarioRoles urs = new UsuarioRoles();
                urs.setFkRole(role);
                urs.setFkUsuario(dbUser);
                if (isNull(role)) {
                    throw ExceptionsManager.newValidationException("invalid_role",
                            new String[]{"role,Role es invalido o no existe"});
                }
                return urs;
            }
        }));
        if (!lsRoles.isEmpty()) {
            uRoles.deleteInBatch((List) dbUser.getUsuarioRolesCollection());
            dbUser.setUsuarioRolesCollection(lsRoles);
        }

        if (request.isResetClave()) {
            String newPass = new String(DigestUtils.md5Digest(request.getClave().getBytes()));
            dbUser.setClave(newPass);
        }

        EntitiesHelper.setDateUpdatedInfo(dbUser);
        dbUser.setNombres(request.getNombres());
        dbUser.setApellidos(request.getApellidos());
        dbUser.setEstado(Estado.ACTIVO);

        usuarios.save(dbUser);

        return bc.toDTO(dbUser);

    }

}
