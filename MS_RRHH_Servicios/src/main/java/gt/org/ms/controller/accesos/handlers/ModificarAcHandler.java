/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.accesos.handlers;

import gt.org.ms.api.requesting.AbstractRequestHandler;
import gt.org.ms.controller.dto.AccesoDto;
import gt.org.ms.model.Acceso;
import gt.org.ms.model.enums.Estado;
import gt.org.ms.api.utils.EntitiesHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import gt.org.ms.repository.AccesosRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author edcracken
 */
@Service
public class ModificarAcHandler extends AbstractRequestHandler<AccesoDto, Acceso> {

    @Autowired
    AccesosRepository accesos;

    @Override
    public Acceso execute(final AccesoDto request) {
        Acceso origin = accesos.findOne(request.getId());
        BeanUtils.copyProperties(request, origin);
        EntitiesHelper.setDateUpdatedInfo(origin);
        origin.setEstado(Estado.ACTIVO);
        accesos.save(origin);
        return origin;
    }

}
