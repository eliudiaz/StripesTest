/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.api.utils;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import static gt.org.ms.api.requesting.ValidationsHelper.isNull;
import gt.org.ms.api.global.exceptions.ext.UnknownException;
import gt.org.ms.api.entities.CustomEntity;
import gt.org.ms.model.Persona;
import gt.org.ms.model.PersonaChildEntity;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public static boolean isNumeric(String value) {
        try {
            Integer.valueOf(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String formatDate(Date date) {
        String format;
        SimpleDateFormat sd = new SimpleDateFormat(!isNull(format = System.getProperty("customPrintDateFormat"))
                ? format : "dd/MM/yyyy");
        return sd.format(date);
    }

    public static Date parseFechaDPI(String text) {
        try {
            DateFormatSymbols sym = DateFormatSymbols.getInstance();
            sym.setShortMonths(new String[]{"ENE", "FEB", "MAR", "ABR", "MAY", "JUN", "JUL", "AGO", "SEP", "OCT", "NOV", "DEC"});
            SimpleDateFormat sd = new SimpleDateFormat("ddMMMyyyy", sym);
            return sd.parse(text);
        } catch (ParseException ex) {
            ex.printStackTrace(System.err);
            return new DateTime().plusYears(-18).toDate();
        }
    }

    public static void setDateCreatedInfo(CustomEntity ce) {
        ce.setFechaCreacion(new DateTime().toDate());
    }

    public static void setDateUpdatedInfo(CustomEntity ce) {
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
