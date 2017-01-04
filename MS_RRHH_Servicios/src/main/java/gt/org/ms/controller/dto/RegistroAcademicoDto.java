/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.dto;

import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;

/**
 *
 * @author eliud
 */
public class RegistroAcademicoDto {

    private Integer id;
    @NotNull
    private Integer ultimoGrado;
    private String nombreUltimoGrado;
    private Integer carreraUltimoGrado;
    private String carreraUltimoGradoNombre;
    private Integer nivelUltimoGrado;
    private String nivelUltimoGradoNombre;
    @NotNull
    private boolean estudiaActualmente;
    private Integer gradoActual;
    private String nombreGradoActual;
    private Integer nivelGradoActual;
    private String nivelGradoActualNombre;
    private Integer carreraGradoActual;
    private String carreraGradoActualNombre;
    private Date fechaCreacion;
    private String creadoPor;
    private List<RegistroAcademicoDto> historial;

    public String getCarreraGradoActualNombre() {
        return carreraGradoActualNombre;
    }

    public void setCarreraGradoActualNombre(String carreraGradoActualNombre) {
        this.carreraGradoActualNombre = carreraGradoActualNombre;
    }

    public Integer getCarreraUltimoGrado() {
        return carreraUltimoGrado;
    }

    public void setCarreraUltimoGrado(Integer carreraUltimoGrado) {
        this.carreraUltimoGrado = carreraUltimoGrado;
    }

    public String getCarreraUltimoGradoNombre() {
        return carreraUltimoGradoNombre;
    }

    public void setCarreraUltimoGradoNombre(String carreraUltimoGradoNombre) {
        this.carreraUltimoGradoNombre = carreraUltimoGradoNombre;
    }

    public Integer getCarreraGradoActual() {
        return carreraGradoActual;
    }

    public void setCarreraGradoActual(Integer carreraGradoActual) {
        this.carreraGradoActual = carreraGradoActual;
    }

    public String getNivelUltimoGradoNombre() {
        return nivelUltimoGradoNombre;
    }

    public void setNivelUltimoGradoNombre(String nivelUltimoGradoNombre) {
        this.nivelUltimoGradoNombre = nivelUltimoGradoNombre;
    }

    public String getNivelGradoActualNombre() {
        return nivelGradoActualNombre;
    }

    public void setNivelGradoActualNombre(String nivelGradoActualNombre) {
        this.nivelGradoActualNombre = nivelGradoActualNombre;
    }

    public Integer getNivelUltimoGrado() {
        return nivelUltimoGrado;
    }

    public void setNivelUltimoGrado(Integer nivelUltimoGrado) {
        this.nivelUltimoGrado = nivelUltimoGrado;
    }

    public Integer getNivelGradoActual() {
        return nivelGradoActual;
    }

    public void setNivelGradoActual(Integer nivelGradoActual) {
        this.nivelGradoActual = nivelGradoActual;
    }

    public String getNombreUltimoGrado() {
        return nombreUltimoGrado;
    }

    public void setNombreUltimoGrado(String nombreUltimoGrado) {
        this.nombreUltimoGrado = nombreUltimoGrado;
    }

    public String getNombreGradoActual() {
        return nombreGradoActual;
    }

    public void setNombreGradoActual(String nombreGradoActual) {
        this.nombreGradoActual = nombreGradoActual;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUltimoGrado() {
        return ultimoGrado;
    }

    public void setUltimoGrado(Integer ultimoGrado) {
        this.ultimoGrado = ultimoGrado;
    }

    public boolean isEstudiaActualmente() {
        return estudiaActualmente;
    }

    public void setEstudiaActualmente(boolean estudiaActualmente) {
        this.estudiaActualmente = estudiaActualmente;
    }

    public Integer getGradoActual() {
        return gradoActual;
    }

    public void setGradoActual(Integer gradoActual) {
        this.gradoActual = gradoActual;
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

    public List<RegistroAcademicoDto> getHistorial() {
        return historial;
    }

    public void setHistorial(List<RegistroAcademicoDto> historial) {
        this.historial = historial;
    }

}
