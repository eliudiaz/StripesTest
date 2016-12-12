/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.api.misc;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import gt.org.isis.api.misc.exceptions.ext.ValidationError;
import gt.org.isis.api.misc.exceptions.ext.ValidationException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author edcracken
 */
public class TemplateManager {

    public static void createContent(OutputStream out, Object model, String template) {
        try {
            ValidationException ex = new ValidationException(Arrays.asList(new ValidationError("test", "test")));
            PebbleEngine engine = new PebbleEngine.Builder().build();
            PebbleTemplate compiledTemplate = engine.getTemplate(template);
            Writer writer = new StringWriter();
            Map<String, Object> context = new HashMap<String, Object>();
            context.put("errors", ex.getErrors());
            compiledTemplate.evaluate(writer, context);
            out.write(writer.toString().getBytes());
        } catch (PebbleException ex1) {
            Logger.getLogger(TemplateManager.class.getName()).log(Level.SEVERE, null, ex1);
        } catch (IOException ex) {
            Logger.getLogger(TemplateManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
