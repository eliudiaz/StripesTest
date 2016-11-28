/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.home.handlers;

import gt.org.isis.api.AbstractRequestHandler;
import gt.org.isis.controller.dto.PersonaDto;
import gt.org.isis.controller.dto.PersonaRowsFileDto;
import gt.org.isis.controller.personas.handlers.PersonaBusquedaSimpleHandler;
import gt.org.isis.converters.PersonaRowsFileDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ecracken
 */
@Service
public class PersonaCompletarDatosHandler extends AbstractRequestHandler<PersonaDto, PersonaRowsFileDto> {

    @Autowired
    PersonaBusquedaSimpleHandler handler;

    @Override
    public PersonaRowsFileDto execute(PersonaDto request) {
        return new PersonaRowsFileDtoConverter().toEntity(handler.handle(request));
    }

}
