/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.model.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import static org.apache.commons.lang3.StringUtils.capitalize;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 *
 * @author eliud
 */
public class ExcelHelper {

    public static <T> void writeToExcel(OutputStream out, List<T> data) {
        HSSFWorkbook workbook = null;
        try {
            workbook = new HSSFWorkbook();
            Sheet sheet = workbook.createSheet();
            List<String> fieldNames = getFieldNamesForClass(data.get(0).getClass());
            int rowCount = 0;
            int columnCount = 0;
            Row row = sheet.createRow(rowCount++);
            for (String fieldName : fieldNames) {
                Cell cell = row.createCell(columnCount++);
                cell.setCellValue(fieldName);
            }
            Class<? extends Object> classz = data.get(0).getClass();
            for (T t : data) {
                row = sheet.createRow(rowCount++);
                columnCount = 0;
                for (String fieldName : fieldNames) {
                    Cell cell = row.createCell(columnCount);
                    Method method = null;
                    try {
                        method = classz.getMethod("get" + capitalize(fieldName));
                    } catch (NoSuchMethodException nme) {
                        method = classz.getMethod("is" + capitalize(fieldName));
                    }
                    Object value = method.invoke(t, (Object[]) null);
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
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
            }
        }
    }

    private static List<String> getFieldNamesForClass(Class<?> clazz) throws Exception {
        List<String> fieldNames = new ArrayList<String>();
        java.lang.reflect.Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fieldNames.add(fields[i].getName());
        }
        return fieldNames;
    }

}
