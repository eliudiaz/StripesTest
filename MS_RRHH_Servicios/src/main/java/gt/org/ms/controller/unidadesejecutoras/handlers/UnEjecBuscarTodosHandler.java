/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.unidadesejecutoras.handlers;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import gt.org.ms.api.requesting.AbstractRequestHandler;
import gt.org.ms.controller.dto.UnidadEjecutoraDto;
import gt.org.ms.converters.UnidadEjecutoraConverter;
import gt.org.ms.model.UnidadEjecutora;
import gt.org.ms.repository.UnidadEjecutoraRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author edcracken
 */
@Service
public class UnEjecBuscarTodosHandler
        extends AbstractRequestHandler<Object, List<UnidadEjecutoraDto>> {

    @Autowired
    UnidadEjecutoraRepository repo;

    @Override
    public List<UnidadEjecutoraDto> execute(final Object request) {
        return new ArrayList<UnidadEjecutoraDto>(Collections2
                .transform(repo.findAll(), new Function<UnidadEjecutora, UnidadEjecutoraDto>() {
                    @Override
                    public UnidadEjecutoraDto apply(UnidadEjecutora r) {
                        return new UnidadEjecutoraConverter().toDTO(r);
                    }
                }));
    }
}
