/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.home.handlers;

import gt.org.isis.api.misc.exceptions.ExceptionsManager;
import gt.org.isis.api.requesting.GenericValidationRequest;
import gt.org.isis.api.requesting.ValidationRequestContext;
import static gt.org.isis.api.requesting.ValidationsHelper.isNull;
import gt.org.isis.controller.dto.BusquedaAvanzadaDto;
import gt.org.isis.controller.dto.FiltroAvanzadoDto;
import gt.org.isis.model.enums.ComparadorBusqueda;

/**
 *
 * @author edcracken
 */
public class BusquedaAvanzadaValidation extends GenericValidationRequest<BusquedaAvanzadaDto> {

    @Override
    public void validate(BusquedaAvanzadaDto request, ValidationRequestContext ctx) {
        for (FiltroAvanzadoDto f : request.getFiltros()) {
            if (f.getComparador().equals(ComparadorBusqueda.ENTRE)) {
                if (!isNull(f.getValor1()) || !isNull(f.getValor2())) {
                    throw ExceptionsManager.newValidationException("rango_invalido", "Rango incompleto, debe llevar valor inicio y fin!");
                }
                if (Integer.valueOf(f.getValor2()) < Integer.valueOf(f.getValor1())) {
                    throw ExceptionsManager.newValidationException("rango_invalido", "Valor 1 debe ser menor a Valor 2");
                }
            }
        }
    }

}
