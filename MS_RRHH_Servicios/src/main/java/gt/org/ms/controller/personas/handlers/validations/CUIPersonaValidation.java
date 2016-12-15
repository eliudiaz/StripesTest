/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.personas.handlers.validations;

import gt.org.ms.api.requesting.GenericValidationRequest;
import gt.org.ms.api.requesting.ValidationRequestContext;
import gt.org.ms.api.misc.exceptions.ExceptionsManager;
import gt.org.ms.controller.dto.PersonaDto;
import java.math.BigInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author edcracken
 * @param <T>
 */
public class CUIPersonaValidation<T extends PersonaDto> extends GenericValidationRequest<T> {

    private static final Logger LOG
            = LoggerFactory.getLogger(CUIPersonaValidation.class);

    @Override
    public void validate(final T persona, ValidationRequestContext ctx) {
        if (persona.getCui().length() != 13) {
            throw ExceptionsManager.newValidationException("cui", "invalid_cui,Longitud de CUI es diferente de 13 digitos!");
        }
        try {
            LOG.info(">> cui >> valid! >> " + new BigInteger(persona.getCui()).toString());
        } catch (NumberFormatException e) {
            throw ExceptionsManager.newValidationException("cui", "invalid_cui,CUI debe ser numerico!");

        }

    }

}
