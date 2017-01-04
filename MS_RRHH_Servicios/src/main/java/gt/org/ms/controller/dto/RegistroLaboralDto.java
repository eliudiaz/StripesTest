/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.dto;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 *
 * @author eliud
 */
public class RegistroLaboralDto {

    private Integer id;
    @NotNull
    private Integer anioIngreso;
    @NotNull
    private boolean comisionado;
    @NotNull
    private Integer fkExpectativa;
    private String fkExpectativaNombre;
    private Integer fkComunidadComisionado;
    private RefUnidadNotificadoraDto refUnidadNotificadoraComisionado;
    private String observaciones;
    @NotNull
    @Valid
    private List<RegistroLaboralPuestoDto> puestos;
    private List<RegistroLaboralDto> historial;

    public RefUnidadNotificadoraDto getRefUnidadNotificadoraComisionado() {
        return refUnidadNotificadoraComisionado;
    }

    public void setRefUnidadNotificadoraComisionado(RefUnidadNotificadoraDto refUnidadNotificadoraComisionado) {
        this.refUnidadNotificadoraComisionado = refUnidadNotificadoraComisionado;
    }

    public String getFkExpectativaNombre() {
        return fkExpectativaNombre;
    }

    public void setFkExpectativaNombre(String fkExpectativaNombre) {
        this.fkExpectativaNombre = fkExpectativaNombre;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getFkExpectativa() {
        return fkExpectativa;
    }

    public void setFkExpectativa(Integer fkExpectativa) {
        this.fkExpectativa = fkExpectativa;
    }

    public List<RegistroLaboralPuestoDto> getPuestos() {
        return puestos;
    }

    public void setPuestos(List<RegistroLaboralPuestoDto> puestos) {
        this.puestos = puestos;
    }

    public List<RegistroLaboralDto> getHistorial() {
        return historial;
    }

    public void setHistorial(List<RegistroLaboralDto> historial) {
        this.historial = historial;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAnioIngreso() {
        return anioIngreso;
    }

    public void setAnioIngreso(Integer anioIngreso) {
        this.anioIngreso = anioIngreso;
    }

    public boolean isComisionado() {
        return comisionado;
    }

    public void setComisionado(boolean comisionado) {
        this.comisionado = comisionado;
    }

    public Integer getFkComunidadComisionado() {
        return fkComunidadComisionado;
    }

    public void setFkComunidadComisionado(Integer fkComunidadComisionado) {
        this.fkComunidadComisionado = fkComunidadComisionado;
    }

}
