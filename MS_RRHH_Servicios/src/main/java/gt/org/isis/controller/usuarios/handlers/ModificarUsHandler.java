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
import gt.org.isis.model.Role;
import gt.org.isis.model.Usuario;
import gt.org.isis.model.UsuarioRoles;
import gt.org.isis.model.utils.EntitiesHelper;
import gt.org.isis.repository.PersonasRepository;
import gt.org.isis.repository.RolesRepository;
import gt.org.isis.repository.UsuariosRepository;
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
public class ModificarUsHandler extends AbstractRequestHandler<UsuarioDto, UsuarioDto> {

    @Autowired
    RolesRepository roles;
    @Autowired
    UsuariosRepository usuarios;
    @Autowired
    PersonasRepository personas;

    @Override
    public UsuarioDto execute(final UsuarioDto rq) {
        List<Usuario> ls = usuarios.findAll(new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery cq, CriteriaBuilder cb) {
                return cb.equal(root.get("id"), rq.getUsuario());
            }
        });
        if (ls.isEmpty()) {
            throw ExceptionsManager.newValidationException("usuario_no_existe",
                    new String[]{"usuario,El usuario no existe!"});
        }
        UsuarioDtoConverter bc = new UsuarioDtoConverter();
        final Usuario dbUser = ls.get(0); //usuarios.findOne(request.getUsuario());
        roles.deleteInBatch((List) dbUser.getUsuarioRolesCollection());

        List<UsuarioRoles> lsRoles = new ArrayList<UsuarioRoles>(Collections2.transform(rq.getRoles(),
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
        dbUser.setUsuarioRolesCollection(lsRoles);
        if (dbUser.getFkPersona() == null || !dbUser.getFkPersona().getCui().equals(rq.getCui())) {
            dbUser.setFkPersona(personas.findOne(rq.getCui()));
        }
        String newPass;
        if (!dbUser.getClave().equalsIgnoreCase(newPass
                = new String(DigestUtils.md5Digest(rq.getClave().getBytes())))) {
            if (rq.getClave().equalsIgnoreCase(rq.getConfirmacionClave())) {
                dbUser.setClave(newPass);
            } else {
                throw ExceptionsManager.newValidationException("clave_invalida",
                        new String[]{"clave,Clave y confirmacion no coinciden!"});
            }
        }

        EntitiesHelper.setDateUpdateRef(dbUser);
        dbUser.setNombres(rq.getNombres());
        dbUser.setApellidos(rq.getApellidos());
        dbUser.setEstado(rq.getEstado());

        usuarios.save(dbUser);

        return bc.toDTO(dbUser);

    }

}
