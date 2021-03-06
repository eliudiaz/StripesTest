/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.catalogos.areasgeograficas.handlers;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import gt.org.ms.api.requesting.AbstractRequestHandler;
import gt.org.ms.controller.dto.AreaGeograficaDto;
import gt.org.ms.controller.dto.CatalogosRequestDto;
import gt.org.ms.converters.AreaGeograficaConverter;
import gt.org.ms.model.AreaGeografica;
import gt.org.ms.repository.AreasGeografRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author edcracken
 */
@Service
public class AreaGeoBuscarTodosHandler
        extends AbstractRequestHandler<CatalogosRequestDto, List<AreaGeograficaDto>> {

    @Autowired
    AreasGeografRepository repository;
    @Autowired
    AreasGeografSpec spec;

    @Override
    public List<AreaGeograficaDto> execute(CatalogosRequestDto request) {
        return new ArrayList<AreaGeograficaDto>(Collections2
                .transform(repository.findAll(spec.build(request)),
                        new Function<AreaGeografica, AreaGeograficaDto>() {
                    @Override
                    public AreaGeograficaDto apply(AreaGeografica r) {
                        return new AreaGeograficaConverter().toDTO(r);
                    }
                }));
    }

}
