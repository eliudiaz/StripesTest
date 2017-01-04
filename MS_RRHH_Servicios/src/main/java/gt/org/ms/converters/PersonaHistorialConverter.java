/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.converters;

import gt.org.ms.controller.dto.PersonaDto;
import gt.org.ms.model.HistoricoPersona;
import gt.org.ms.api.utils.BeansConverter;
import org.springframework.stereotype.Component;

/**
 *
 * @author edcracken
 */
@Component
public class PersonaHistorialConverter extends BeansConverter<HistoricoPersona, PersonaDto> {
}
