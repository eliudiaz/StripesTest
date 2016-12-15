/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.catalogos.unidadesnotificadoras.handlers;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import gt.org.ms.api.requesting.AbstractRequestHandler;
import gt.org.ms.controller.dto.CatalogoDto;
import gt.org.ms.controller.dto.CatalogosRequestDto;
import gt.org.ms.controller.dto.UnidadNotificadoraDto;
import gt.org.ms.converters.UnidadNotificadoraConverter;
import gt.org.ms.model.UnidadNotificadora;
import gt.org.ms.repository.UnidadNotificadoraRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author edcracken
 */
@Service
public class UnidNotifBuscarTodosHandler
        extends AbstractRequestHandler<CatalogosRequestDto, List<UnidadNotificadoraDto>> {

    @Autowired
    UnidadNotificadoraRepository repository;
    @Autowired
    UnidadesNotifSpec spec;

    @Override
    public List<UnidadNotificadoraDto> execute(CatalogosRequestDto request) {
        return new ArrayList<UnidadNotificadoraDto>(Collections2
                .transform(repository.findAll(spec.build(request)),
                        new Function<UnidadNotificadora, CatalogoDto>() {
                    @Override
                    public CatalogoDto apply(UnidadNotificadora r) {
                        return new UnidadNotificadoraConverter().toDTO(r);
                    }
                }));
    }

}
