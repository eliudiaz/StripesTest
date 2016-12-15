package gt.org.ms.api.misc.exceptions.ext;

import gt.org.ms.api.misc.exceptions.BaseException;
import org.springframework.http.HttpStatus;

/**
 * Created by eddcracken on 19/09/16.
 */
public class PersistenceException extends BaseException {

    public PersistenceException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "diagnosis_save", "Ocurrio un error al almacenar en base de datos.");
    }
}
