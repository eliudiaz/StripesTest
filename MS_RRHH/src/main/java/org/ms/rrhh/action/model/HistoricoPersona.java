/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ms.rrhh.domain.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author edcracken
 */
@Entity
@Table(name = "historico_persona",   schema = "public")
@NamedQueries({
    @NamedQuery(name = "HistoricoPersona.findAll", query = "SELECT h FROM HistoricoPersona h")})
public class HistoricoPersona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "primer_nombre", nullable = false, length = 100)
    private String primerNombre;
    @Column(name = "segundo_nombre", length = 100)
    private String segundoNombre;
    @Column(name = "otros_nombres", length = 2147483647)
    private String otrosNombres;
    @Basic(optional = false)
    @Column(name = "primer_apellido", nullable = false, length = 100)
    private String primerApellido;
    @Column(name = "segundo_apellido", length = 100)
    private String segundoApellido;
    @Column(name = "otros_apellidos", length = 2147483647)
    private String otrosApellidos;
    @Column(name = "apellido_casada", length = 100)
    private String apellidoCasada;
    @Column(name = "estado_civil", length = 50)
    private String estadoCivil;
    @Column(length = 50)
    private String sexo;
    @Basic(optional = false)
    @Column(name = "fk_nacionalidad", nullable = false)
    private int fkNacionalidad;
    @Basic(optional = false)
    @Column(name = "fk_profesion", nullable = false)
    private int fkProfesion;
    @Column(name = "limitaciones_fisicas", length = 2147483647)
    private String limitacionesFisicas;
    @Column(name = "sabe_leer")
    private Boolean sabeLeer;
    @Column(name = "sabe_escribir")
    private Boolean sabeEscribir;
    @Basic(optional = false)
    @Column(name = "fecha_nacimiento", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNacimiento;
    @Column(name = "fk_municipio_nacimiento")
    private Integer fkMunicipioNacimiento;
    @Column(name = "nac_no_libro", length = 50)
    private String nacNoLibro;
    @Column(name = "nac_no_folio", length = 50)
    private String nacNoFolio;
    @Column(name = "nac_no_partida", length = 50)
    private String nacNoPartida;
    @Column(name = "fk_pueblo", length = 50)
    private String fkPueblo;
    @Column(name = "fk_comunidad_linguistica", length = 50)
    private String fkComunidadLinguistica;
    @Column(length = 2147483647)
    private String mrz;
    @Column(name = "no_cedula", length = 50)
    private String noCedula;
    @Column(length = 50)
    private String estado;
    @Column(name = "fk_municipio_cedula")
    private Integer fkMunicipioCedula;
    @Column(name = "fk_municipio_vecindad")
    private Integer fkMunicipioVecindad;
    @Column(name = "huella_mano_der")
    private Boolean huellaManoDer;
    @Column(name = "huella_mano_izq")
    private Boolean huellaManoIzq;
    @Column(name = "huella_dedo_der", length = 50)
    private String huellaDedoDer;
    @Column(name = "huella_dedo_izq", length = 50)
    private String huellaDedoIzq;
    @Basic(optional = false)
    @Column(name = "fecha_creacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Basic(optional = false)
    @Column(name = "creado_por", nullable = false, length = 50)
    private String creadoPor;
    @JoinColumn(name = "fk_persona", referencedColumnName = "cui")
    @ManyToOne
    private Persona fkPersona;

    public HistoricoPersona() {
    }

    public HistoricoPersona(Integer id) {
        this.id = id;
    }

    public HistoricoPersona(Integer id, String primerNombre, String primerApellido, int fkNacionalidad, int fkProfesion, Date fechaNacimiento, Date fechaCreacion, String creadoPor) {
        this.id = id;
        this.primerNombre = primerNombre;
        this.primerApellido = primerApellido;
        this.fkNacionalidad = fkNacionalidad;
        this.fkProfesion = fkProfesion;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaCreacion = fechaCreacion;
        this.creadoPor = creadoPor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getFkNacionalidad() {
        return fkNacionalidad;
    }

    public void setFkNacionalidad(int fkNacionalidad) {
        this.fkNacionalidad = fkNacionalidad;
    }

    public int getFkProfesion() {
        return fkProfesion;
    }

    public void setFkProfesion(int fkProfesion) {
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

    public String getFkPueblo() {
        return fkPueblo;
    }

    public void setFkPueblo(String fkPueblo) {
        this.fkPueblo = fkPueblo;
    }

    public String getFkComunidadLinguistica() {
        return fkComunidadLinguistica;
    }

    public void setFkComunidadLinguistica(String fkComunidadLinguistica) {
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
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

    public Persona getFkPersona() {
        return fkPersona;
    }

    public void setFkPersona(Persona fkPersona) {
        this.fkPersona = fkPersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoricoPersona)) {
            return false;
        }
        HistoricoPersona other = (HistoricoPersona) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ms.rrhh.domain.model.HistoricoPersona[ id=" + id + " ]";
    }
    
}
