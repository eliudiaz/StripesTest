/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.converters;

import gt.org.isis.controller.dto.PersonaDto;
import gt.org.isis.controller.dto.PersonaRowsFileDto;
import gt.org.isis.api.utils.BeansConverter;
import org.springframework.stereotype.Component;

/**
 *
 * @author edcracken
 */
@Component
public class PersonaRowsFileDtoConverter extends BeansConverter<PersonaRowsFileDto, PersonaDto> {
}
