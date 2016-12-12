/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.api.misc;

import com.google.common.collect.HashBiMap;
import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import gt.org.isis.api.misc.exceptions.ext.ValidationError;
import gt.org.isis.api.misc.exceptions.ext.ValidationException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;

/**
 *
 * @author edcracken
 */
public class TemplateManagerTest {

    /**
     * Test of createContent method, of class TemplateManager.
     */
    @Test
    public void testCreateContent() {
        System.out.println("createContent");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ValidationException ex = new ValidationException(Arrays.asList(new ValidationError("test", "test")));
        String template = "errores.html";
        TemplateManager.createContent(out, ex, template);
        System.out.println(">> " + new String(out.toByteArray()));

    }

    @Test
    public void pebble() {
        try {
            ValidationException ex = new ValidationException(Arrays.asList(new ValidationError("test", "test")));
            PebbleEngine engine = new PebbleEngine.Builder().build();
            PebbleTemplate compiledTemplate = engine.getTemplate("errores_1.html");
            Writer writer = new StringWriter();
            Map<String, Object> context = new HashMap<String, Object>();
            context.put("errors", ex.getErrors());
            compiledTemplate.evaluate(writer, context);
            System.out.println(">> " + writer.toString());
        } catch (PebbleException ex) {
            Logger.getLogger(TemplateManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TemplateManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
