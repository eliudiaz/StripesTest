/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.model;

import gt.org.isis.model.enums.EstadoVariable;
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

/**
 *
 * @author edcracken
 */
@Entity
@Table(name = "registro_academico", schema = "public")
@NamedQueries({
    @NamedQuery(name = "RegistroAcademico.findAll", query = "SELECT r FROM RegistroAcademico r")})
public class RegistroAcademico implements Serializable, CustomEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "ultimo_grado", nullable = false)
    private Integer ultimoGrado;
    @Column(name = "estudia_actualmente")
    private Boolean estudiaActualmente;
    @Column(name = "grado_actual")
    private Integer gradoActual;
    @Column
    @Enumerated(EnumType.STRING)
    private EstadoVariable estado;
    @Basic(optional = false)
    @Column(name = "fecha_creacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "creado_por")
    private String creadoPor;
    @JoinColumn(name = "fk_persona", referencedColumnName = "cui")
    @ManyToOne
    private Persona fkPersona;

    public RegistroAcademico() {
    }

    public RegistroAcademico(Integer id) {
        this.id = id;
    }

    public RegistroAcademico(Integer id, int ultimoGrado, int gradoActual, Date fechaCreacion) {
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

    public Integer getUltimoGrado() {
        return ultimoGrado;
    }

    public void setUltimoGrado(Integer ultimoGrado) {
        this.ultimoGrado = ultimoGrado;
    }

    public Boolean getEstudiaActualmente() {
        return estudiaActualmente;
    }

    public void setEstudiaActualmente(Boolean estudiaActualmente) {
        this.estudiaActualmente = estudiaActualmente;
    }

    public Integer getGradoActual() {
        return gradoActual;
    }

    public void setGradoActual(Integer gradoActual) {
        this.gradoActual = gradoActual;
    }

    public EstadoVariable getEstado() {
        return estado;
    }

    public void setEstado(EstadoVariable estado) {
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
        if (!(object instanceof RegistroAcademico)) {
            return false;
        }
        RegistroAcademico other = (RegistroAcademico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ms.rrhh.domain.model.RegistroAcademico[ id=" + id + " ]";
    }

    @Override
    public void setFechaUltimoCambio(Date fechaUltimoCambio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
