/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.home.handlers;

import com.google.common.collect.Sets;
import gt.org.ms.api.requesting.AbstractValidationsRequestHandler;
import gt.org.ms.controller.dto.BusquedaAvanzadaDto;
import gt.org.ms.controller.dto.PersonaDto;
import gt.org.ms.converters.PersonaDtoConverter;
import gt.org.ms.model.Persona;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author edcracken
 */
@Service
public class BusquedaAvanzadaHandler
        extends AbstractValidationsRequestHandler<BusquedaAvanzadaDto, List<PersonaDto>> {

    @Autowired
    BusqPersonasByUnidadEjecutoraHandler unidadEjecutoraBusqHandler;
    @Autowired
    BusqPersonasByRenglonHandler personasByRenglonHandler;
    @Autowired
    BusqPersonasByPuestoParamsHandler personasByPuestoParams;

    @Override
    public List<PersonaDto> execute(final BusquedaAvanzadaDto request) {
        List<Persona> personas = new ArrayList();
        personas.addAll(unidadEjecutoraBusqHandler.handle(request));
        personas.addAll(personasByRenglonHandler.handle(request));
        personas.addAll(personasByPuestoParams.handle(request));
        return new ArrayList<PersonaDto>(Sets.newHashSet(new PersonaDtoConverter().toDTO(personas)));
    }

}
