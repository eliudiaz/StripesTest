/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.dto;

import gt.org.ms.model.enums.CampoBusquedaAvanzada;
import gt.org.ms.model.enums.ComparadorBusqueda;

/**
 *
 * @author edcracken
 */
public class FiltroAvanzadoDto {

    private CampoBusquedaAvanzada campo;
    private ComparadorBusqueda comparador;
    private Integer valor1;
    private Integer valor2;
    private String valorTexto1;
    private String valorTexto2;

    public String getValorTexto1() {
        return valorTexto1;
    }

    public void setValorTexto1(String valorTexto1) {
        this.valorTexto1 = valorTexto1;
    }

    public String getValorTexto2() {
        return valorTexto2;
    }

    public void setValorTexto2(String valorTexto2) {
        this.valorTexto2 = valorTexto2;
    }

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
