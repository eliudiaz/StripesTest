/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.api.entities;

import gt.org.isis.model.enums.Estado;

/**
 *
 * @author eliud
 */
public interface DesactivableEntity extends CustomEntity {

    public Integer getId();

    public void setEstado(Estado estado);
}
