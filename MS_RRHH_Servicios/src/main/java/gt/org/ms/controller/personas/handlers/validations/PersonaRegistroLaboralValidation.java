/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.personas.handlers.validations;

import gt.org.ms.api.requesting.GenericValidationRequest;
import gt.org.ms.api.requesting.ValidationRequestContext;
import gt.org.ms.api.global.exceptions.ExceptionsManager;
import gt.org.ms.controller.dto.PersonaDto;
import gt.org.ms.controller.dto.RegistroLaboralDto;
import org.joda.time.DateTime;

/**
 *
 * @author edcracken
 */
public class PersonaRegistroLaboralValidation<T extends PersonaDto> extends GenericValidationRequest<T> {

    @Override
    public void validate(T persona, ValidationRequestContext ctx) {
        RegistroLaboralDto rl = persona.getRegistroLaboral();
        if (rl.getAnioIngreso() > DateTime.now().getYear()) {
            throw ExceptionsManager.newValidationException("registro_laboral",
                    "anio_ingreso,El año de ingreso no puede ser superior al actual!");
        }
    }

}
