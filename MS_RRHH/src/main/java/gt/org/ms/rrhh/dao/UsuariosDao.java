/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.rrhh.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gt.org.ms.rrhh.dao.dto.UsuarioDto;
import gt.org.ms.rrhh.utils.C;
import gt.org.ms.rrhh.utils.ServerMessagesHelper;
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
 * @author edcracken
 */
@Service
public class UsuariosDao {

    public UsuarioDto doLogin(String username, String password, String sesion) throws Exception {
        try {
            RestTemplate rt = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            String r = new Gson().toJson(UsuarioDto.builder()
                    .withUsuario(username)
                    .withClave(password)
                    .withSesion(sesion)
                    .build());
            HttpEntity<String> entity = new HttpEntity<String>(r, headers);
            System.out.println(">> " + r);
            String servicesCtx;
            System.out.println(">> login to >> " + (servicesCtx = C.SERVICIOS_CONTEXT == null
                    ? "http://localhost:41825/MS_RRHH_Servicios/" : C.SERVICIOS_CONTEXT));
            ResponseEntity<String> re = rt.exchange(servicesCtx.concat("usuarios/login"),
                    HttpMethod.POST, entity, String.class);
            System.out.println(">> " + re.getBody());
            Gson g = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
            return g.fromJson(re.getBody(), UsuarioDto.class);

        } catch (HttpClientErrorException rc) {
            if (rc.getStatusCode().toString().equals("422")) {
                throw ServerMessagesHelper.parseValidationHttpErrorMessage(rc.getResponseBodyAsString());
            } else {
                throw new Exception("Servicio de autenticacion no disponible");
            }
        }
    }

}
