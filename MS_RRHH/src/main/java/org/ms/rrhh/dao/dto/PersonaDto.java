/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ms.rrhh.dao.dto;

import java.util.Date;
import javax.validation.constraints.NotNull;
import org.ms.rrhh.dao.dto.enums.Estado;
import org.ms.rrhh.dao.dto.enums.EstadoCivil;
import org.ms.rrhh.dao.dto.enums.Sexo;

/**
 *
 * @author edcracken
 */
public class PersonaDto {

    private String cui;
    private String foto;
    @NotNull
    private String primerNombre;
    @NotNull
    private String segundoNombre;
    private String otrosNombres;
    @NotNull
    private String primerApellido;
    @NotNull
    private String segundoApellido;
    private String otrosApellidos;
    private String apellidoCasada;
    @NotNull
    private EstadoCivil estadoCivil;
    @NotNull
    private Sexo sexo;
    @NotNull
    private Integer fkNacionalidad;
    @NotNull
    private String fkProfesion;
    @NotNull
    private String limitacionesFisicas;
    @NotNull
    private Boolean sabeLeer;
    @NotNull
    private Boolean sabeEscribir;
//    @NotNull
    private Date fechaNacimiento;
    private Integer edad;

    @NotNull
    private String nacNoLibro;
    @NotNull
    private String nacNoFolio;
    @NotNull
    private String nacNoPartida;

    @NotNull
    private Integer fkComunidadLinguistica;
    @NotNull
    private String mrz;
    @NotNull
    private String noCedula;
    private Estado estado;
    private Integer fkMunicipioNacimiento;
    private Integer fkMunicipioCedula;
    private Integer fkMunicipioVecindad;
    @NotNull
    private Boolean huellaManoDer;
    @NotNull
    private Boolean huellaManoIzq;
    @NotNull
    private String huellaDedoDer;
    @NotNull
    private String huellaDedoIzq;

    public PersonaDto() {
    }

    public PersonaDto(String cui) {
        this.cui = cui;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getCui() {
        return cui;
    }

    public void setCui(String cui) {
        this.cui = cui;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getOtrosNombres() {
        return otrosNombres;
    }

    public void setOtrosNombres(String otrosNombres) {
        this.otrosNombres = otrosNombres;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getOtrosApellidos() {
        return otrosApellidos;
    }

    public void setOtrosApellidos(String otrosApellidos) {
        this.otrosApellidos = otrosApellidos;
    }

    public String getApellidoCasada() {
        return apellidoCasada;
    }

    public void setApellidoCasada(String apellidoCasada) {
        this.apellidoCasada = apellidoCasada;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Integer getFkNacionalidad() {
        return fkNacionalidad;
    }

    public void setFkNacionalidad(Integer fkNacionalidad) {
        this.fkNacionalidad = fkNacionalidad;
    }

    public String getFkProfesion() {
        return fkProfesion;
    }

    public void setFkProfesion(String fkProfesion) {
        this.fkProfesion = fkProfesion;
    }

    public String getLimitacionesFisicas() {
        return limitacionesFisicas;
    }

    public void setLimitacionesFisicas(String limitacionesFisicas) {
        this.limitacionesFisicas = limitacionesFisicas;
    }

    public Boolean getSabeLeer() {
        return sabeLeer;
    }

    public void setSabeLeer(Boolean sabeLeer) {
        this.sabeLeer = sabeLeer;
    }

    public Boolean getSabeEscribir() {
        return sabeEscribir;
    }

    public void setSabeEscribir(Boolean sabeEscribir) {
        this.sabeEscribir = sabeEscribir;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getFkMunicipioNacimiento() {
        return fkMunicipioNacimiento;
    }

    public void setFkMunicipioNacimiento(Integer fkMunicipioNacimiento) {
        this.fkMunicipioNacimiento = fkMunicipioNacimiento;
    }

    public String getNacNoLibro() {
        return nacNoLibro;
    }

    public void setNacNoLibro(String nacNoLibro) {
        this.nacNoLibro = nacNoLibro;
    }

    public String getNacNoFolio() {
        return nacNoFolio;
    }

    public void setNacNoFolio(String nacNoFolio) {
        this.nacNoFolio = nacNoFolio;
    }

    public String getNacNoPartida() {
        return nacNoPartida;
    }

    public void setNacNoPartida(String nacNoPartida) {
        this.nacNoPartida = nacNoPartida;
    }

    public Integer getFkComunidadLinguistica() {
        return fkComunidadLinguistica;
    }

    public void setFkComunidadLinguistica(Integer fkComunidadLinguistica) {
        this.fkComunidadLinguistica = fkComunidadLinguistica;
    }

    public String getMrz() {
        return mrz;
    }

    public void setMrz(String mrz) {
        this.mrz = mrz;
    }

    public String getNoCedula() {
        return noCedula;
    }

    public void setNoCedula(String noCedula) {
        this.noCedula = noCedula;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Integer getFkMunicipioCedula() {
        return fkMunicipioCedula;
    }

    public void setFkMunicipioCedula(Integer fkMunicipioCedula) {
        this.fkMunicipioCedula = fkMunicipioCedula;
    }

    public Integer getFkMunicipioVecindad() {
        return fkMunicipioVecindad;
    }

    public void setFkMunicipioVecindad(Integer fkMunicipioVecindad) {
        this.fkMunicipioVecindad = fkMunicipioVecindad;
    }

    public Boolean getHuellaManoDer() {
        return huellaManoDer;
    }

    public void setHuellaManoDer(Boolean huellaManoDer) {
        this.huellaManoDer = huellaManoDer;
    }

    public Boolean getHuellaManoIzq() {
        return huellaManoIzq;
    }

    public void setHuellaManoIzq(Boolean huellaManoIzq) {
        this.huellaManoIzq = huellaManoIzq;
    }

    public String getHuellaDedoDer() {
        return huellaDedoDer;
    }

    public void setHuellaDedoDer(String huellaDedoDer) {
        this.huellaDedoDer = huellaDedoDer;
    }

    public String getHuellaDedoIzq() {
        return huellaDedoIzq;
    }

    public void setHuellaDedoIzq(String huellaDedoIzq) {
        this.huellaDedoIzq = huellaDedoIzq;
    }

}
