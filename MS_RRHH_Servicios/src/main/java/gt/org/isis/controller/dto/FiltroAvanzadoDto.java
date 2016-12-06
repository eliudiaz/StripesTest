/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.dto;

import gt.org.isis.model.enums.CampoBusquedaAvanzada;
import gt.org.isis.model.enums.ComparadorBusqueda;

/**
 *
 * @author edcracken
 */
public class FiltroAvanzadoDto {

    private CampoBusquedaAvanzada campo;
    private ComparadorBusqueda comparador;
    private Integer valor1;
    private Integer valor2;

    public CampoBusquedaAvanzada getCampo() {
        return campo;
    }

    public void setCampo(CampoBusquedaAvanzada campo) {
        this.campo = campo;
    }

    public Integer getValor1() {
        return valor1;
    }

    public void setValor1(Integer valor1) {
        this.valor1 = valor1;
    }

    public Integer getValor2() {
        return valor2;
    }

    public void setValor2(Integer valor2) {
        this.valor2 = valor2;
    }

    public ComparadorBusqueda getComparador() {
        return comparador;
    }

    public void setComparador(ComparadorBusqueda comparador) {
        this.comparador = comparador;
    }

}
