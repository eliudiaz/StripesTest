/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ms.rrhh.utils.exceptions;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;

/**
 *
 * @author fernando
 */
public class ValidationException extends BaseException {

    private List<ValidationError> errors;

    /**
     * Default Constructor empty parameters
     */
    public ValidationException() {
        this(new ArrayList<ValidationError>());
    }

    /**
     * Default constructor when error list is passed
     *
     * @param errors
     */
    public ValidationException(List<ValidationError> errors) {
        //super(HttpStatus.UNPROCESSABLE_ENTITY,"validation_error", "There is a validation issue with your request see [errors] for more info");
        super(HttpStatus.UNPROCESSABLE_ENTITY, "validation_error", "Hay un problema de validación con su solicitud, ver [errores] para obtener más información.");
        this.errors = errors;
    }

    /**
     * Get the value of errors
     *
     * @return the value of errors
     */
    public List<ValidationError> getErrors() {
        return errors;
    }

}
