/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.usuarios.handlers.validations;

import gt.org.isis.api.GenericValidationRequest;
import gt.org.isis.api.ValidationRequestContext;
import static gt.org.isis.api.ValidationsHelper.isNull;
import gt.org.isis.api.jpa.SingleFieldSpecification;
import gt.org.isis.api.misc.exceptions.ExceptionsManager;
import gt.org.isis.controller.dto.UsuarioDto;
import gt.org.isis.model.Persona;
import gt.org.isis.model.Usuario;
import gt.org.isis.model.Usuario_;
import gt.org.isis.repository.PersonasRepository;
import gt.org.isis.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author edcracken
 */
public class GenUsNoDuplicadosPersonaValidation<T extends UsuarioDto> extends GenericValidationRequest<T> {

    @Autowired
    UsuariosRepository usuarios;
    @Autowired
    PersonasRepository personas;

    @Override
    public void validate(T request, ValidationRequestContext ctx) {

        if (!request.isRoot() && (isNull(request.getCui()) || request.getCui().isEmpty())) {
            throw ExceptionsManager
                    .newValidationException("cui_invalido",
                            "cui,El usuario debe tener un CUI de persona valido!");
        }

        if (!isNull(request.getCui()) && !request.getCui().isEmpty()) {
            Persona p = personas.findOne(request.getCui());
            if (isNull(p)) {
                throw ExceptionsManager.newValidationException("cui_invalido", "Cui es invalido!");
            }
            if (!usuarios.findAll(new SingleFieldSpecification<Usuario, Persona>(Usuario_.fkPersona,
                    p)).isEmpty()) {
                throw ExceptionsManager.newValidationException("cui_invalido", "No puede asignar una persona a dos usuarios!");
            }
        }
    }

}
