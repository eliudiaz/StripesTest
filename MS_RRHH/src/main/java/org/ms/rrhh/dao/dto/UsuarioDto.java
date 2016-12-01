/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ms.rrhh.dao.dto;

import com.google.gson.Gson;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author edcracken
 */
@XmlRootElement
public class UsuarioDto {

    private String usuario;
    private String correo;
    private String nombres;
    private String apellidos;
    private String clave;
    private boolean root;
    private List<RoleDto> roles;

    public UsuarioDto(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }

    public boolean isRoot() {
        return root;
    }

    public void setRoot(boolean root) {
        this.root = root;
    }

    public List<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDto> roles) {
        this.roles = roles;
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

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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

    public UsuarioDto() {
    }

    private UsuarioDto(Builder builder) {
        this.usuario = builder.usuario;
        this.correo = builder.correo;
        this.nombres = builder.nombres;
        this.apellidos = builder.apellidos;
        this.clave = builder.clave;
        this.roles = builder.roles;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String usuario;
        private String correo;
        private String nombres;
        private String apellidos;
        private String clave;
        private List<RoleDto> roles;

        public Builder withUsuario(String usuario) {
            this.usuario = usuario;
            return this;
        }

        public Builder withCorreo(String correo) {
            this.correo = correo;
            return this;
        }

        public Builder withNombres(String nombres) {
            this.nombres = nombres;
            return this;
        }

        public Builder withApellidos(String apellidos) {
            this.apellidos = apellidos;
            return this;
        }

        public Builder withClave(String clave) {
            this.clave = clave;
            return this;
        }

        public Builder withRoles(List<RoleDto> roles) {
            this.roles = roles;
            return this;
        }

        public UsuarioDto build() {
            return new UsuarioDto(this);
        }
    }

}
