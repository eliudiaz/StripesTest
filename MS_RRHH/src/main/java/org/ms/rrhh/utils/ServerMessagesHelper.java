/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ms.rrhh.utils;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.validation.SimpleError;
import org.ms.rrhh.action.exceptions.StripesValidationException;
import org.ms.rrhh.utils.exceptions.ValidationError;
import org.ms.rrhh.utils.exceptions.ValidationException;

/**
 *
 * @author eliud
 */
public class ServerMessagesHelper {

    public static Exception parseValidationHttpErrorMessage(String r) {
        ValidationException v = new Gson().fromJson(r, ValidationException.class);
        List<net.sourceforge.stripes.validation.ValidationError> erros = new ArrayList<net.sourceforge.stripes.validation.ValidationError>();

        for (ValidationError ve : v.getErrors()) {
            erros.add(new SimpleError(ve.getMessage(), new Object[]{}));
        }
        return new StripesValidationException(erros, r);
    }
}
