/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.dto;

import gt.org.ms.model.enums.EstadoVariable;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;

/**
 *
 * @author eliud
 */
public class LugarResidenciaDto {

    private Integer id;
    @NotNull
    private Integer fkMunicipio;
    private RefAreaGeograficaDto refLugarResidencia;
    @NotNull
    private String direccion;
    private EstadoVariable estado;
    private Date fechaCreacion;
    private String creadoPor;
    private List<LugarResidenciaDto> historial;

    public RefAreaGeograficaDto getRefLugarResidencia() {
        return refLugarResidencia;
    }

    public void setRefLugarResidencia(RefAreaGeograficaDto refLugarResidencia) {
        this.refLugarResidencia = refLugarResidencia;
    }

    public List<LugarResidenciaDto> getHistorial() {
        return historial;
    }

    public void setHistorial(List<LugarResidenciaDto> historial) {
        this.historial = historial;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFkMunicipio() {
        return fkMunicipio;
    }

    public void setFkMunicipio(Integer fkMunicipio) {
        this.fkMunicipio = fkMunicipio;
    }

    public EstadoVariable getEstado() {
        return estado;
    }

    public void setEstado(EstadoVariable estado) {
        this.estado = estado;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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
