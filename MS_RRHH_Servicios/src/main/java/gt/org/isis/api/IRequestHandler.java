/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.api;

/**
 *
 * @author edcracken
 */
public interface IRequestHandler<T, Q> {

    public void before(T request);

    public Q handle(T request);

    public abstract Q execute(T request);

    public void after(T request, Q response);
}
