/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.usuarios.handlers;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import gt.org.isis.api.AbstractRequestHandler;
import gt.org.isis.api.entities.NoDisableEntitiesSpec;
import gt.org.isis.controller.dto.UsuarioDto;
import gt.org.isis.converters.UsuarioDtoConverter;
import gt.org.isis.model.Usuario;
import gt.org.isis.repository.UsuariosRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author edcracken
 */
@Service
public class BuscarUsTodosHandler
        extends AbstractRequestHandler<Object, List<UsuarioDto>> {

    @Autowired
    UsuariosRepository usuarios;

    @Override
    public List<UsuarioDto> execute(final Object request) {
        return new ArrayList<UsuarioDto>(Collections2
                .transform(usuarios.findAll(new NoDisableEntitiesSpec<Usuario>()), new Function<Usuario, UsuarioDto>() {
                    @Override
                    public UsuarioDto apply(Usuario r) {
                        r.setClave("");
                        UsuarioDto u = new UsuarioDtoConverter().toDTO(r);
                        return u;
                    }
                }));
    }

}
