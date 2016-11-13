package gt.org.isis.api.misc.exceptions.ext;

import com.fasterxml.jackson.databind.JsonMappingException;
import gt.org.isis.api.misc.exceptions.BaseException;
import org.springframework.http.HttpStatus;

/**
 * Created by eddcracken on 19/09/16.
 */
public class MalformedJsonException extends BaseException {

    public MalformedJsonException(JsonMappingException ex) {
        super(HttpStatus.BAD_REQUEST, "bad_json_object",
                ex.getMessage()
                        .concat(" >> ")
                        .concat(ex.getPathReference()));
    }
}
