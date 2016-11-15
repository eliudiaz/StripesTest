/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.usuarios.handlers;

import gt.org.isis.api.AbstractRequestHandler;
import gt.org.isis.controller.dto.UsuarioDto;
import gt.org.isis.converters.UsuarioDtoConverter;
import gt.org.isis.model.Usuario;
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
public class BuscarUsHandler extends AbstractRequestHandler<UsuarioDto, UsuarioDto> {

    @Autowired
    UsuariosRepository usuarios;

    @Override
    public UsuarioDto execute(final UsuarioDto request) {
        List<Usuario> ls = usuarios.findAll(new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery cq, CriteriaBuilder cb) {
                return cb.like(root.get("id"), request.getUsuario());
            }
        });
        if (!ls.isEmpty()) {
            Usuario r = ls.get(0);
            r.setClave("");
            return new UsuarioDtoConverter().toDTO(r);
        }
        throw new RuntimeException("No existe");
    }
}
