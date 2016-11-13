/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ms.rrhh.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.ms.rrhh.dao.dto.UsuarioDto;
import org.ms.rrhh.utils.C;
import org.ms.rrhh.utils.ServerMessagesHelper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
            RestTemplate rt = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            String r = new Gson().toJson(UsuarioDto.builder().withUsuario(username).withClave(password).build());
            HttpEntity<String> entity = new HttpEntity<String>(r, headers);

            System.out.println(">> " + r);

            ResponseEntity<String> re = rt.exchange(C.LOGIN_PATH != null ? C.SERVICIOS_CONTEXT.concat(C.LOGIN_PATH)
                    : "http://localhost:41825/MS_RRHH_Servicios/usuarios/login",
                    HttpMethod.POST, entity, String.class);
            System.out.println(">> " + re.getBody());
            Gson g = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
            return g.fromJson(re.getBody(), UsuarioDto.class);

        } catch (HttpClientErrorException rc) {
            if (rc.getStatusCode().toString().equals("500")) {
                throw new Exception("Servicio de autenticacion no disponible");
            } else if (rc.getStatusCode().toString().equals("422")) {
                throw ServerMessagesHelper.parseValidationHttpErrorMessage(rc.getResponseBodyAsString());
            } else {
                throw new Exception(rc.getResponseBodyAsString());
            }
        }
    }

}
