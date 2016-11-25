/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author eliud
 */
@Entity
@Table(name = "historico_registro_laboral", catalog = "rrhh", schema = "public")
public class HistoricoRegistroLaboral implements Serializable, CustomEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "anio_ingreso")
    private Integer anioIngreso;
    @Column(name = "fk_expectativa")
    private Integer fkExpectativa;
    @Column(name = "comisionado")
    private Boolean comisionado;
    @Column(name = "fk_comunidad_comisionado")
    private Integer fkComunidadComisionado;
    @Column
    private String observaciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_creacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "creado_por")
    private String creadoPor;
    @JoinColumn(name = "fk_persona", referencedColumnName = "cui", nullable = false)
    @ManyToOne(optional = false)
    private Persona fkPersona;
    @OneToMany(mappedBy = "fkRegistroLaboral")
    private Collection<HistoricoPuesto> historicoPuestoCollection;

    public HistoricoRegistroLaboral() {
    }

    public HistoricoRegistroLaboral(Integer id) {
        this.id = id;
    }

    public HistoricoRegistroLaboral(Integer id, Date fechaCreacion) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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

    public Integer getFkExpectativa() {
        return fkExpectativa;
    }

    public void setFkExpectativa(Integer fkExpectativa) {
        this.fkExpectativa = fkExpectativa;
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

    public Collection<HistoricoPuesto> getHistoricoPuestoCollection() {
        return historicoPuestoCollection;
    }

    public void setHistoricoPuestoCollection(Collection<HistoricoPuesto> historicoPuestoCollection) {
        this.historicoPuestoCollection = historicoPuestoCollection;
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
        if (!(object instanceof HistoricoRegistroLaboral)) {
            return false;
        }
        HistoricoRegistroLaboral other = (HistoricoRegistroLaboral) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.org.isis.model.HistoricoRegistroLaboral[ id=" + id + " ]";
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
