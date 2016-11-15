/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.repository;

import gt.org.isis.model.RegistroLaboral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author eliud
 */
@Repository
public interface RegistroLaboralRepository
        extends JpaRepository<RegistroLaboral, Integer>, JpaSpecificationExecutor {

    @Modifying
    @Query("update RegistroLaboral r set r.estado='HISTORICO' where r.fkPersona.cui=?1")
    public void archivarRegitro(String cui);
}
