/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.dto;

import java.util.Date;
import javax.validation.constraints.NotNull;

/**
 *
 * @author eliud
 */
public class EstudioSaludDto {

    private Integer id;
    @NotNull
    private Integer anioEstudio;
    @NotNull
    private Integer fkEstudioSalud;
    private Date fechaCreacion;
    private String creadoPor;
    private Date fechaUltimoCambio;
    private String ultimoCambioPor;

    public Integer getFkEstudioSalud() {
        return fkEstudioSalud;
    }

    public void setFkEstudioSalud(Integer fkEstudioSalud) {
        this.fkEstudioSalud = fkEstudioSalud;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAnioEstudio() {
        return anioEstudio;
    }

    public void setAnioEstudio(Integer anioEstudio) {
        this.anioEstudio = anioEstudio;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }

    public Date getFechaUltimoCambio() {
        return fechaUltimoCambio;
    }

    public void setFechaUltimoCambio(Date fechaUltimoCambio) {
        this.fechaUltimoCambio = fechaUltimoCambio;
    }

    public String getUltimoCambioPor() {
        return ultimoCambioPor;
    }

    public void setUltimoCambioPor(String ultimoCambioPor) {
        this.ultimoCambioPor = ultimoCambioPor;
    }

}
