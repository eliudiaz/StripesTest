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
public class AccesoDto {

    private Integer id;
    @NotNull
    private String valor;
    @NotNull
    private String tipo;
    private String estado;
    private Date fechaCreacion;
    private String creadoPor;
    private Integer codigoPadre;

    public AccesoDto() {
    }

    public AccesoDto(Integer id) {
        this.id = id;
    }

    public Integer getCodigoPadre() {
        return codigoPadre;
    }

    public void setCodigoPadre(Integer codigoPadre) {
        this.codigoPadre = codigoPadre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
