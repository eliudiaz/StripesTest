/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.api.misc;

import gt.org.isis.api.misc.exceptions.ext.ValidationError;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
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
        String template = "errores.html";
        TemplateManager.createContent(out, Arrays.asList(new ValidationError("test", "test")), template);
        System.out.println(">> " + new String(out.toByteArray()));
    }

}
