/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.repository;

import gt.org.ms.model.RegistroLaboral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author eliud
 */
@Repository
public interface RegistroLaboralRepository
        extends JpaRepository<RegistroLaboral, Integer>, JpaSpecificationExecutor {

}
