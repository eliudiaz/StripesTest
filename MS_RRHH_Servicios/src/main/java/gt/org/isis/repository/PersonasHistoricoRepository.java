/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.repository;

import gt.org.isis.model.HistoricoPersona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author eliud
 */
@Repository
public interface PersonasHistoricoRepository extends JpaRepository<HistoricoPersona, String> {

}