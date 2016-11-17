/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed.cracken.code.managers;

import ed.cracken.code.servlets.dto.Persona;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author eliud
 */
public class IDsManager {

    private Map<String, Persona> ids = new HashMap<String, Persona>();

    public Map<String, Persona> getIds() {
        return ids;
    }

    public void setIds(Map<String, Persona> ids) {
        this.ids = ids;
    }

}
