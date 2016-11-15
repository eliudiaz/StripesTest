/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.dto;

import java.util.List;

/**
 *
 * @author edcracken
 */
public class BusquedaAvanzadaDto {

    private List<FiltroAvanzadoDto> filtros;

    public List<FiltroAvanzadoDto> getFiltros() {
        return filtros;
    }

    public void setFiltros(List<FiltroAvanzadoDto> filtros) {
        this.filtros = filtros;
    }

}
