/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.personas.handlers.validations;

import gt.org.isis.api.GenericValidationRequest;
import gt.org.isis.api.ValidationRequestContext;
import gt.org.isis.api.misc.exceptions.ExceptionsManager;
import gt.org.isis.controller.dto.PersonaDto;
import gt.org.isis.controller.dto.RegistroLaboralDto;
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
