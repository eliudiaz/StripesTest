/*
 * Copyright (C) 2008-2012 Freddy Daoud
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. *
 */
package gt.org.ms.rrhh.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidationError;
import gt.org.ms.rrhh.action.exceptions.StripesValidationException;
import gt.org.ms.rrhh.dao.UsuariosDao;
import gt.org.ms.rrhh.dao.dto.AccesoDto;
import gt.org.ms.rrhh.dao.dto.RoleDto;
import gt.org.ms.rrhh.dao.dto.UsuarioDto;
import gt.org.ms.rrhh.utils.C;

@UrlBinding("/Login.htm")
public class LoginActionBean extends BaseActionBean {

    @SpringBean
    private UsuariosDao usuariosRepo;

    @Validate(required = true)
    private String username;

    @Validate(required = true)
    private String password;

    private String targetUrl;

    @DefaultHandler
    @DontValidate
    public Resolution view() {
        if (getContext().getRequest().getSession().getAttribute("currentUser") == null) {
            return new ForwardResolution("/WEB-INF/jsp/login.jsp");
        } else {
            return new ForwardResolution(HomeActionBean.class);
        }
    }

    private void setApplicationContext() {
        HttpServletRequest req = getContext().getRequest();
        String scheme = req.getScheme();
        String serverName = req.getServerName();
        int serverPort = req.getServerPort();
        String uri = req.getContextPath();
        String url = scheme + "://" + serverName + ":" + serverPort + uri;
        req.getSession().setAttribute("applicationPath", url);
        req.getSession().setAttribute("servicesPath", C.SERVICIOS_CONTEXT == null
                ? "http://localhost:41825/MS_RRHH_Servicios/" : C.SERVICIOS_CONTEXT);
        req.getSession().setAttribute("lectorPath", C.LECTOR_PATH == null
                ? "http://localhost:82/lector/launch.php" : C.LECTOR_PATH);
        req.getSession().setAttribute("pushPath", C.PUSH_PATH == null
                ? "http://localhost:41825/sd-lector-events/push" : C.PUSH_PATH);
        req.getSession().setAttribute("pullPath", C.PULL_PATH == null
                ? "http://localhost:41825/sd-lector-events/pull" : C.PULL_PATH);

    }

    public Resolution login() {
        UsuarioDto usuario;
        try {
            usuario = usuariosRepo.doLogin(username, password, getContext().getRequest().getSession().getId());
            if (!usuario.isRoot()
                    && (usuario.getRoles() == null || usuario.getRoles().isEmpty())) {
                throw new Exception("Usuario no tiene roles asignados");
            }
            if (!usuario.isRoot()) {
                Map<String, AccesoDto> accesos = new HashMap<String, AccesoDto>();
                for (RoleDto r : usuario.getRoles()) {
                    for (AccesoDto a : r.getAccesos()) {
                        accesos.put(a.getValor(), a);
                    }
                }
                usuario.setAccesos(new ArrayList<AccesoDto>(accesos.values()));
            }

            getContext().getRequest().getSession().setAttribute("currentUser", usuario);
            setApplicationContext();

        } catch (Exception e) {
            if (e instanceof StripesValidationException) {
                for (ValidationError ve : ((StripesValidationException) e).getErrors()) {
                    getContext().getValidationErrors().add("username", ve);
                }
            } else {
                getContext().getValidationErrors().add("username", new SimpleError(e.getMessage(), new Object[]{}));
            }

            return getContext().getSourcePageResolution();
        }
        ValidationError error = new SimpleError("Error usuario invalido", new Object[]{});//new LocalizableError("usernameDoesNotExist");
        if (usuario == null) {
            getContext().getValidationErrors().add("username", error);
            return getContext().getSourcePageResolution();
        }
        System.out.println("reading >>  " + username + " >> " + password);
        return new ForwardResolution(HomeActionBean.class);
    }

    public String getJavaVersion() {
        return System.getProperty("java.version");
    }

    public String getOsName() {
        return System.getProperty("os.name");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

}
