/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.accesos.handlers;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import gt.org.isis.api.requesting.AbstractRequestHandler;
import gt.org.isis.api.entities.NoDisableEntitiesSpec;
import gt.org.isis.controller.dto.AccesoDto;
import gt.org.isis.converters.AccesoDtoConverter;
import gt.org.isis.model.Acceso;
import gt.org.isis.repository.AccesosRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author edcracken
 */
@Service
public class BuscarAcTodosHandler extends AbstractRequestHandler<Integer, List<AccesoDto>> {

    @Autowired
    AccesosRepository accesosRepo;
    @Autowired
    AccesosSpec spec;

    @Override
    public List<AccesoDto> execute(final Integer codigoPadre) {
        List accesos = codigoPadre != null ? accesosRepo.findAll(spec.build(codigoPadre))
                : accesosRepo.findAll(new NoDisableEntitiesSpec<Acceso>());
        return new ArrayList<AccesoDto>(Collections2.transform(accesos,
                new Function<Acceso, AccesoDto>() {
            @Override
            public AccesoDto apply(Acceso r) {
                return new AccesoDtoConverter().toDTO(r);
            }
        }));
    }

}
