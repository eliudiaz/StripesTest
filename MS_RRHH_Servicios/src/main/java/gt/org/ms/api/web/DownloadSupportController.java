/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.api.web;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import gt.org.ms.api.global.exceptions.ExceptionsManager;
import gt.org.ms.controller.dto.ExportPersonasRequestDto;
import gt.org.ms.controller.dto.PersonaDto;
import gt.org.ms.controller.dto.PersonaRowsFileDto;
import gt.org.ms.controller.home.handlers.PersonaCompletarDatosHandler;
import gt.org.ms.controller.home.handlers.PersonasExporterHandler;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author edcracken
 */
public class DownloadSupportController {

    @Autowired
    PersonaCompletarDatosHandler completaDatosPersonaHandler;
    @Autowired
    PersonasExporterHandler exporter;

    public ByteArrayOutputStream generateDownload(List<PersonaDto> out) {
        return (ByteArrayOutputStream) exporter
                .handle(new ExportPersonasRequestDto(
                        new ArrayList(Collections2.transform(out,
                                new Function<PersonaDto, PersonaRowsFileDto>() {
                            @Override
                            public PersonaRowsFileDto apply(PersonaDto f) {
                                return completaDatosPersonaHandler.handle(f);
                            }
                        }))));
    }

    public void produceResponseContent(HttpServletResponse response, List<PersonaDto> content) {
        try {
            String mimeType = "application/octet-stream";
            response.setContentType(mimeType);
            ByteArrayOutputStream bOut = generateDownload(content);
            response.setHeader("Content-Disposition", String.format("inline; filename=out.xls"));
            bOut.writeTo(response.getOutputStream());
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
            throw ExceptionsManager.newInternalErrorException("exporting_error", ex.getMessage(), ex);
        }
    }

}
