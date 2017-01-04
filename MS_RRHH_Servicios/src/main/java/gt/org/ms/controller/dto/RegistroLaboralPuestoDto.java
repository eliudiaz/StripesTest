/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.dto;

import gt.org.ms.model.enums.TipoPuesto;
import javax.validation.constraints.NotNull;

/**
 *
 * @author eliu
 */
public class RegistroLaboralPuestoDto {

    @NotNull
    private Integer fkPuestoFuncional;
    private String nombrePuestoFuncional;
    @NotNull
    private TipoPuesto tipo;
    @NotNull
    private Integer fkPuestoNominal;
    private String nombrePuestoNominal;
    private Integer fkPuestoNominalRenglon;
    private String nombrePuestoNominalRenglon;
    @NotNull
    private Integer fkComunidad; //unidad notificadora hijo
    private RefUnidadNotificadoraDto refUnidadNotificadora;
    @NotNull
    private Integer fkClasificacionServicio;
    private RefClasificacionServiciDto refClasificacionServicio;

    public RefClasificacionServiciDto getRefClasificacionServicio() {
        return refClasificacionServicio;
    }

    public void setRefClasificacionServicio(RefClasificacionServiciDto refClasificacionServicio) {
        this.refClasificacionServicio = refClasificacionServicio;
    }

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
