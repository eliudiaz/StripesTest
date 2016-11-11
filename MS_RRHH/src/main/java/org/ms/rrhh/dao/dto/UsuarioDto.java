/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ms.rrhh.dao.dto;

/**
 *
 * @author edcracken
 */
public class UsuarioDto {

    private String usuario;
    private String correo;
    private String nombres;
    private String apellidos;
    private String clave;
    private PersonaDto persona;
    private RoleDto role;

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

    public PersonaDto getPersona() {
        return persona;
    }

    public void setPersona(PersonaDto persona) {
        this.persona = persona;
    }

    public RoleDto getRole() {
        return role;
    }

    public void setRole(RoleDto role) {
        this.role = role;
    }

    public UsuarioDto() {
    }

    private UsuarioDto(Builder builder) {
        this.usuario = builder.usuario;
        this.correo = builder.correo;
        this.nombres = builder.nombres;
        this.apellidos = builder.apellidos;
        this.clave = builder.clave;
        this.persona = builder.persona;
        this.role = builder.role;
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
        private PersonaDto persona;
        private RoleDto role;

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

        public Builder withPersona(PersonaDto persona) {
            this.persona = persona;
            return this;
        }

        public Builder withRole(RoleDto role) {
            this.role = role;
            return this;
        }

        public UsuarioDto build() {
            return new UsuarioDto(this);
        }
    }

}
