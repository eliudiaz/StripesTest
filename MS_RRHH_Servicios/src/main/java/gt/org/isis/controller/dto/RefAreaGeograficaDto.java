/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.dto;

/**
 *
 * @author eliud
 */
public class RefAreaGeograficaDto {

    private Integer fkPais;
    private String fkPaisNombre;
    private Integer fkDepartamento;
    private String fkDepartamentoNombre;
    private Integer fkMunicio;
    private String fkMunicioNombre;

    public Integer getFkPais() {
        return fkPais;
    }

    public void setFkPais(Integer fkPais) {
        this.fkPais = fkPais;
    }

    public String getFkPaisNombre() {
        return fkPaisNombre;
    }

    public void setFkPaisNombre(String fkPaisNombre) {
        this.fkPaisNombre = fkPaisNombre;
    }

    public Integer getFkDepartamento() {
        return fkDepartamento;
    }

    public void setFkDepartamento(Integer fkDepartamento) {
        this.fkDepartamento = fkDepartamento;
    }

    public String getFkDepartamentoNombre() {
        return fkDepartamentoNombre;
    }

    public void setFkDepartamentoNombre(String fkDepartamentoNombre) {
        this.fkDepartamentoNombre = fkDepartamentoNombre;
    }

    public Integer getFkMunicio() {
        return fkMunicio;
    }

    public void setFkMunicio(Integer fkMunicio) {
        this.fkMunicio = fkMunicio;
    }

    public String getFkMunicioNombre() {
        return fkMunicioNombre;
    }

    public void setFkMunicioNombre(String fkMunicioNombre) {
        this.fkMunicioNombre = fkMunicioNombre;
    }

}
