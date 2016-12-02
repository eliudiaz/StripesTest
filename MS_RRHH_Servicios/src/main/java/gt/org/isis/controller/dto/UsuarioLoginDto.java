/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.dto;

import java.util.List;
import javax.validation.constraints.NotNull;

/**
 *
 * @author edcracken
 */
public class UsuarioLoginDto {

    @NotNull
    private String usuario;
    @NotNull
    private String clave;
    private String nombres;
    private String foto;
    private List<RoleDto> roles;
    private List<AccesoDto> accesos;
    private boolean root;

    public List<AccesoDto> getAccesos() {
        return accesos;
    }

    public void setAccesos(List<AccesoDto> accesos) {
        this.accesos = accesos;
    }

    public boolean isRoot() {
        return root;
    }

    public void setRoot(boolean root) {
        this.root = root;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
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

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

}
