/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.model;

import gt.org.ms.model.enums.Estado;
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
@Table(schema = "public", name = "puestos")
@NamedQueries({
    @NamedQuery(name = "Puestos.findAll", query = "SELECT p FROM Puestos p")})
public class Puestos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Column
    private String valor;

    @Column(name = "tipo")
    private String tipo;
    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private Estado estado;
    @Column(name = "codigo_padre")
    private Integer codigoPadre;
    @Basic(optional = false)
    @Column(name = "fecha_creacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "creado_por", length = 50)
    private String creadoPor;
    @Column(name = "fecha_ultimo_cambio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUltimoCambio;
    @Column(name = "ultimo_cambio_por", length = 50)
    private String ultimoCambioPor;
    @Column(name = "valor_num")
    private Integer valorNum;

    public Puestos() {
    }

    public Puestos(Integer id) {
        this.id = id;
    }

    public Puestos(Integer id, Date fechaCreacion, Date fechaUltimoCambio) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.fechaUltimoCambio = fechaUltimoCambio;
    }

    public Integer getValorNum() {
        return valorNum;
    }

    public void setValorNum(Integer valorNum) {
        this.valorNum = valorNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

//    public Estado getEstado() {
//        return estado;
//    }
//
//    public void setEstado(Estado estado) {
//        this.estado = estado;
//    }
    public Integer getCodigoPadre() {
        return codigoPadre;
    }

    public void setCodigoPadre(Integer codigoPadre) {
        this.codigoPadre = codigoPadre;
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

    public Date getFechaUltimoCambio() {
        return fechaUltimoCambio;
    }

    public void setFechaUltimoCambio(Date fechaUltimoCambio) {
        this.fechaUltimoCambio = fechaUltimoCambio;
    }

    public String getUltimoCambioPor() {
        return ultimoCambioPor;
    }

    public void setUltimoCambioPor(String ultimoCambioPor) {
        this.ultimoCambioPor = ultimoCambioPor;
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
        if (!(object instanceof Puestos)) {
            return false;
        }
        Puestos other = (Puestos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ms.rrhh.domain.model.Puestos[ id=" + id + " ]";
    }

}
