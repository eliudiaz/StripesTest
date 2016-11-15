/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ms.rrhh.utils.exceptions;

/**
 *
 * @author fernando
 */
public class ValidationError {

    public ValidationError() {
    }

    public ValidationError(String path, String message) {
        this.path = path;
        this.message = message;
    }

    private String path;

    private String message;

    /**
     * Get the value of message
     *
     * @return the value of message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Get the value of path
     *
     * @return the value of path
     */
    public String getPath() {
        return path;
    }


}
