/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.dto;

import gt.org.ms.api.entities.SessionEntity;

/**
 *
 * @author edcracken
 */
public class RequestCreatePersonaDto extends PersonaDto implements SessionEntity {

    private String sesion;

    public String getSesion() {
        return sesion;
    }

    public void setSesion(String sesion) {
        this.sesion = sesion;
    }

}
