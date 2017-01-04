/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.dto;

import javax.validation.constraints.NotNull;

/**
 *
 * @author eliud
 */
public class RefUnidadNotificadoraDto {

    @NotNull
    private Integer fkUnidadEjecutora;
    private String fkUnidadEjecutoraNombre;
    private Integer fkComunidad;
    private String nombreComunidad;
    private Integer fkLugarEspecifico;
    private String nombreLugarEspecifico;
    private Integer fkDistrito;
    private String nombreDistrito;
    private Integer fkComunidad2;
    private String nombreComunidad2;

    public Integer getFkComunidad2() {
        return fkComunidad2;
    }

    public void setFkComunidad2(Integer fkComunidad2) {
        this.fkComunidad2 = fkComunidad2;
    }

    public String getNombreComunidad2() {
        return nombreComunidad2;
    }

    public void setNombreComunidad2(String nombreComunidad2) {
        this.nombreComunidad2 = nombreComunidad2;
    }

    public Integer getFkUnidadEjecutora() {
        return fkUnidadEjecutora;
    }

    public void setFkUnidadEjecutora(Integer fkUnidadEjecutora) {
        this.fkUnidadEjecutora = fkUnidadEjecutora;
    }

    public String getFkUnidadEjecutoraNombre() {
        return fkUnidadEjecutoraNombre;
    }

    public void setFkUnidadEjecutoraNombre(String fkUnidadEjecutoraNombre) {
        this.fkUnidadEjecutoraNombre = fkUnidadEjecutoraNombre;
    }

    public Integer getFkComunidad() {
        return fkComunidad;
    }

    public void setFkComunidad(Integer fkComunidad) {
        this.fkComunidad = fkComunidad;
    }

    public String getNombreComunidad() {
        return nombreComunidad;
    }

    public void setNombreComunidad(String nombreComunidad) {
        this.nombreComunidad = nombreComunidad;
    }

    public Integer getFkLugarEspecifico() {
        return fkLugarEspecifico;
    }

    public void setFkLugarEspecifico(Integer fkLugarEspecifico) {
        this.fkLugarEspecifico = fkLugarEspecifico;
    }

    public String getNombreLugarEspecifico() {
        return nombreLugarEspecifico;
    }

    public void setNombreLugarEspecifico(String nombreLugarEspecifico) {
        this.nombreLugarEspecifico = nombreLugarEspecifico;
    }

    public Integer getFkDistrito() {
        return fkDistrito;
    }

    public void setFkDistrito(Integer fkDistrito) {
        this.fkDistrito = fkDistrito;
    }

    public String getNombreDistrito() {
        return nombreDistrito;
    }

    public void setNombreDistrito(String nombreDistrito) {
        this.nombreDistrito = nombreDistrito;
    }

}
