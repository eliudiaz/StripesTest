/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.personas.handlers.validations;

import gt.org.isis.api.GenericValidationRequest;
import gt.org.isis.api.ValidationRequestContext;
import static gt.org.isis.api.ValidationsHelper.isNull;
import gt.org.isis.api.misc.exceptions.ExceptionsManager;
import gt.org.isis.controller.dto.ReqModPersonaDto;
import gt.org.isis.repository.PersonasRepository;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author edcracken
 */
@Component
public class ModExistePersonaValidation extends GenericValidationRequest<ReqModPersonaDto> {

    @Autowired
    PersonasRepository repo;

    @Override
    public void validate(ReqModPersonaDto persona, ValidationRequestContext ctx) {
        if (isNull(repo.findOne(persona.getCui()))) {
            throw ExceptionsManager.newValidationException("persona_no_existe",
                    Arrays.asList("cui_persona,CUI no existe!")
                            .toArray(new String[1]));
        }
    }

}
