/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.dto;

/**
 *
 * @author edcracken
 */
public class ReqNuevaPersonaDto extends PersonaDto {

    private String fkMunicipioNacimientoNombre;
    private String fkMunicipioCedulaNombre;
    private String fkMunicipioVecindadNombre;

    public String getFkMunicipioNacimientoNombre() {
        return fkMunicipioNacimientoNombre;
    }

    public void setFkMunicipioNacimientoNombre(String fkMunicipioNacimientoNombre) {
        this.fkMunicipioNacimientoNombre = fkMunicipioNacimientoNombre;
    }

    public String getFkMunicipioCedulaNombre() {
        return fkMunicipioCedulaNombre;
    }

    public void setFkMunicipioCedulaNombre(String fkMunicipioCedulaNombre) {
        this.fkMunicipioCedulaNombre = fkMunicipioCedulaNombre;
    }

    public String getFkMunicipioVecindadNombre() {
        return fkMunicipioVecindadNombre;
    }

    public void setFkMunicipioVecindadNombre(String fkMunicipioVecindadNombre) {
        this.fkMunicipioVecindadNombre = fkMunicipioVecindadNombre;
    }

}
