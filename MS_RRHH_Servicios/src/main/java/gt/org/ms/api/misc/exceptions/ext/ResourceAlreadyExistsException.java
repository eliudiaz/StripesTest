/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.api.misc.exceptions.ext;

import gt.org.ms.api.misc.exceptions.BaseException;
import org.springframework.http.HttpStatus;

/**
 *
 * @author edcracken
 */
public class ResourceAlreadyExistsException extends BaseException {

    public ResourceAlreadyExistsException() {
        super(HttpStatus.CONFLICT, "resource_already_exists", "El recurso ya existe");
    }

}
