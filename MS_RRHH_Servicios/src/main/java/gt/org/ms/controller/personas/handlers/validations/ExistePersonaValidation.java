/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.personas.handlers.validations;

import gt.org.ms.api.requesting.GenericValidationRequest;
import gt.org.ms.api.requesting.ValidationRequestContext;
import static gt.org.ms.api.requesting.ValidationsHelper.isNull;
import gt.org.ms.api.misc.exceptions.ExceptionsManager;
import gt.org.ms.controller.dto.PersonaDto;
import gt.org.ms.repository.PersonasRepository;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author edcracken
 */
public class ExistePersonaValidation<T extends PersonaDto> extends GenericValidationRequest<T> {

    @Autowired
    PersonasRepository repo;

    @Override
    public void validate(PersonaDto persona, ValidationRequestContext ctx) {
        if (isNull(repo.findOne(persona.getCui()))) {
            throw ExceptionsManager.newValidationException("persona_no_existe",
                    Arrays.asList("cui_persona,CUI no existe!")
                            .toArray(new String[1]));
        }
    }

}
