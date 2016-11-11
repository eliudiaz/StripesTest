/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.api;

import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author eliud
 */
public abstract class AbstractTrxRequestHandler<T, Q> extends AbstractRequestHandler<T, Q> {

    @Transactional
    @Override
    public Q handle(T request) {
        return super.handle(request);
    }

}
