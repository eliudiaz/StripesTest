/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.repository;

import gt.org.ms.model.UnidadNotificadora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ecracken
 */
@Repository
public interface UnidadNotificadoraRepository
        extends JpaRepository<UnidadNotificadora, Integer>, JpaSpecificationExecutor {

}
