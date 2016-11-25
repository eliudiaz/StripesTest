/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.model;

import gt.org.isis.model.enums.Estado;
import gt.org.isis.model.enums.EstadoCivil;
import gt.org.isis.model.enums.Pueblo;
import gt.org.isis.model.enums.Sexo;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author edcracken
 */
@Entity
@Table(name = "historico_persona", schema = "public")
public class HistoricoPersona implements Serializable, CustomEntity {

    @Basic(optional = false)
    @NotNull
    @Column(name = "fk_nacionalidad", nullable = false)
    private Integer fkNacionalidad;
    @Enumerated(EnumType.STRING)
    @Column(name = "fk_pueblo", length = 50)
    private Pueblo fkPueblo;

    @Column(name = "fk_comunidad_linguistica", length = 50)
    private Integer fkComunidadLinguistica;
    @Column(name = "edad", nullable = false)
    private Integer edad;

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
    @Enumerated(EnumType.STRING)
    @Column
    private Sexo sexo;
    @Basic(optional = false)
    @Column(name = "fk_profesion", nullable = false)
    private String fkProfesion;
    @Column(name = "limitaciones_fisicas", length = 2147483647)
    private String limitacionesFisicas;
    @Column(name = "sabe_leer")
    private boolean sabeLeer;
    @Column(name = "sabe_escribir")
    private boolean sabeEscribir;
    @Basic(optional = false)
    @Column(name = "fecha_nacimiento", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNacimiento;
    @Column(name = "fk_municipio_nacimiento")
    private Integer fkMunicipioNacimiento;
    @Column(name = "nac_no_libro")
    private String nacNoLibro;
    @Column(name = "nac_no_folio")
    private String nacNoFolio;
    @Column(name = "nac_no_partida")
    private String nacNoPartida;
    @Column(name = "mrz")
    private String mrz;
    @Column(name = "no_cedula")
    private String noCedula;
    @Column
    private Estado estado;
    @Column(name = "fk_municipio_cedula")
    private Integer fkMunicipioCedula;
    @Column(name = "fk_municipio_vecindad")
    private Integer fkMunicipioVecindad;
    @Column(name = "huella_mano_der")
    private String huellaManoDer;
    @Column(name = "huella_mano_izq")
    private String huellaManoIzq;
    @Basic(optional = false)
    @Column(name = "fecha_creacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Basic(optional = false)
    @Column(name = "creado_por", nullable = false)
    private String creadoPor;
    @JoinColumn(name = "fk_persona", referencedColumnName = "cui")
    @ManyToOne
    private Persona fkPersona;
    @Column(name = "foto")
    private String foto;
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_civil")
    private EstadoCivil estadoCivil;

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

    public Integer getFkNacionalidad() {
        return fkNacionalidad;
    }

    public void setFkNacionalidad(Integer fkNacionalidad) {
        this.fkNacionalidad = fkNacionalidad;
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

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public HistoricoPersona() {
    }

    public HistoricoPersona(Integer id) {
        this.id = id;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Pueblo getFkPueblo() {
        return fkPueblo;
    }

    public void setFkPueblo(Pueblo fkPueblo) {
        this.fkPueblo = fkPueblo;
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
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

    @Override
    public void setFechaUltimoCambio(Date fechaUltimoCambio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUltimoCambioPor(String ultimoCambioPor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
