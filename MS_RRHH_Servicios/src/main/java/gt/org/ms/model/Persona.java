/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.model;

import gt.org.ms.api.entities.DesactivableEntity;
import gt.org.ms.model.enums.Estado;
import gt.org.ms.model.enums.EstadoCivil;
import gt.org.ms.model.enums.Pueblo;
import gt.org.ms.model.enums.Sexo;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author edcracken
 */
@Entity
@Table(schema = "public", name = "persona")
public class Persona implements Serializable, DesactivableEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private String cui;
    @Basic(optional = false)
    @Column(name = "primer_nombre", nullable = false)
    private String primerNombre;
    @Column(name = "segundo_nombre")
    private String segundoNombre;
    @Column(name = "otros_nombres")
    private String otrosNombres;
    @Basic(optional = false)
    @Column(name = "primer_apellido", nullable = false)
    private String primerApellido;
    @Column(name = "segundo_apellido")
    private String segundoApellido;
    @Column(name = "otros_apellidos")
    private String otrosApellidos;
    @Column(name = "apellido_casada")
    private String apellidoCasada;
    @Column
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_civil")
    private EstadoCivil estadoCivil;
    @Column(name = "edad", nullable = false)
    private Integer edad;
    @Column(name = "fk_nacionalidad", nullable = false)
    private Integer fkNacionalidad;
    @Enumerated(EnumType.STRING)
    @Column(name = "fk_pueblo", length = 50)
    private Pueblo fkPueblo;
    @Column(name = "fk_comunidad_linguistica")
    private Integer fkComunidadLinguistica;
    @Basic(optional = false)
    @Column(name = "fk_profesion", nullable = false)
    private String fkProfesion;
    @Column(name = "limitaciones_fisicas")
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
    @Column
    private String mrz;
    @Column(name = "no_cedula")
    private String noCedula;
    @Column
    @Enumerated(EnumType.STRING)
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
    @Column(name = "fecha_ultimo_cambio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUltimoCambio;
    @Column(name = "ultimo_cambio_por")
    private String ultimoCambioPor;
    @Column(name = "foto")
    private String foto;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "fkPersona", cascade = CascadeType.ALL)
    private Collection<Idioma> idiomaCollection;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "fkPersona", orphanRemoval = true)
    private Collection<HistoricoIdioma> hisIdiomaCollection;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "fkPersona", orphanRemoval = true)
    private Collection<HistoricoPersona> hisPersonaCollection;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "fkPersona", orphanRemoval = true)
    private Collection<HistoricoRegistroAcademico> hisRegAcademicoCollection;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "fkPersona", orphanRemoval = true)
    private Collection<HistoricoRegistroLaboral> hisRegLaboralCollection;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "fkPersona", orphanRemoval = true)
    private Collection<HistoricoLugarResidencia> hisLugarResidenciaCollection;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "fkPersona", cascade = CascadeType.ALL)
    private Collection<RegistroAcademico> registroAcademicoCollection;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "fkPersona", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<EstudioSalud> estudioSaludCollection;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "fkPersona", cascade = CascadeType.ALL)
    private Collection<HistoricoEstudioSalud> hisEstudioSaludCollection;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "fkPersona", cascade = CascadeType.ALL)
    private Collection<RegistroLaboral> registroLaboralCollection;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "fkPersona", cascade = CascadeType.ALL)
    private Collection<LugarResidencia> lugarResidenciaCollection;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "fkPersona", cascade = CascadeType.ALL)
    private Collection<Dpi> dpiCollection;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "fkPersona", orphanRemoval = true)
    private Collection<Usuario> usuariosCollection;

    public Persona() {
    }

    public Collection<Usuario> getUsuariosCollection() {
        return usuariosCollection;
    }

    public void setUsuariosCollection(Collection<Usuario> usuariosCollection) {
        this.usuariosCollection = usuariosCollection;
    }

    public Collection<HistoricoLugarResidencia> getHisLugarResidenciaCollection() {
        return hisLugarResidenciaCollection;
    }

    public void setHisLugarResidenciaCollection(Collection<HistoricoLugarResidencia> hisLugarResidenciaCollection) {
        this.hisLugarResidenciaCollection = hisLugarResidenciaCollection;
    }

    public Collection<HistoricoEstudioSalud> getHisEstudioSaludCollection() {
        return hisEstudioSaludCollection;
    }

    public void setHisEstudioSaludCollection(Collection<HistoricoEstudioSalud> hisEstudioSaludCollection) {
        this.hisEstudioSaludCollection = hisEstudioSaludCollection;
    }

    public Collection<HistoricoIdioma> getHisIdiomaCollection() {
        return hisIdiomaCollection;
    }

    public void setHisIdiomaCollection(Collection<HistoricoIdioma> hisIdiomaCollection) {
        this.hisIdiomaCollection = hisIdiomaCollection;
    }

    public Collection<HistoricoPersona> getHisPersonaCollection() {
        return hisPersonaCollection;
    }

    public void setHisPersonaCollection(Collection<HistoricoPersona> hisPersonaCollection) {
        this.hisPersonaCollection = hisPersonaCollection;
    }

    public Collection<HistoricoRegistroAcademico> getHisRegAcademicoCollection() {
        return hisRegAcademicoCollection;
    }

    public void setHisRegAcademicoCollection(Collection<HistoricoRegistroAcademico> hisRegAcademicoCollection) {
        this.hisRegAcademicoCollection = hisRegAcademicoCollection;
    }

    public Collection<HistoricoRegistroLaboral> getHisRegLaboralCollection() {
        return hisRegLaboralCollection;
    }

    public void setHisRegLaboralCollection(Collection<HistoricoRegistroLaboral> hisRegLaboralCollection) {
        this.hisRegLaboralCollection = hisRegLaboralCollection;
    }

    public Persona(String cui) {
        this.cui = cui;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Date getFechaUltimoCambio() {
        return fechaUltimoCambio;
    }

    public String getUltimoCambioPor() {
        return ultimoCambioPor;
    }

    public void setUltimoCambioPor(String ultimoCambioPor) {
        this.ultimoCambioPor = ultimoCambioPor;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Pueblo getFkPueblo() {
        return fkPueblo;
    }

    public void setFkPueblo(Pueblo fkPueblo) {
        this.fkPueblo = fkPueblo;
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

    public Collection<Idioma> getIdiomaCollection() {
        return idiomaCollection;
    }

    public void setIdiomaCollection(Collection<Idioma> idiomaCollection) {
        this.idiomaCollection = idiomaCollection;
    }

    public Collection<RegistroAcademico> getRegistroAcademicoCollection() {
        return registroAcademicoCollection;
    }

    public void setRegistroAcademicoCollection(Collection<RegistroAcademico> registroAcademicoCollection) {
        this.registroAcademicoCollection = registroAcademicoCollection;
    }

    public Collection<EstudioSalud> getEstudioSaludCollection() {
        return estudioSaludCollection;
    }

    public void setEstudioSaludCollection(Collection<EstudioSalud> estudioSaludCollection) {
        this.estudioSaludCollection = estudioSaludCollection;
    }

    public Collection<RegistroLaboral> getRegistroLaboralCollection() {
        return registroLaboralCollection;
    }

    public void setRegistroLaboralCollection(Collection<RegistroLaboral> registroLaboralCollection) {
        this.registroLaboralCollection = registroLaboralCollection;
    }

    public Collection<LugarResidencia> getLugarResidenciaCollection() {
        return lugarResidenciaCollection;
    }

    public void setLugarResidenciaCollection(Collection<LugarResidencia> lugarResidenciaCollection) {
        this.lugarResidenciaCollection = lugarResidenciaCollection;
    }

    public Collection<Dpi> getDpiCollection() {
        return dpiCollection;
    }

    public void setDpiCollection(Collection<Dpi> dpiCollection) {
        this.dpiCollection = dpiCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cui != null ? cui.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.cui == null && other.cui != null) || (this.cui != null && !this.cui.equals(other.cui))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ms.rrhh.domain.model.Persona[ cui=" + cui + " ]";
    }

    @Override
    public void setFechaUltimoCambio(Date fechaUltimoCambio) {
        System.out.println("no");
    }

    public Persona(String cui, int fkNacionalidad, int edad) {
        this.cui = cui;
        this.fkNacionalidad = fkNacionalidad;
        this.edad = edad;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    @Override
    public Serializable getId() {
        return getCui();
    }

}
