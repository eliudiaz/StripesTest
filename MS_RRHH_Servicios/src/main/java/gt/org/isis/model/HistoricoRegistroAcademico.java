/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.model;

import gt.org.isis.model.enums.Estado;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author eliud
 */
@Entity
@Table(name = "historico_registro_academico", catalog = "rrhh", schema = "public")
@NamedQueries({
    @NamedQuery(name = "HistoricoRegistroAcademico.findAll", query = "SELECT h FROM HistoricoRegistroAcademico h")})
public class HistoricoRegistroAcademico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ultimo_grado", nullable = false)
    private int ultimoGrado;
    @Column(name = "estudia_actualmente")
    private Boolean estudiaActualmente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "grado_actual", nullable = false)
    private int gradoActual;
    @Size(max = 50)
    @Column(name = "estado", length = 50)
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_creacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Size(max = 50)
    @Column(name = "creado_por", length = 50)
    private String creadoPor;
    @JoinColumn(name = "fk_persona", referencedColumnName = "cui")
    @ManyToOne
    private Persona fkPersona;

    public HistoricoRegistroAcademico() {
    }

    public HistoricoRegistroAcademico(Integer id) {
        this.id = id;
    }

    public HistoricoRegistroAcademico(Integer id, int ultimoGrado, int gradoActual, Date fechaCreacion) {
        this.id = id;
        this.ultimoGrado = ultimoGrado;
        this.gradoActual = gradoActual;
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUltimoGrado() {
        return ultimoGrado;
    }

    public void setUltimoGrado(int ultimoGrado) {
        this.ultimoGrado = ultimoGrado;
    }

    public Boolean getEstudiaActualmente() {
        return estudiaActualmente;
    }

    public void setEstudiaActualmente(Boolean estudiaActualmente) {
        this.estudiaActualmente = estudiaActualmente;
    }

    public int getGradoActual() {
        return gradoActual;
    }

    public void setGradoActual(int gradoActual) {
        this.gradoActual = gradoActual;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
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
        if (!(object instanceof HistoricoRegistroAcademico)) {
            return false;
        }
        HistoricoRegistroAcademico other = (HistoricoRegistroAcademico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.org.isis.model.HistoricoRegistroAcademico[ id=" + id + " ]";
    }

}