/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.api;

import gt.org.isis.api.misc.GlobalExceptionHandler;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author eliud
 * @param <T>
 * @param <Q>
 */
public abstract class AbstractValidationsRequestHandler<T, Q> extends AbstractRequestHandler<T, Q> {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Autowired(required = false)
    private List<IValidationRequest<T>> validations;

    @Override
    public void before(T r) {
        super.before(r);
        if (validations != null) {
            LOG.info("validaciones a implementar: " + validations.size());
            ValidationRequestContext ctx = new ValidationRequestContext();
            for (IValidationRequest v : validations) {
                v.validate(r, ctx);
            }
        }
    }

}
