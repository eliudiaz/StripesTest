/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.model;

import gt.org.isis.api.entities.CustomEntity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author eliud
 */
@Entity
@Table(name = "historico_idioma", schema = "public")
public class HistoricoIdioma implements Serializable, CustomEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "fk_idioma", nullable = false)
    private Integer fkIdioma;
    @Column(name = "fecha_creacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "creado_por", length = 50)
    private String creadoPor;
    @JoinColumn(name = "fk_persona", referencedColumnName = "cui", nullable = false)
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Persona fkPersona;

    public HistoricoIdioma() {
    }

    public HistoricoIdioma(Integer id) {
        this.id = id;
    }

    public HistoricoIdioma(Integer id, Integer fkIdioma, Date fechaCreacion) {
        this.id = id;
        this.fkIdioma = fkIdioma;
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFkIdioma() {
        return fkIdioma;
    }

    public void setFkIdioma(Integer fkIdioma) {
        this.fkIdioma = fkIdioma;
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
        if (!(object instanceof HistoricoIdioma)) {
            return false;
        }
        HistoricoIdioma other = (HistoricoIdioma) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.org.isis.model.HistoricoIdioma[ id=" + id + " ]";
    }

    @Override
    public void setFechaUltimoCambio(Date fechaUltimoCambio) {
    }

    @Override
    public void setUltimoCambioPor(String ultimoCambioPor) {
    }

}
