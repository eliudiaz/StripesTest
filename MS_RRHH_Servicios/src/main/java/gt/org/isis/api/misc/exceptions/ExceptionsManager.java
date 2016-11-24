/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.api.misc.exceptions;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import gt.org.isis.api.misc.exceptions.ext.NotFoundException;
import gt.org.isis.api.misc.exceptions.ext.ValidationError;
import gt.org.isis.api.misc.exceptions.ext.ValidationException;
import java.util.Arrays;

/**
 *
 * @author edcracken
 */
public class ExceptionsManager {

    public static RuntimeException newValidationException(String cause, String[] errors) {
        return new ValidationException(Lists.transform(Arrays.asList(errors),
                new Function<String, ValidationError>() {
            @Override
            public ValidationError apply(String f) {
                return splitError(f);
            }
        }));
    }

    public static RuntimeException newValidationException(String cause, String error) {
        return new ValidationException(Lists.transform(Arrays.asList(new String[]{error}),
                new Function<String, ValidationError>() {
            @Override
            public ValidationError apply(String f) {
                return splitError(f);
            }
        }));
    }

    protected static ValidationError splitError(String error) {
        String[] e = error.split(",");
        return new ValidationError(e[0], e.length > 1 ? e[1] : error);
    }

    public static RuntimeException newNotFound() {
        return new NotFoundException();
    }

}
