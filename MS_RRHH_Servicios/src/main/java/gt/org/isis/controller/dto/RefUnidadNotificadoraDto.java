/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.dto;

/**
 *
 * @author eliud
 */
public class RefUnidadNotificadoraDto {

    private Integer fkComunidad;
    private String nombreComunidad;
    private Integer fkLugarEspecifico;
    private String nombreLugarEspecifico;
    private Integer fkDistrito;
    private String nombreDistrito;

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
