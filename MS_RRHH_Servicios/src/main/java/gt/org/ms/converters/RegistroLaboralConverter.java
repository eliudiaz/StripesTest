
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.converters;

import gt.org.ms.controller.dto.RegistroLaboralDto;
import gt.org.ms.model.RegistroLaboral;
import gt.org.ms.api.utils.BeansConverter;
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
