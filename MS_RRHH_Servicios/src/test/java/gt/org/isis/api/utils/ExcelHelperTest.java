/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.api.utils;

import gt.org.isis.controller.dto.PersonaRowsFileDto;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author eliud
 */
public class ExcelHelperTest {

    /**
     * Test of getAnnotatedFieldsConfig method, of class ExcelHelper.
     */
    @Test
    public void testGetAnnotatedFieldsConfig() {
        System.out.println("getAnnotatedFieldsConfig");
        Class c = PersonaRowsFileDto.class;
        List<FieldDto> result = ExcelHelper.getAnnotatedFieldsConfig(c);
        assertFalse(result.isEmpty());
    }

}
