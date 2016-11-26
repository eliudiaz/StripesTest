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
 * @author edcracken
 */
public class EstudioSaludDto {

    private Integer id;
    @NotNull
    private Integer anioEstudio;
    @NotNull
    private Integer fkEstudioSalud;
    private String nombre;
    private Date fechaCreacion;
    private String creadoPor;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

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

}
