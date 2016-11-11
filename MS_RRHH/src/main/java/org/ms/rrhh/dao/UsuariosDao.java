/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ms.rrhh.dao;

import org.ms.rrhh.dao.dto.UsuarioDto;
import org.ms.rrhh.utils.C;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author eliud
 */
@Service
public class UsuariosDao {

    public UsuarioDto doLogin(String username, String password) throws Exception {
        try {
            RestTemplate rs = new RestTemplate();
            return rs.postForObject(C.LOGIN_PATH != null ? C.SERVICIOS_CONTEXT.concat(C.LOGIN_PATH)
                    : "http://localhost:41825/MS_RRHH_Servicios/usuarios/login",
                    UsuarioDto.builder().withUsuario(username).withClave(username).build(),
                    UsuarioDto.class);
        } catch (HttpClientErrorException rc) {
            if (rc.getStatusCode()
                    .equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
                throw new Exception("Servicio de autenticacion no disponible");
            } else {
                throw new Exception(rc.getResponseBodyAsString());
            }
        }
    }

}
