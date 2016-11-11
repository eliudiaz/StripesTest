/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ms.rrhh.action.exceptions;

import java.util.List;
import net.sourceforge.stripes.validation.ValidationError;

/**
 *
 * @author eliud
 */
public class StripesValidationException extends Exception {

    public StripesValidationException(List<ValidationError> errors, String message) {
        super(message);
        this.errors = errors;
    }

    private List<ValidationError> errors;

    public List<ValidationError> getErrors() {
        return errors;
    }

}
