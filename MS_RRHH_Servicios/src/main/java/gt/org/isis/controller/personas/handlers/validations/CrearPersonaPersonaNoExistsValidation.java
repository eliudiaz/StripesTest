/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.personas.handlers.validations;

import gt.org.isis.api.GenericValidationRequest;
import gt.org.isis.api.ValidationRequestContext;
import gt.org.isis.api.misc.exceptions.ExceptionsManager;
import gt.org.isis.controller.dto.RequestPersonaDto;
import gt.org.isis.repository.PersonasRepository;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author edcracken
 */
@Component
public class CrearPersonaPersonaNoExistsValidation extends GenericValidationRequest<RequestPersonaDto> {

    @Autowired
    PersonasRepository repo;

    @Override
    public void validate(final RequestPersonaDto persona, ValidationRequestContext ctx) {
        if (repo.findOne(persona.getCui().trim().toLowerCase()) != null) {
            throw ExceptionsManager.newValidationException("persona_existe",
                    Arrays.asList("cui_persona,Persona con CUI ya existe!")
                            .toArray(new String[1]));
        }
    }

}
