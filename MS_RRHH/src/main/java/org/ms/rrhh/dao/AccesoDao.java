/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ms.rrhh.dao;

import org.ms.rrhh.api.dao.impl.CrudRepositoryImpl;
import org.ms.rrhh.domain.model.Acceso;
import org.springframework.stereotype.Repository;

/**
 *
 * @author eliud
 */
@Repository("accesosDao")
public class AccesoDao extends CrudRepositoryImpl<Acceso> {

}
