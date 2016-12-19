/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.repository;

import gt.org.ms.model.Idioma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author eliud
 */
@Repository
public interface IdiomaRepository extends JpaRepository<Idioma, Integer>, JpaSpecificationExecutor {

    @Modifying
    @Transactional
    @Query("DELETE FROM Idioma i WHERE  i.fkPersona.cui=:cui")
    public void deleteByPersona(@Param("cui") String cui);
}
