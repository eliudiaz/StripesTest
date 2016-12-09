/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.api.misc;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 *
 * @author edcracken
 */
public class TemplateManager {

    public static void createContent(OutputStream out, Object model, String template) {
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache m = mf.compile(template);
        m.execute(new PrintWriter(out), model);
    }

}
