/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.catalogos.generales.handlers;

import gt.org.ms.api.jpa.DoSaveEntityHandler;
import gt.org.ms.model.Catalogos;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author edcracken
 */
@Service
public class ActualizarCatalogoGeneralHandler extends DoSaveEntityHandler<Catalogos> {

    @Autowired
    public ActualizarCatalogoGeneralHandler(@Qualifier("catalogosRepository") JpaRepository<Catalogos, Serializable> repo) {
        super(repo);
    }

}
