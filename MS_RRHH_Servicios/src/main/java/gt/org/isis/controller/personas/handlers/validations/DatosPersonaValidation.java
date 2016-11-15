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
import org.joda.time.DateTime;

/**
 *
 * @author edcracken
 * @param <T>
 */
public class DatosPersonaValidation<T extends PersonaDto> extends GenericValidationRequest<T> {

    @Override
    public void validate(final T persona, ValidationRequestContext ctx) {
        ValidationException ex = new ValidationException(new ArrayList<ValidationError>());

        DateTime f = new DateTime(persona.getFechaNacimiento());
        if (f.getYear() >= DateTime.now().getYear()) {
            ex.getErrors().add(new ValidationError("fecha_nacimiento", "Solo se soportan fechas anteriores al año actual!"));
        } else {
            int diff = DateTime.now().getYear() - f.getYear();
            if (diff < 18) {
                ex.getErrors().add(new ValidationError("fecha_nacimiento", "Solo se permiten personas de 18 años en adelante!"));
            }
        }
        if (!ex.getErrors().isEmpty()) {
            throw ex;
        }
    }

}
