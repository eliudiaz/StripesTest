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
public class GetPersonaDto extends PersonaDto {

    private RefAreaGeograficaDto refNacimiento;
    private RefAreaGeograficaDto refVecindad;
    private RefAreaGeograficaDto refCedula;

    public RefAreaGeograficaDto getRefNacimiento() {
        return refNacimiento;
    }

    public void setRefNacimiento(RefAreaGeograficaDto refNacimiento) {
        this.refNacimiento = refNacimiento;
    }

    public RefAreaGeograficaDto getRefVecindad() {
        return refVecindad;
    }

    public void setRefVecindad(RefAreaGeograficaDto refVecindad) {
        this.refVecindad = refVecindad;
    }

    public RefAreaGeograficaDto getRefCedula() {
        return refCedula;
    }

    public void setRefCedula(RefAreaGeograficaDto refCedula) {
        this.refCedula = refCedula;
    }

}
