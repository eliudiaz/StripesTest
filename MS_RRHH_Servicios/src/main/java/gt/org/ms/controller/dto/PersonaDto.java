/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.dto;

import gt.org.ms.model.enums.Estado;
import gt.org.ms.model.enums.EstadoCivil;
import gt.org.ms.model.enums.Pueblo;
import gt.org.ms.model.enums.Sexo;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

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
    private String segundoApellido;
    private String otrosApellidos;
    private String apellidoCasada;
    @NotNull
    private EstadoCivil estadoCivil;
    @NotNull
    private Sexo sexo;
    private Integer fkNacionalidad;
    private String fkNacionalidadNombre;
    @NotNull
    private String fkProfesion;
    private String limitacionesFisicas;
    @NotNull
    private boolean sabeLeer;
    @NotNull
    private boolean sabeEscribir;
    private Date fechaNacimiento;
    private String fechaNacimientoTexto;
    private Integer fkMunicipioNacimiento;
    private String fkMunicipioNacimientoNombre;
    private Integer edad;
    @NotNull
    private String nacNoLibro;
    @NotNull
    private String nacNoFolio;
    @NotNull
    private String nacNoPartida;
    @NotNull
    private Pueblo fkPueblo;
    private Integer fkComunidadLinguistica;
    private String fkComunidadLinguisticaNombre;
    @NotNull
    private String mrz;
    private String noCedula;
    private Integer fkMunicipioCedula;
    private String fkMunicipioCedulaNombre;
    private Estado estado;
    private Integer fkMunicipioVecindad;
    private String fkMunicipioVecindadNombre;
    @NotNull
    private String huellaManoDer;
    @NotNull
    private String huellaManoIzq;
    @Valid
    @NotNull
    private RegistroLaboralDto registroLaboral;
    @NotNull
    @Valid
    private LugarResidenciaDto lugarResidencia;
    @NotNull
    @Valid
    private RegistroAcademicoDto registroAcademico;
    private DpiDto dpi;
    @NotNull
    @NotEmpty
    @Valid
    private List<IdiomaDto> idiomas;
    private List<EstudioSaludDto> estudiosSalud;
    private String observaciones;
    private boolean lector = true;

    public String getFkComunidadLinguisticaNombre() {
        return fkComunidadLinguisticaNombre;
    }

    public void setFkComunidadLinguisticaNombre(String fkComunidadLinguisticaNombre) {
        this.fkComunidadLinguisticaNombre = fkComunidadLinguisticaNombre;
    }

    public String getFkMunicipioVecindadNombre() {
        return fkMunicipioVecindadNombre;
    }

    public void setFkMunicipioVecindadNombre(String fkMunicipioVecindadNombre) {
        this.fkMunicipioVecindadNombre = fkMunicipioVecindadNombre;
    }

    public boolean isLector() {
        return lector;
    }

    public void setLector(boolean lector) {
        this.lector = lector;
    }

    public String getFkMunicipioNacimientoNombre() {
        return fkMunicipioNacimientoNombre;
    }

    public void setFkMunicipioNacimientoNombre(String fkMunicipioNacimientoNombre) {
        this.fkMunicipioNacimientoNombre = fkMunicipioNacimientoNombre;
    }

    public String getFkMunicipioCedulaNombre() {
        return fkMunicipioCedulaNombre;
    }

    public void setFkMunicipioCedulaNombre(String fkMunicipioCedulaNombre) {
        this.fkMunicipioCedulaNombre = fkMunicipioCedulaNombre;
    }

    public String getFkNacionalidadNombre() {
        return fkNacionalidadNombre;
    }

    public void setFkNacionalidadNombre(String fkNacionalidadNombre) {
        this.fkNacionalidadNombre = fkNacionalidadNombre;
    }

    public String getFechaNacimientoTexto() {
        return fechaNacimientoTexto;
    }

    public void setFechaNacimientoTexto(String fechaNacimientoTexto) {
        this.fechaNacimientoTexto = fechaNacimientoTexto;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

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

    public RegistroLaboralDto getRegistroLaboral() {
        return registroLaboral;
    }

    public void setRegistroLaboral(RegistroLaboralDto registroLaboral) {
        this.registroLaboral = registroLaboral;
    }

    public LugarResidenciaDto getLugarResidencia() {
        return lugarResidencia;
    }

    public void setLugarResidencia(LugarResidenciaDto lugarResidencia) {
        this.lugarResidencia = lugarResidencia;
    }

    public RegistroAcademicoDto getRegistroAcademico() {
        return registroAcademico;
    }

    public void setRegistroAcademico(RegistroAcademicoDto registroAcademico) {
        this.registroAcademico = registroAcademico;
    }

    public DpiDto getDpi() {
        return dpi;
    }

    public void setDpi(DpiDto dpi) {
        this.dpi = dpi;
    }

    public List<IdiomaDto> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<IdiomaDto> idiomas) {
        this.idiomas = idiomas;
    }

    public List<EstudioSaludDto> getEstudiosSalud() {
        return estudiosSalud;
    }

    public void setEstudiosSalud(List<EstudioSaludDto> estudiosSalud) {
        this.estudiosSalud = estudiosSalud;
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

    public boolean isSabeLeer() {
        return sabeLeer;
    }

    public void setSabeLeer(boolean sabeLeer) {
        this.sabeLeer = sabeLeer;
    }

    public boolean isSabeEscribir() {
        return sabeEscribir;
    }

    public void setSabeEscribir(boolean sabeEscribir) {
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

    public Pueblo getFkPueblo() {
        return fkPueblo;
    }

    public void setFkPueblo(Pueblo fkPueblo) {
        this.fkPueblo = fkPueblo;
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

    public String getHuellaManoDer() {
        return huellaManoDer;
    }

    public void setHuellaManoDer(String huellaManoDer) {
        this.huellaManoDer = huellaManoDer;
    }

    public String getHuellaManoIzq() {
        return huellaManoIzq;
    }

    public void setHuellaManoIzq(String huellaManoIzq) {
        this.huellaManoIzq = huellaManoIzq;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.cui != null ? this.cui.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PersonaDto other = (PersonaDto) obj;
        return !((this.cui == null) ? (other.cui != null) : !this.cui.equals(other.cui));
    }

}
