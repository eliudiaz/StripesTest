/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.api.requesting;

/**
 *
 * @author edcracken
 * @param <T>
 */
public interface IValidationRequest<T> {

    public abstract void checkType(T parameter, ValidationRequestContext ctx);

    public abstract void validate(T parameter, ValidationRequestContext ctx);
}
