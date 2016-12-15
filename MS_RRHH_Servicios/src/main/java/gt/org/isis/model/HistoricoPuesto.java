/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.model;

import gt.org.isis.api.entities.CustomEntity;
import gt.org.isis.model.enums.TipoPuesto;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
 * @author eliud
 */
@Entity
@Table(name = "historico_puesto", schema = "public")
public class HistoricoPuesto implements Serializable, CustomEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "tipo")
    @Enumerated(EnumType.STRING)
    private TipoPuesto tipo;
    @Column(name = "fk_puesto_nominal")
    private Integer fkPuestoNominal;
    @Column(name = "fk_comunidad")
    private Integer fkComunidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_creacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "creado_por", length = 50)
    private String creadoPor;

    @Column(name = "fk_puesto_funcional")
    private Integer fkPuestoFuncional;
    @Column(name = "fk_clasificacin_servicio")
    private Integer fkClasificacinServicio;
    @JoinColumn(name = "fk_registro_laboral", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private HistoricoRegistroLaboral fkRegistroLaboral;

    public HistoricoPuesto() {
    }

    public HistoricoPuesto(Integer id) {
        this.id = id;
    }

    public HistoricoPuesto(Integer id, Date fechaCreacion) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoPuesto getTipo() {
        return tipo;
    }

    public void setTipo(TipoPuesto tipo) {
        this.tipo = tipo;
    }

    public Integer getFkPuestoNominal() {
        return fkPuestoNominal;
    }

    public void setFkPuestoNominal(Integer fkPuestoNominal) {
        this.fkPuestoNominal = fkPuestoNominal;
    }

    public Integer getFkComunidad() {
        return fkComunidad;
    }

    public void setFkComunidad(Integer fkComunidad) {
        this.fkComunidad = fkComunidad;
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

    public Integer getFkPuestoFuncional() {
        return fkPuestoFuncional;
    }

    public void setFkPuestoFuncional(Integer fkPuestoFuncional) {
        this.fkPuestoFuncional = fkPuestoFuncional;
    }

    public Integer getFkClasificacinServicio() {
        return fkClasificacinServicio;
    }

    public void setFkClasificacinServicio(Integer fkClasificacinServicio) {
        this.fkClasificacinServicio = fkClasificacinServicio;
    }

    public HistoricoRegistroLaboral getFkRegistroLaboral() {
        return fkRegistroLaboral;
    }

    public void setFkRegistroLaboral(HistoricoRegistroLaboral fkRegistroLaboral) {
        this.fkRegistroLaboral = fkRegistroLaboral;
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
        if (!(object instanceof HistoricoPuesto)) {
            return false;
        }
        HistoricoPuesto other = (HistoricoPuesto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.org.isis.model.HistoricoPuesto[ id=" + id + " ]";
    }

    @Override
    public void setFechaUltimoCambio(Date fechaUltimoCambio) {
    }

    @Override
    public void setUltimoCambioPor(String ultimoCambioPor) {
    }

}
