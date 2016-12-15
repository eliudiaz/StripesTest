/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.usuarios.handlers;

import gt.org.ms.api.jpa.DoDesactivarHandler;
import gt.org.ms.model.Usuario;
import gt.org.isis.model.Usuario_;
import java.io.Serializable;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author edcracken
 */
@Service
public class DesactivarUsHandler extends DoDesactivarHandler<Usuario> {

    @Autowired
    public DesactivarUsHandler(@Qualifier("usuariosRepository") JpaRepository<Usuario, Serializable> repo) {
        super(repo);
    }

    @Override
    public Boolean execute(final Usuario e) {
        setCustomSpec(new Specification<Usuario>() {
            @Override
            public Predicate toPredicate(Root<Usuario> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                return cb.like(root.get(Usuario_.id), e.getId().trim().toLowerCase());
            }
        });
        return super.execute(e);
    }

}
