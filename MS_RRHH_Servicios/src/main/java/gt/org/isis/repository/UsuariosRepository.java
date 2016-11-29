/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.repository;

import gt.org.isis.model.Usuario;
import org.springframework.stereotype.Repository;

/**
 *
 * @author eliud
 */
@Repository("usuariosRepository")
public interface UsuariosRepository extends CustomRepository<Usuario> {

}
