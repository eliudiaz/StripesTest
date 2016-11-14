/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.dto;

import gt.org.isis.model.enums.TipoPuesto;

/**
 *
 * @author eliud
 */
public class RegistroLaboralPuestoDto {

    private Integer fkPuestoFuncional;
    private String nombrePuestoFuncional;
    private TipoPuesto tipo;
    private Integer fkPuestoNominal;
    private String nombrePuestoNominal;
    private Integer fkPuestoNominalRenglon;
    private String nombrePuestoNominalRenglon;
    private Integer fkComunidad;
    private RefUnidadNotificadoraDto refUnidadNotificadora;
    private Integer fkClasificacionServicio;
    private String nombreClasificacionServicio;

    public RefUnidadNotificadoraDto getRefUnidadNotificadora() {
        return refUnidadNotificadora;
    }

    public void setRefUnidadNotificadora(RefUnidadNotificadoraDto refUnidadNotificadora) {
        this.refUnidadNotificadora = refUnidadNotificadora;
    }

    public Integer getFkPuestoNominalRenglon() {
        return fkPuestoNominalRenglon;
    }

    public void setFkPuestoNominalRenglon(Integer fkPuestoNominalRenglon) {
        this.fkPuestoNominalRenglon = fkPuestoNominalRenglon;
    }

    public String getNombrePuestoNominalRenglon() {
        return nombrePuestoNominalRenglon;
    }

    public void setNombrePuestoNominalRenglon(String nombrePuestoNominalRenglon) {
        this.nombrePuestoNominalRenglon = nombrePuestoNominalRenglon;
    }

    public String getNombrePuestoFuncional() {
        return nombrePuestoFuncional;
    }

    public void setNombrePuestoFuncional(String nombrePuestoFuncional) {
        this.nombrePuestoFuncional = nombrePuestoFuncional;
    }

    public String getNombrePuestoNominal() {
        return nombrePuestoNominal;
    }

    public void setNombrePuestoNominal(String nombrePuestoNominal) {
        this.nombrePuestoNominal = nombrePuestoNominal;
    }

    public String getNombreClasificacionServicio() {
        return nombreClasificacionServicio;
    }

    public void setNombreClasificacionServicio(String nombreClasificacionServicio) {
        this.nombreClasificacionServicio = nombreClasificacionServicio;
    }

    public Integer getFkPuestoFuncional() {
        return fkPuestoFuncional;
    }

    public void setFkPuestoFuncional(Integer fkPuestoFuncional) {
        this.fkPuestoFuncional = fkPuestoFuncional;
    }

    public TipoPuesto getTipo() {
        return tipo;
    }

    public void setTipo(TipoPuesto tipo) {
        this.tipo = tipo;
    }

    public Integer getFkPuestoNominal() {
        return fkPuestoNominal;
    }

    public void setFkPuestoNominal(Integer fkPuestoNominal) {
        this.fkPuestoNominal = fkPuestoNominal;
    }

    public Integer getFkComunidad() {
        return fkComunidad;
    }

    public void setFkComunidad(Integer fkComunidad) {
        this.fkComunidad = fkComunidad;
    }

    public Integer getFkClasificacionServicio() {
        return fkClasificacionServicio;
    }

    public void setFkClasificacionServicio(Integer fkClasificacionServicio) {
        this.fkClasificacionServicio = fkClasificacionServicio;
    }

}
