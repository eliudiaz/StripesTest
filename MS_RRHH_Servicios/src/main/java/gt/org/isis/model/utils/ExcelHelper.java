/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.model.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 *
 * @author eliud
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

    public static <T> void writeMapToExcel(OutputStream out, List<Map<String, Object>> data) {
        HSSFWorkbook workbook = null;
        try {
            workbook = new HSSFWorkbook();
            Sheet sheet = workbook.createSheet();

            int rowCount = 0;
            int columnCount = 0;
            Row row = sheet.createRow(rowCount++);
            List<String> fieldNames = new ArrayList<String>();
            for (Iterator<String> iterator = data.get(0).keySet().iterator(); iterator.hasNext();) {
                String val;
                Cell cell = row.createCell(columnCount++);
                cell.setCellValue(val = iterator.next());
                fieldNames.add(val);
            }
            for (Map<String, Object> t : data) {
                row = sheet.createRow(rowCount++);
                columnCount = 0;
                for (String fieldName : fieldNames) {
                    Cell cell = row.createCell(columnCount);

                    Object value = t.get(fieldName);
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
                        } else if (value instanceof Boolean) {
                            cell.setCellValue(value.toString());
                        }
                    }
                    columnCount++;
                }
            }
            workbook.write(out);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
