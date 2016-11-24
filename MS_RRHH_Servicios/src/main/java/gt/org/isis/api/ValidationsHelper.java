/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.api;

/**
 *
 * @author edcracken
 */
public class ValidationsHelper {

    public static boolean isNull(Object o) {
        return o == null;
    }

    public static boolean containsAny(String source, String param) {
        param = param.toLowerCase().substring(0, param.length() > 6
                ? 6 : param.length() - 1);
        source = source.toLowerCase();
        return source.equalsIgnoreCase(param) || source.contains(param) || source.startsWith(param);
    }
}
