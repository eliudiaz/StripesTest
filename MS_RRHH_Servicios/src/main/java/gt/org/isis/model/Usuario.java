/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.model;

import gt.org.isis.api.entities.DesactivableEntity;
import gt.org.isis.model.enums.Estado;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(schema = "public", name = "usuario")
public class Usuario implements Serializable, DesactivableEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String id;
    @Column
    private String correo;
    @Column(name = "super_usuario")
    private Boolean superUsuario;
    @Column
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @Column
    private String nombres;
    @Column
    private String apellidos;
    @Column
    private String clave;
    @Column
    private String sesion;
    @Basic(optional = false)
    @Column(name = "fecha_creacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "creado_por")
    private String creadoPor;
    @Column(name = "fecha_ultimo_cambio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUltimoCambio;
    @Column(name = "ultimo_cambio_por")
    private String ultimoCambioPor;
    @JoinColumn(name = "fk_persona", referencedColumnName = "cui")
    @ManyToOne(optional = false)
    private Persona fkPersona;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "fkUsuario", cascade = CascadeType.ALL)
    private Collection<UsuarioRoles> usuarioRolesCollection;

    public Usuario() {
    }

    public Usuario(String id) {
        this.id = id;
    }

    public Usuario(String id, Date fechaCreacion, Date fechaUltimoCambio) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.fechaUltimoCambio = fechaUltimoCambio;
    }

    public String getSesion() {
        return sesion;
    }

    public void setSesion(String sesion) {
        this.sesion = sesion;
    }

    public Collection<UsuarioRoles> getUsuarioRolesCollection() {
        return usuarioRolesCollection;
    }

    public void setUsuarioRolesCollection(Collection<UsuarioRoles> usuarioRolesCollection) {
        this.usuarioRolesCollection = usuarioRolesCollection;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Boolean getSuperUsuario() {
        return superUsuario;
    }

    public void setSuperUsuario(Boolean superUsuario) {
        this.superUsuario = superUsuario;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
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

    @Override
    public void setFechaUltimoCambio(Date fechaUltimoCambio) {
        this.fechaUltimoCambio = fechaUltimoCambio;
    }

    public String getUltimoCambioPor() {
        return ultimoCambioPor;
    }

    public void setUltimoCambioPor(String ultimoCambioPor) {
        this.ultimoCambioPor = ultimoCambioPor;
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ms.rrhh.domain.model.Usuario[ id=" + id + " ]";
    }

}
