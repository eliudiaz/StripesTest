/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.usuarios.handlers.validations;

import gt.org.ms.api.requesting.GenericValidationRequest;
import gt.org.ms.api.requesting.ValidationRequestContext;
import static gt.org.ms.api.requesting.ValidationsHelper.isNull;
import gt.org.ms.api.jpa.ManySpecificationANDHandler;
import gt.org.ms.api.jpa.SingleFieldSpecification;
import gt.org.ms.api.global.exceptions.ExceptionsManager;
import gt.org.ms.controller.dto.UsuarioDto;
import gt.org.ms.model.Persona;
import gt.org.ms.model.Usuario;
import gt.org.ms.model.Usuario_;
import gt.org.ms.model.enums.Estado;
import gt.org.ms.repository.PersonasRepository;
import gt.org.ms.repository.UsuariosRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

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

            ArrayList<Specification<Usuario>> ls = new ArrayList<Specification<Usuario>>();
            ls.add(new SingleFieldSpecification<Usuario, Persona>(Usuario_.fkPersona, p));
            ls.add(new SingleFieldSpecification<Usuario, Estado>(Usuario_.estado, Estado.ACTIVO));
            List us = usuarios.findAll(new ManySpecificationANDHandler<Usuario>(ls));
            if (!us.isEmpty()) {
                if (!(us.size() == 1 && ((Usuario) us.iterator().next()).getId().equals(request.getUsuario()))) {
                    throw ExceptionsManager.newValidationException("cui_invalido", "No puede asignar una persona a dos usuarios!");
                }
            }
        }

    }
}
