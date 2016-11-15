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
    private List<RoleDto> role;

    public List<RoleDto> getRole() {
        return role;
    }

    public void setRole(List<RoleDto> role) {
        this.role = role;
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
