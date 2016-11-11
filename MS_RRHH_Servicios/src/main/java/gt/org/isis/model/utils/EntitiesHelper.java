/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.model.utils;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import gt.org.isis.api.misc.exceptions.ext.UnknownException;
import gt.org.isis.model.CustomEntity;
import gt.org.isis.model.Persona;
import gt.org.isis.model.PersonaChildEntity;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.Years;

/**
 *
 * @author eliud
 */
public class EntitiesHelper {

    public static void setDateCreateRef(CustomEntity ce) {
        ce.setFechaCreacion(new DateTime().toDate());
    }

    public static void setDateUpdateRef(CustomEntity ce) {
        ce.setFechaUltimoCambio(new DateTime().toDate());
    }

    public static List<Persona> getPersonas(List<PersonaChildEntity> pc) {
        return new ArrayList<Persona>(Collections2.transform(pc,
                new Function<PersonaChildEntity, Persona>() {
            @Override
            public Persona apply(PersonaChildEntity f) {
                return f.getFkPersona();
            }
        }));
    }

    public static Integer getAge(Date birthDate) {
        return Years.yearsBetween(LocalDate.fromDateFields(birthDate),
                LocalDate.fromDateFields(Calendar.getInstance().getTime())).getYears();
    }

    public static String md5Gen(String or) {
        try {
            byte[] bytesOfMessage = or.getBytes("UTF-8");
            StringBuilder hexString = new StringBuilder();
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(bytesOfMessage);
            for (int i = 0; i < hash.length; i++) {
                if ((0xff & hash[i]) < 0x10) {
                    hexString.append("0"
                            + Integer.toHexString((0xFF & hash[i])));
                } else {
                    hexString.append(Integer.toHexString(0xFF & hash[i]));
                }
            }
            return hexString.toString();
        } catch (UnsupportedEncodingException ex) {
            throw new UnknownException();
        } catch (NoSuchAlgorithmException ex) {
            throw new UnknownException();
        }
    }

}
