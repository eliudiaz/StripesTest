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
 * @author edcracken
 */
public class AuthenticationRequiredException extends BaseException {

    public AuthenticationRequiredException() {
        super(HttpStatus.UNAUTHORIZED, "authentication_required", "El servidor fallo al autenticar la solicitud. Asegúrese que el valor Authorization del encabezado esta formado correctamente, incluyendo la firma");
    }

}
