/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.repository;

import gt.org.isis.model.Persona;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author eliud
 */
@Repository
public interface PersonasRepository extends JpaRepository<Persona, String>, JpaSpecificationExecutor<Persona> {

    @Query("select p from Persona f where f.cui like :nCui")
    public List<Persona> findByCui(@Param("nCui") String cui);

}
