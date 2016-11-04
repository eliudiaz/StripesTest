/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.api;

import java.lang.reflect.ParameterizedType;

/**
 *
 * @author eliud
 * @param <T>
 */
public abstract class GenericValidationRequest<T> implements IValidationRequest<T> {

    @Override
    public void checkType(T parameter, ValidationRequestContext ctx) {
        if (parameter.getClass().isInstance(getClazz())) {
            validate(parameter, ctx);
        }
    }

    protected Class<T> getClazz() {
        return (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
