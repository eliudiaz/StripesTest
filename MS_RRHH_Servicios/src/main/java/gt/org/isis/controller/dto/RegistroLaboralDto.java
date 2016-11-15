/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.dto;

import java.util.List;
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
    private Boolean comisionado;
    @NotNull
    private Integer fkExpectativa;
    private Integer fkComunidadComisionado;
    private String nombreComunidadComisionado;
    @NotNull
    private List<RegistroLaboralPuestoDto> puestos;
    private List<RegistroLaboralDto> historial;

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

    public String getNombreComunidadComisionado() {
        return nombreComunidadComisionado;
    }

    public void setNombreComunidadComisionado(String nombreComunidadComisionado) {
        this.nombreComunidadComisionado = nombreComunidadComisionado;
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

    public Boolean getComisionado() {
        return comisionado;
    }

    public void setComisionado(Boolean comisionado) {
        this.comisionado = comisionado;
    }

    public Integer getFkComunidadComisionado() {
        return fkComunidadComisionado;
    }

    public void setFkComunidadComisionado(Integer fkComunidadComisionado) {
        this.fkComunidadComisionado = fkComunidadComisionado;
    }

}
