/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.dto;

import gt.org.isis.api.entities.SessionEntity;
import gt.org.isis.api.entities.SessionEntityImpl;
import gt.org.isis.model.enums.Estado;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;

/**
 *
 * @author edcracken
 */
public class UsuarioDto extends SessionEntityImpl {

    private String usuario;
    @NotNull
    private String correo;
    private Boolean superUsuario;
    @NotNull
    private Estado estado;
    @NotNull
    private String nombres;
    @NotNull
    private String apellidos;
    private String clave;
    private String confirmacionClave;
    private String cui;
    private List<RoleDto> roles;
    private PersonaDto persona;
    private Date fechaCreacion;
    private String creadoPor;
    private Date fechaUltimoCambio;
    private String ultimoCambioPor;
    private boolean root;
    private boolean resetClave;

    public boolean isResetClave() {
        return resetClave;
    }

    public void setResetClave(boolean resetClave) {
        this.resetClave = resetClave;
    }

    public boolean isRoot() {
        return root;
    }

    public void setRoot(boolean root) {
        this.root = root;
    }

    public UsuarioDto(String usuario) {
        this.usuario = usuario;
    }

    public UsuarioDto() {
    }

    public PersonaDto getPersona() {
        return persona;
    }

    public void setPersona(PersonaDto persona) {
        this.persona = persona;
    }

    public List<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDto> roles) {
        this.roles = roles;
    }

    public String getCui() {
        return cui;
    }

    public void setCui(String cui) {
        this.cui = cui;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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

    public String getConfirmacionClave() {
        return confirmacionClave;
    }

    public void setConfirmacionClave(String confirmacionClave) {
        this.confirmacionClave = confirmacionClave;
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

}
