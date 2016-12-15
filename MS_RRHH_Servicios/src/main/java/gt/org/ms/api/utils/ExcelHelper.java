/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.api.utils;

import gt.org.ms.api.global.exceptions.ExceptionsManager;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 *
 * @author edcracken
 */
public class ExcelHelper {

    public static Map<String, Object> introspect(Object obj) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        BeanInfo info = Introspector.getBeanInfo(obj.getClass());
        for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
            Method reader = pd.getReadMethod();
            if (reader != null) {
                result.put(pd.getName(), reader.invoke(obj));
            }
        }
        return result;
    }

    public static <T> void writeMapToExcel(OutputStream out, List<Map<String, Object>> data, List<FieldDto> fields) {
        if (fields.isEmpty()) {
            throw ExceptionsManager.newInternalErrorException("fields_config",
                    "Configuracion de campos de excel no es valida!", null);
        }
        HSSFWorkbook workbook = null;
        try {
            workbook = new HSSFWorkbook();
            Sheet sheet = workbook.createSheet();

            int rowCount = 0;
            int columnCount = 0;
            Row row = sheet.createRow(rowCount++);
            Collections.sort(fields);
            for (FieldDto field : fields) {
                Cell cell = row.createCell(columnCount++);
                cell.setCellValue(field.getTitle());
            }
            for (Map<String, Object> t : data) {
                row = sheet.createRow(rowCount++);
                columnCount = 0;
                for (FieldDto field : fields) {
                    Cell cell = row.createCell(columnCount);

                    Object value = t.get(field.getName());
                    System.out.println("writing >> " + value);
                    if (value != null) {
                        if (value instanceof String) {
                            cell.setCellValue((String) value);
                        } else if (value instanceof Long) {
                            cell.setCellValue((Long) value);
                        } else if (value instanceof Integer) {
                            cell.setCellValue((Integer) value);
                        } else if (value instanceof Double) {
                            cell.setCellValue((Double) value);
                        } else {
                            cell.setCellValue(value.toString());
                        }
                    }
                    sheet.autoSizeColumn(columnCount);
                    columnCount++;
                }
            }
            workbook.write(out);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace(System.err);
            throw ExceptionsManager.newInternalErrorException("excel_exporter",
                    "Error generando archivo de excel", e);
        }
    }

    public static <T> void writeMapToExcel(OutputStream out, List<Map<String, Object>> data) {
        List<FieldDto> fields = new LinkedList<FieldDto>();
        for (Iterator<Map.Entry<String, Object>> it = data.iterator().next().entrySet().iterator(); it.hasNext();) {
            String k;
            fields.add(new FieldDto(k = it.next().getKey(), k));
        }
        writeMapToExcel(out, data, fields);
    }

    public static List<FieldDto> getAnnotatedFieldsConfig(Class c) {
        LinkedList<FieldDto> lsFields = new LinkedList<FieldDto>();
        Field[] fields = c.getDeclaredFields();
        for (Field f : fields) {
            Annotation[] ans = f.getDeclaredAnnotations();
            System.out.println(Arrays.toString(ans));
            for (Annotation an : ans) {
                ExcelCol col = (ExcelCol) an;
                lsFields.add(new FieldDto(f.getName(), col.title(), col.order()));
            }
        }
        return lsFields;
    }

}
