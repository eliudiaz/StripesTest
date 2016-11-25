/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.api;

import com.google.gson.Gson;
import gt.org.isis.controller.dto.SecureRequestDto;
import gt.org.isis.model.CustomEntity;
import gt.org.isis.model.utils.EntitiesHelper;

/**
 *
 * @author edcracken
 * @param <T>
 * @param <Q>
 */
public abstract class AbstractRequestHandler<T, Q> extends SecureRequestDto implements IRequestHandler<T, Q> {

    @Override
    public void after(T request, Q response) {
    }

    @Override
    public Q handle(T request) {
        before(request);
        Q response = execute(request);
        after(request, response);
        return response;
    }

    @Override
    public void before(T request) {
        System.out.println(">> in >> " + new Gson().toJson(request));
    }

    public void setUpdateInfo(CustomEntity entity) {
        try {
            EntitiesHelper.setDateUpdatedInfo(entity);
            entity.setUltimoCambioPor(getLoggedUser().getUserName());
        } catch (UnsupportedOperationException ex) {
        }
    }

    public void setCreateInfo(CustomEntity entity) {
        try {
            EntitiesHelper.setDateCreatedInfo(entity);
            entity.setCreadoPor(getLoggedUser().getUserName());
        } catch (UnsupportedOperationException ex) {
            entity.getClass().getName();
        }
    }
}
