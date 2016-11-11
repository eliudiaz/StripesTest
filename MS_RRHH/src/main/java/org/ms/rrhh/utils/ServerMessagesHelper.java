/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ms.rrhh.utils;

import com.google.gson.Gson;
import org.ms.rrhh.utils.exceptions.ValidationException;

/**
 *
 * @author eliud
 */
public class ServerMessagesHelper {

    public static Exception parseValidationHttpErrorMessage(String r) {
        ValidationException v = new Gson().fromJson(r, ValidationException.class);
        return new Exception(v.getMessage());
    }
}
