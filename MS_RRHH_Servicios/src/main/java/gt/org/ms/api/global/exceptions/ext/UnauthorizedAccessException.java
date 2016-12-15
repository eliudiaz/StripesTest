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
 * @author Fernando Segura
 */
public class UnauthorizedAccessException extends BaseException {

    public UnauthorizedAccessException() {
        super(HttpStatus.FORBIDDEN, "unauthorized_access", "No tiene permisos para acceder a este recurso");
    }

}
