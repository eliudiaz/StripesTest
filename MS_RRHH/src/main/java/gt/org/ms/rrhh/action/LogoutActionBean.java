/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.rrhh.action;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author eliud
 */
@UrlBinding("/Logout.htm")
public class LogoutActionBean extends BaseActionBean {

    public Resolution logout() throws Exception {
        getContext().getRequest().getSession().invalidate();
        return new ForwardResolution(LoginActionBean.class);
    }
}
