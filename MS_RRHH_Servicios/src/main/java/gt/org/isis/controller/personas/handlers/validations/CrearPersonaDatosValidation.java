/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.personas.handlers.validations;

import gt.org.isis.api.ValidationRequestContext;
import gt.org.isis.api.misc.exceptions.ExceptionsManager;
import gt.org.isis.controller.dto.RequestPersonaDto;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

/**
 *
 * @author eliud
 */
@Component
public class CrearPersonaDatosValidation extends DatosPersonaValidation<RequestPersonaDto> {

    private Date parseFechaNacTexto(String text) {
        try {
            SimpleDateFormat sd = new SimpleDateFormat("ddMMMyyyy");
            return sd.parse(text);
        } catch (ParseException ex) {
            ex.printStackTrace(System.err);
            return new DateTime().plusYears(-18).toDate();
        }
    }

    @Override
    public void validate(RequestPersonaDto persona, ValidationRequestContext ctx) {

        if (persona.getFechaNacimiento() == null && persona.getFechaNacimientoTexto() == null) {
            throw ExceptionsManager.newValidationException("invalid_fecha_nacimiento",
                    new String[]{"fecha_nac_requerido,Fecha nacimiento es requerido!"});
        } else if (persona.getFechaNacimientoTexto() != null) {
            Date parseFechaNacTexto = parseFechaNacTexto(persona.getFechaNacimientoTexto());
            Calendar c = Calendar.getInstance();
            c.setTime(parseFechaNacTexto);
            if (DateTime.now().getYear() < c.get(Calendar.YEAR)) {
                throw ExceptionsManager.newValidationException("invalid_fecha_nacimiento",
                        new String[]{"fecha_nac_invalida,Fecha nacimiento debe ser menor a fecha actual!"});
            }
        }
        super.validate(persona, ctx);
    }

}
