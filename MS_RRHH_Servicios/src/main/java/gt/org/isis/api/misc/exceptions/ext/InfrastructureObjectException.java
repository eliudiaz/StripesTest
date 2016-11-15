/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.api.misc.exceptions.ext;

import gt.org.isis.api.misc.exceptions.BaseException;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Fernando Segura
 */
public class InfrastructureObjectException extends BaseException {

    public InfrastructureObjectException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "infrastructure", message);
    }

}
