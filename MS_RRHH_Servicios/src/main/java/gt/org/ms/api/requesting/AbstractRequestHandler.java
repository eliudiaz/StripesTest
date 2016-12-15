/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.api.requesting;

import gt.org.ms.api.entities.CustomEntity;
import gt.org.ms.api.entities.SessionEntity;
import gt.org.ms.api.global.exceptions.ExceptionsManager;
import gt.org.ms.controller.dto.SecureRequestDto;
import gt.org.ms.api.utils.EntitiesHelper;
import gt.org.ms.controller.dto.LoggedUserDto;
import gt.org.ms.model.Usuario;
import gt.org.isis.model.Usuario_;
import gt.org.ms.repository.UsuariosRepository;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author edcracken
 * @param <T>
 * @param <Q>
 */
public abstract class AbstractRequestHandler<T, Q> extends SecureRequestDto implements IRequestHandler<T, Q> {

    @Autowired
    UsuariosRepository usuarios;

    @Override
    public void after(T request, Q response) {
    }

    @Override
    public void before(T request) {
        if (request instanceof SessionEntity) {
            final String sesion = ((SessionEntity) request).getSesion();
            List<Usuario> r = usuarios.findAll(new Specification<Usuario>() {
                @Override
                public Predicate toPredicate(Root<Usuario> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                    return cb.equal(root.get(Usuario_.sesion), sesion);
                }
            });
            if (!r.isEmpty()) {
                setLoggedUser(new LoggedUserDto(r.iterator().next().getId()));
            } else {
                throw ExceptionsManager.newValidationException("sesion_invalida",
                        "Para realizar esta accion se requiere que el usuario inicie sesion valida!");
            }
        }
    }

    @Override
    public Q handle(T request) {
        before(request);
        Q response = execute(request);
        after(request, response);
        return response;
    }

    public void setUpdateInfo(CustomEntity entity) {
        try {
            EntitiesHelper.setDateUpdatedInfo(entity);
            entity.setUltimoCambioPor(getLoggedUser().getUserName());
        } catch (UnsupportedOperationException ex) {
        }
    }

    public void setCreateInfo(CustomEntity entity) {
        try {
            EntitiesHelper.setDateCreatedInfo(entity);
            entity.setCreadoPor(getLoggedUser().getUserName());
        } catch (UnsupportedOperationException ex) {
            entity.getClass().getName();
        }
    }
}
