/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.personas.handlers;

import gt.org.ms.api.requesting.AbstractRequestHandler;
import gt.org.ms.api.entities.NoDisableEntitiesSpec;
import gt.org.ms.controller.dto.PersonaDto;
import gt.org.ms.converters.PersonaDtoConverter;
import gt.org.ms.model.Persona;
import gt.org.ms.repository.PersonasRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author eliud
 */
@Service
public class PersonaBuscarTodosHandler extends AbstractRequestHandler<Object, List<PersonaDto>> {

    @Autowired
    PersonasRepository personas;

    @Override
    public List<PersonaDto> execute(Object request) {
        return new PersonaDtoConverter().toDTO(personas.findAll(new NoDisableEntitiesSpec<Persona>()));
    }

}
