/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.api.entities;

import java.util.Date;

/**
 *
 * @author eliud
 */
public interface CustomEntity {

    public void setFechaUltimoCambio(Date fechaUltimoCambio);

    public void setUltimoCambioPor(String ultimoCambioPor);

    public void setFechaCreacion(Date fechaCreacion);

    public void setCreadoPor(String creadoPor);
}
