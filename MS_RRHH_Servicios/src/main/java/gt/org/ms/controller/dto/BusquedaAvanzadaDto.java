/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.dto;

import java.util.List;
import javax.validation.Valid;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author edcracken
 */
public class BusquedaAvanzadaDto {

    @Valid
    @NotEmpty
    private List<FiltroAvanzadoDto> filtros;

    public List<FiltroAvanzadoDto> getFiltros() {
        return filtros;
    }

    public void setFiltros(List<FiltroAvanzadoDto> filtros) {
        this.filtros = filtros;
    }

}
