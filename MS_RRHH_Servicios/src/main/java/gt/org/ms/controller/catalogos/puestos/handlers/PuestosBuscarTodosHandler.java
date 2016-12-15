/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.catalogos.puestos.handlers;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import gt.org.ms.api.requesting.AbstractRequestHandler;
import gt.org.ms.controller.dto.CatalogosRequestDto;
import gt.org.ms.controller.dto.PuestoDto;
import gt.org.ms.converters.PuestosDtoConverter;
import gt.org.ms.model.Puestos;
import gt.org.ms.repository.PuestosRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author edcracken
 */
@Service
public class PuestosBuscarTodosHandler extends AbstractRequestHandler<CatalogosRequestDto, List<PuestoDto>> {

    @Autowired
    PuestosRepository catalogos;
    @Autowired
    PuestosSpec spec;

    @Override
    public List<PuestoDto> execute(CatalogosRequestDto request) {
        return new ArrayList<PuestoDto>(Collections2
                .transform(catalogos.findAll(spec.build(request)),
                        new Function<Puestos, PuestoDto>() {
                    @Override
                    public PuestoDto apply(Puestos r) {
                        return new PuestosDtoConverter().toDTO(r);
                    }
                }));
    }

}
