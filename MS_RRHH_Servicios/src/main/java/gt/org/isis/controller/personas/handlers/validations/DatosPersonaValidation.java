/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.personas.handlers.validations;

import gt.org.isis.api.requesting.GenericValidationRequest;
import gt.org.isis.api.requesting.ValidationRequestContext;
import static gt.org.isis.api.requesting.ValidationsHelper.isNull;
import gt.org.isis.api.misc.exceptions.ExceptionsManager;
import gt.org.isis.api.misc.exceptions.ext.ValidationError;
import gt.org.isis.api.misc.exceptions.ext.ValidationException;
import gt.org.isis.api.utils.EntitiesHelper;
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
        if (!persona.isLector()) {
            if (isNull(persona.getFkNacionalidad())) {
                throw ExceptionsManager.newValidationException("nacionalidad", "Nacionadlidad es requerida!");
            }
            if (isNull(persona.getFkMunicipioNacimiento())) {
                throw ExceptionsManager.newValidationException("invalid_lugar_nacimiento",
                        new String[]{"lugar_nac_requerido,Lugar de nacimiento es requerido!"});
            }
            if (isNull(persona.getFechaNacimiento())) {
                throw ExceptionsManager.newValidationException("invalid_fecha_nacimiento",
                        new String[]{"feha_nac_requerido,Fecha de nacimiento es requerido!"});
            }
            if (!isNull(persona.getFechaNacimiento())) {
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
        } else {
            if (isNull(persona.getFkMunicipioNacimientoNombre())) {
                throw ExceptionsManager.newValidationException("invalid_lugar_nacimiento",
                        new String[]{"lugar_nac_requerido,Lugar de nacimiento es requerido!"});
            }
            if (isNull(persona.getFkNacionalidadNombre())) {
                throw ExceptionsManager.newValidationException("invalid_lugar_nacimiento",
                        new String[]{"lugar_nac_requerido,Lugar de nacimiento es requerido!"});
            }
            if (isNull(persona.getFechaNacimientoTexto())) {
                throw ExceptionsManager.newValidationException("invalid_fecha_nacimiento",
                        new String[]{"feha_nac_requerido,Fecha de nacimiento es requerido!"});
            } else {
                try {
                    EntitiesHelper.parseFechaDPI(persona.getFechaNacimientoTexto());
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                    throw ExceptionsManager.newValidationException("invalid_fecha_nacimiento",
                            new String[]{"feha_nac_invalido,Fecha de nacimiento es invalida!"});
                }
            }
        }

    }

}
