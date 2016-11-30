/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.dto;

import gt.org.isis.model.enums.Estado;
import java.util.Date;
import javax.validation.constraints.NotNull;

/**
 *
 * @author edcracken
 */
public class IdiomaDto {

    private Integer id;
    private Estado estado;
    @NotNull
    private Integer fkIdioma;
    private String nombre;
    private Date fechaCreacion;
    private String creadoPor;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Integer getFkIdioma() {
        return fkIdioma;
    }

    public void setFkIdioma(Integer fkIdioma) {
        this.fkIdioma = fkIdioma;
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

    @Override
    public String toString() {
        return nombre;
    }

}
