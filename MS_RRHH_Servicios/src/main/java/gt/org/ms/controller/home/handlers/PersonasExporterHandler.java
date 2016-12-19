/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.home.handlers;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import gt.org.ms.api.requesting.AbstractRequestHandler;
import gt.org.ms.controller.dto.ExportPersonasRequestDto;
import gt.org.ms.controller.dto.PersonaRowsFileDto;
import gt.org.ms.api.utils.ExcelHelper;
import gt.org.ms.api.utils.FieldDto;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 *
 * @author ecracken
 */
@Service
public class PersonasExporterHandler extends AbstractRequestHandler<ExportPersonasRequestDto, ByteArrayOutputStream> {

    @Override
    public ByteArrayOutputStream execute(ExportPersonasRequestDto request) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        List<FieldDto> fields = ExcelHelper.getAnnotatedFieldsConfig(PersonaRowsFileDto.class);
        ExcelHelper.writeMapToExcel(out,
                new ArrayList<Map<String, Object>>(Collections2.transform(request.getPersonas(),
                        new Function<PersonaRowsFileDto, Map<String, Object>>() {
                    @Override
                    public Map<String, Object> apply(PersonaRowsFileDto f) {
                        try {
                            return ExcelHelper.introspect(f);
                        } catch (Exception ex) {
                            ex.printStackTrace(System.err);
                            return new HashMap<String, Object>();
                        }
                    }
                })), fields);

        return out;

    }

}
