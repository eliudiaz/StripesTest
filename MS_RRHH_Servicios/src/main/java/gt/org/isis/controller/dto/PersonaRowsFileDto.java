/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.dto;

/**
 *
 * @author eliud
 */
public class PersonaRowsFileDto extends PersonaDto {

    private RegistroLaboralPuestoDto actual;

    public RegistroLaboralPuestoDto getActual() {
        return actual;
    }

    public void setActual(RegistroLaboralPuestoDto actual) {
        this.actual = actual;
    }

}
