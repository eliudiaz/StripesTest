package org.ms.rrhh.action.ext;

import net.sourceforge.stripes.action.ActionBeanContext;
import org.ms.rrhh.dao.dto.UsuarioDto;

/**
 * ActionBeanContext subclass for the Bugzooky application that manages where
 * values like the logged in user are stored.
 *
 * @author edcracken
 */
public class SystemActionBeanContext extends ActionBeanContext {

    /**
     * Gets the currently logged in user, or null if no-one is logged in.
     *
     * @return
     */
    public UsuarioDto getUser() {
        return (UsuarioDto) getRequest().getSession().getAttribute("user");
    }

    /**
     * Sets the currently logged in user.
     *
     * @param currentUser
     */
    public void setUser(UsuarioDto currentUser) {
        getRequest().getSession().setAttribute("user", currentUser);
    }

    /**
     * Logs the user out by invalidating the session.
     */
    public void logout() {
        getRequest().getSession().invalidate();
    }
}
