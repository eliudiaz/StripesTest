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
 * @author daniel
 */
public class AttributesRequestInvalidException extends BaseException {

    public AttributesRequestInvalidException() {
        //super(HttpStatus.BAD_REQUEST, "attributes_request_invalid", "Some attributes are empty or invalids");
        super(HttpStatus.BAD_REQUEST, "attributes_request_invalid", "Algunos atributos están vacíos o son inválidos");
    }

}
