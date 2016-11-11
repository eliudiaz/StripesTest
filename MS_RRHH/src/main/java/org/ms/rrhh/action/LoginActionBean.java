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
package org.ms.rrhh.action;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidationError;
import org.ms.rrhh.dao.UsuariosDao;
import org.ms.rrhh.dao.dto.UsuarioDto;
import org.springframework.util.DigestUtils;

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
        return new ForwardResolution("/WEB-INF/jsp/login.jsp");
    }

    public Resolution login() {
        UsuarioDto usuario = usuariosRepo.doLogin(username);
        ValidationError error = new SimpleError("Error usuario invalido", new Object[]{});//new LocalizableError("usernameDoesNotExist");
        if (usuario == null) {
            getContext().getValidationErrors().add("username", error);
            return getContext().getSourcePageResolution();
        } else {
            if (!usuario.getClave().equals(new String(DigestUtils.md5Digest(password.getBytes())))) {
                getContext().getValidationErrors().add("username", error);
                return getContext().getSourcePageResolution();
            }
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
