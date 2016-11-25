/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.personas.handlers;

import gt.org.isis.api.AbstractValidationsRequestHandler;
import gt.org.isis.controller.dto.PersonaDto;

/**
 *
 * @author eliud
 */
public abstract class PersonasBaseHandler<T extends PersonaDto, Q> extends AbstractValidationsRequestHandler<T, Q> {

    @Override
    public abstract Q execute(T request);

}
