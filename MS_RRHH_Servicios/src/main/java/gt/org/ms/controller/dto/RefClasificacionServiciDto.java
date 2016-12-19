/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.dto;

/**
 *
 * @author eliud
 */
public class RefClasificacionServiciDto {

    private Integer fkAreaServicio;
    private String fkAreaServicioNombre;
    private Integer fkClasificacionServicio;
    private String fkClasificacionServicioNombre;

    public Integer getFkAreaServicio() {
        return fkAreaServicio;
    }

    public void setFkAreaServicio(Integer fkAreaServicio) {
        this.fkAreaServicio = fkAreaServicio;
    }

    public String getFkAreaServicioNombre() {
        return fkAreaServicioNombre;
    }

    public void setFkAreaServicioNombre(String fkAreaServicioNombre) {
        this.fkAreaServicioNombre = fkAreaServicioNombre;
    }

    public Integer getFkClasificacionServicio() {
        return fkClasificacionServicio;
    }

    public void setFkClasificacionServicio(Integer fkClasificacionServicio) {
        this.fkClasificacionServicio = fkClasificacionServicio;
    }

    public String getFkClasificacionServicioNombre() {
        return fkClasificacionServicioNombre;
    }

    public void setFkClasificacionServicioNombre(String fkClasificacionServicioNombre) {
        this.fkClasificacionServicioNombre = fkClasificacionServicioNombre;
    }

}
