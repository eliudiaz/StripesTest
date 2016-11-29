/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.home.handlers;

import gt.org.isis.controller.dto.ExportPersonasRequestDto;
import gt.org.isis.controller.dto.PersonaRowsFileDto;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import org.springframework.util.Assert;

/**
 *
 * @author eliud
 */
public class PersonasExporterHandlerTest {

    public PersonasExporterHandlerTest() {
    }

    /**
     * Test of execute method, of class PersonasExporterHandler.
     */
    @Test
    public void testExecute() {
        FileOutputStream fo = null;
        try {
            PersonaRowsFileDto p = new PersonaRowsFileDto();
            p.setPrimerNombre("sdfsdf");
            p.setSegundoNombre("sdfsdfs");
            p.setPrimerApellido("sdsdfsfd");
            p.setSegundoApellido("sdfsdfsdf");
            p.setCui("sdfsdlfksjdlfkjsdf");
            System.out.println("execute");
            fo = new FileOutputStream(new File("test.xls"));

            ExportPersonasRequestDto request = new ExportPersonasRequestDto(Arrays.asList(p));
            PersonasExporterHandler instance = new PersonasExporterHandler();
            ByteArrayOutputStream result = instance.execute(request);
            Assert.notNull(result);
            result.writeTo(fo);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PersonasExporterHandlerTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PersonasExporterHandlerTest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fo.close();
            } catch (IOException ex) {
                Logger.getLogger(PersonasExporterHandlerTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
