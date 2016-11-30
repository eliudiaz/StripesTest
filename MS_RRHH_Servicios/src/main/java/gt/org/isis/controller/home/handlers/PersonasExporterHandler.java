/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.home.handlers;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import gt.org.isis.api.AbstractRequestHandler;
import gt.org.isis.controller.dto.ExportPersonasRequestDto;
import gt.org.isis.controller.dto.PersonaRowsFileDto;
import gt.org.isis.api.utils.ExcelHelper;
import gt.org.isis.api.utils.FieldDto;
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

    private final List<FieldDto> fields = ExcelHelper.getAnnotatedFieldsConfig(PersonaRowsFileDto.class);

    @Override
    public ByteArrayOutputStream execute(ExportPersonasRequestDto request) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

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
