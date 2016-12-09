/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.api.requesting;

import gt.org.isis.api.entities.CustomEntity;
import com.google.gson.Gson;
import gt.org.isis.api.entities.SessionEntity;
import gt.org.isis.controller.dto.SecureRequestDto;
import gt.org.isis.api.utils.EntitiesHelper;
import gt.org.isis.controller.dto.LoggedUserDto;
import gt.org.isis.model.Usuario;
import gt.org.isis.model.Usuario_;
import gt.org.isis.repository.UsuariosRepository;
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

    @Override
    public void before(T request) {
        System.out.println(">> in >> " + new Gson().toJson(request));
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
