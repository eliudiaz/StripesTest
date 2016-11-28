/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.home.handlers;

import gt.org.isis.api.AbstractRequestHandler;
import gt.org.isis.controller.dto.ExportPersonasRequestDto;
import gt.org.isis.model.utils.ExcelHelper;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import org.springframework.stereotype.Service;

/**
 *
 * @author ecracken
 */
@Service
public class PersonasExporterHandler extends AbstractRequestHandler<ExportPersonasRequestDto, OutputStream> {

    @Override
    public OutputStream execute(ExportPersonasRequestDto request) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ExcelHelper.writeToExcel(out, request.getPersonas());

        return out;

    }

}
