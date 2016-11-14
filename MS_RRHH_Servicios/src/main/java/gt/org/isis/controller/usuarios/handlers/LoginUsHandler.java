/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.usuarios.handlers;

import gt.org.isis.api.AbstractRequestHandler;
import gt.org.isis.api.misc.exceptions.ExceptionsManager;
import gt.org.isis.controller.dto.UsuarioLoginDto;
import gt.org.isis.converters.RoleDtoConverter;
import gt.org.isis.model.Usuario;
import gt.org.isis.model.utils.EntitiesHelper;
import gt.org.isis.repository.PersonasRepository;
import gt.org.isis.repository.RolesRepository;
import gt.org.isis.repository.UsuariosRepository;
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
public class LoginUsHandler extends AbstractRequestHandler<UsuarioLoginDto, UsuarioLoginDto> {

    @Autowired
    RolesRepository roles;
    @Autowired
    UsuariosRepository usuarios;
    @Autowired
    PersonasRepository personas;

    @Override
    public UsuarioLoginDto execute(final UsuarioLoginDto request) {
        if (request.getUsuario() == null) {
            throw ExceptionsManager.newValidationException("invalid_user",
                    new String[]{"usuario,Usuario es requerido!"});
        }

        RuntimeException usuarioInvalido = ExceptionsManager.newValidationException("usuario_invalido",
                new String[]{"usuario,Usuario invalido!"});

        List<Usuario> ls = usuarios.findAll(new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery cq, CriteriaBuilder cb) {
                return cb.equal(root.get("id"), request.getUsuario());
            }
        });
        if (ls.isEmpty() || ls.size() > 1) {
            throw usuarioInvalido;
        }
        Usuario r = ls.get(0);
        if (!r.getClave().equalsIgnoreCase(EntitiesHelper.md5Gen(request.getClave()))) {
            throw usuarioInvalido;
        }
        request.setRole(new RoleDtoConverter().toDTO(r.getFkRole()));
        request.setClave("");
        return request;
    }

}