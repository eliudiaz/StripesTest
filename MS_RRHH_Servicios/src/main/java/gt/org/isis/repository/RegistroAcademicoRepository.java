/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.repository;

import gt.org.isis.model.RegistroAcademico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author eliud
 */
@Repository
public interface RegistroAcademicoRepository
        extends JpaRepository<RegistroAcademico, Integer>, JpaSpecificationExecutor {

    @Query("update RegistroAcademico r set r.estado='HISTORICO' where r.fkPersona.cui=?1")
    public void archivarRegitro(String cui);
}
