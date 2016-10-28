/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ms.rrhh.rest.accesos.controllers.handlers;

import org.ms.rrhh.api.AbstractRequestHandler;
import org.ms.rrhh.dao.AccesoDao;
import org.ms.rrhh.domain.model.Acceso;
import org.ms.rrhh.domain.utils.BeansConverter;
import org.ms.rrhh.rest.dto.AccesoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author eliud
 */
@Component
public class CrearAcHandler extends AbstractRequestHandler<AccesoDto, Acceso> {

    @Autowired
    AccesoDao accesos;

    @Override
    public Acceso execute(final AccesoDto request) {
        final Acceso r = new BeansConverter<Acceso, AccesoDto>().toEntity(request);
        accesos.save(r);
        return r;
    }

}