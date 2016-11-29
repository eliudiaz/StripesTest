
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.converters;

import gt.org.isis.controller.dto.RegistroLaboralDto;
import gt.org.isis.model.RegistroLaboral;
import gt.org.isis.api.utils.BeansConverter;
import java.util.List;

/**
 *
 * @author edcracken
 */
public class RegistroLaboralConverter extends BeansConverter<RegistroLaboral, RegistroLaboralDto> {

    @Override
    public RegistroLaboralDto toDTO(RegistroLaboral iA) {
        RegistroLaboralDto rl = super.toDTO(iA);
        rl.setPuestos(new RegistroLaboralPuestosConverter().toDTO((List) iA.getPuestoCollection()));
        return rl;
    }

}
