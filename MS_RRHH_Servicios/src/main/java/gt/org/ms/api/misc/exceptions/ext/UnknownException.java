/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.api.misc.exceptions.ext;

import static gt.org.ms.api.requesting.ValidationsHelper.isNull;
import gt.org.ms.api.misc.exceptions.BaseException;
import org.springframework.http.HttpStatus;

/**
 *
 * @author edcracken
 */
public class UnknownException extends BaseException {

    public UnknownException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "unknown_error", "Ha ocurrido un error desconocido, por favor intente más tarde");
    }

    public UnknownException(String cause, String description, Throwable er) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, cause,
                description.concat(" >> ").concat(!isNull(er.getMessage()) ? er.getMessage() : "Unknown"));
    }
}
