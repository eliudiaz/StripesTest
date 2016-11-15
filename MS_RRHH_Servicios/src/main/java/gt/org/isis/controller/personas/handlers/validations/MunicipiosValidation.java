/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.personas.handlers.validations;

import gt.org.isis.api.C;
import gt.org.isis.api.GenericValidationRequest;
import gt.org.isis.api.ValidationRequestContext;
import static gt.org.isis.api.ValidationsHelper.isNull;
import gt.org.isis.api.misc.exceptions.ExceptionsManager;
import gt.org.isis.controller.dto.PersonaDto;
import gt.org.isis.model.AreaGeografica;
import gt.org.isis.repository.AreasGeografRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author edcrakcne
 */
//@Component
public class MunicipiosValidation extends GenericValidationRequest<PersonaDto> {

    @Autowired
    AreasGeografRepository areasRepo;

    @Override
    public void validate(PersonaDto persona, ValidationRequestContext ctx) {
        validateMunicipio("municipio_cedula", persona.getFkMunicipioCedula());
        validateMunicipio("municipio_nacimiento", persona.getFkMunicipioNacimiento());
        validateMunicipio("municipio_vecindad", persona.getFkMunicipioVecindad());
    }

    private void validateMunicipio(String campo, Integer codigo) {
        AreaGeografica ag;
        String errNullMunicipio = "%s,Municipio no existe!";
        String errWrongMunicipio = "%s,No es un codigo de municipio valido";
        String cause = "validacion_personas";
        if (isNull(ag = areasRepo.findOne(codigo))) {
            throw ExceptionsManager
                    .newValidationException(cause,
                            new String[]{String.format(errNullMunicipio, campo)});
        }
        if (ag.getCodigoPadre() == null || !ag.getTipo().equalsIgnoreCase(C.CAT_AG_TIPO_MUNICIPIOS)) {
            throw ExceptionsManager.newValidationException(cause,
                    new String[]{String.format(errWrongMunicipio, campo)});
        }
    }

}
