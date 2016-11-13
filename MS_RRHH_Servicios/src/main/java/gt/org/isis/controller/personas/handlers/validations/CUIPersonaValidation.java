/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.personas.handlers.validations;

import gt.org.isis.api.GenericValidationRequest;
import gt.org.isis.api.ValidationRequestContext;
import gt.org.isis.api.misc.exceptions.ext.ValidationError;
import gt.org.isis.api.misc.exceptions.ext.ValidationException;
import gt.org.isis.controller.dto.PersonaDto;
import java.util.ArrayList;

/**
 *
 * @author edcracken
 * @param <T>
 */
public class CUIPersonaValidation<T extends PersonaDto> extends GenericValidationRequest<T> {

    @Override
    public void validate(final T persona, ValidationRequestContext ctx) {
        ValidationException ex = new ValidationException(new ArrayList<ValidationError>());
        if (persona.getCui().length() != 13) {
            ex.getErrors().add(new ValidationError("cui", "CUI invalido"));
        }

        try {
            Integer.valueOf(persona.getCui());
        } catch (NumberFormatException e) {
            ex.getErrors().add(new ValidationError("cui", "CUI no lleva letras"));
        }

        if (!ex.getErrors().isEmpty()) {
            throw ex;
        }
    }

}
