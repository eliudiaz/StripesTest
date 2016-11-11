/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.usuarios.handlers;

import gt.org.isis.api.AbstractRequestHandler;
import gt.org.isis.api.misc.exceptions.ExceptionsManager;
import gt.org.isis.controller.dto.UsuarioLoginDto;
import gt.org.isis.model.Usuario;
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
public class LoginUsHandler extends AbstractRequestHandler<UsuarioLoginDto, Boolean> {

    @Autowired
    RolesRepository roles;
    @Autowired
    UsuariosRepository usuarios;
    @Autowired
    PersonasRepository personas;

    @Override
    public Boolean execute(final UsuarioLoginDto request) {
        if (request.getUsuario() == null) {
            throw ExceptionsManager.newValidationException("invalid_user",
                    new String[]{"usuario,Usuario es requerido!"});
        }

        RuntimeException usuarioInvalido = ExceptionsManager.newValidationException("usuario_invalido",
                new String[]{"usuario,Usuario invalido!"});
        Usuario r;
        if ((r = usuarios.findOne(request.getUsuario())) == null) {
            throw usuarioInvalido;
        }

        String clave = new String(DigestUtils.md5Digest(request.getClave().getBytes()));
        if (!r.getClave().equalsIgnoreCase(clave)) {
            throw usuarioInvalido;
        }
        return Boolean.TRUE;
    }

}
