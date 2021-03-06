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
public class DpiDto {

    @NotNull
    private String noSerie;
    private Date fechaEmision;
    private Date fechaVencimiento;
    private String fechaEmisionTexto;
    private String fechaVencimientoTexto;
    private EstadoVariable estado;
    private Date fechaCreacion;
    private String creadoPor;
    private List<DpiDto> historial;

    public String getFechaEmisionTexto() {
        return fechaEmisionTexto;
    }

    public void setFechaEmisionTexto(String fechaEmisionTexto) {
        this.fechaEmisionTexto = fechaEmisionTexto;
    }

    public String getFechaVencimientoTexto() {
        return fechaVencimientoTexto;
    }

    public void setFechaVencimientoTexto(String fechaVencimientoTexto) {
        this.fechaVencimientoTexto = fechaVencimientoTexto;
    }

    public List<DpiDto> getHistorial() {
        return historial;
    }

    public void setHistorial(List<DpiDto> historial) {
        this.historial = historial;
    }

    public String getNoSerie() {
        return noSerie;
    }

    public void setNoSerie(String noSerie) {
        this.noSerie = noSerie;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public EstadoVariable getEstado() {
        return estado;
    }

    public void setEstado(EstadoVariable estado) {
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
