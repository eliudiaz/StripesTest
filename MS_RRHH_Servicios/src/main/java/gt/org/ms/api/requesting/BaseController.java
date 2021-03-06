/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.api.requesting;

import gt.org.ms.api.entities.SessionEntity;
import gt.org.ms.api.global.exceptions.ExceptionsManager;
import static gt.org.ms.api.requesting.ValidationsHelper.isNull;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author eliu
 */
public class BaseController {

    public void configureSesion(Object o, HttpServletRequest request) {
        if (o instanceof SessionEntity) {
            String s;
            if (!isNull(s = request.getParameter("sesion"))) {
                ((SessionEntity) o).setSesion(s);
            } else {
                throw ExceptionsManager.newValidationException("sesion_invalida",
                        "No puede realizar transacciones sin una sesion de usuario valida!");
            }
        }
    }

}
