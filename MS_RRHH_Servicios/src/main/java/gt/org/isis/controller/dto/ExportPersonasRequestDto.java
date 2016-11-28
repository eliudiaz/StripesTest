/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.dto;

import java.util.List;

/**
 *
 * @author eliud
 */
public class ExportPersonasRequestDto {

    private final List<PersonaDto> personas;

    public List<PersonaDto> getPersonas() {
        return personas;
    }

    public ExportPersonasRequestDto(List<PersonaDto> personas) {
        this.personas = personas;
    }

}
