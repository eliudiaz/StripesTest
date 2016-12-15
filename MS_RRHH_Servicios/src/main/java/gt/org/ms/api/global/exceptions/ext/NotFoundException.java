/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.api.global.exceptions.ext;

import gt.org.ms.api.global.exceptions.BaseException;
import org.springframework.http.HttpStatus;

/**
 *
 * @author edcracken
 */
public class NotFoundException extends BaseException {

    public NotFoundException() {
        super(HttpStatus.NOT_FOUND, "resource_not_found", "El recurso especificado no existe.");
    }

}
