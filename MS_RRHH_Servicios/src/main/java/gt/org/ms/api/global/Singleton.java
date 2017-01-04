/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.api.global;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author edcracken
 */
public final class Singleton {

    private static Map<String, Object> instances = new HashMap<String, Object>();

    private Singleton() {
    }

    @SuppressWarnings("unchecked")
    public static <T> T Instance(String name, Object... initargs) {
        Class<T> c;
        try {
            c = (Class<T>) Class.forName(name);
        } catch (ClassNotFoundException e1) {
            System.err.println("ClassNotFoundException: " + name);
            e1.printStackTrace();
            return null;
        }

        if (!instances.containsKey(name)) {
            T inst;
            try {
                Class<?>[] parameterTypes = new Class<?>[initargs.length];
                for (int i = 0; i < initargs.length; i++) {
                    parameterTypes[i] = initargs[i].getClass();
                }
                inst = c.getConstructor(parameterTypes).newInstance(initargs);
            } catch (Exception e) {
                System.err.println("Can't get or call constructor");
                e.printStackTrace();
                return null;
            }
            instances.put(name, inst);
        }

        return c.cast(instances.get(name));
    }
}
