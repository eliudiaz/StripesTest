/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.repository;

import gt.org.isis.model.UsuarioRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author eliud
 */
@Repository
public interface UsuarioRolesRepository extends JpaRepository<UsuarioRoles, Integer>, JpaSpecificationExecutor {

}
