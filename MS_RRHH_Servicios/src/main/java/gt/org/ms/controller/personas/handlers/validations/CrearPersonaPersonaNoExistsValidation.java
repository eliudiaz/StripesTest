/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.personas.handlers.validations;

import gt.org.ms.api.requesting.GenericValidationRequest;
import gt.org.ms.api.requesting.ValidationRequestContext;
import gt.org.ms.api.global.exceptions.ExceptionsManager;
import gt.org.ms.controller.dto.RequestCreatePersonaDto;
import gt.org.ms.repository.PersonasRepository;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author edcracken
 */
@Component
public class CrearPersonaPersonaNoExistsValidation extends GenericValidationRequest<RequestCreatePersonaDto> {

    @Autowired
    PersonasRepository repo;

    @Override
    public void validate(final RequestCreatePersonaDto persona, ValidationRequestContext ctx) {
        if (repo.findOne(persona.getCui().trim().toLowerCase()) != null) {
            throw ExceptionsManager.newValidationException("persona_existe",
                    Arrays.asList("cui_persona,Persona con CUI ya existe!")
                            .toArray(new String[1]));
        }
    }

}
