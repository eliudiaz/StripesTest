/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.home;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import gt.org.isis.api.misc.exceptions.ExceptionsManager;
import gt.org.isis.controller.dto.ExportPersonasRequestDto;
import gt.org.isis.controller.dto.PersonaDto;
import gt.org.isis.controller.dto.PersonaRowsFileDto;
import gt.org.isis.controller.home.handlers.PersonaCompletarDatosHandler;
import gt.org.isis.controller.home.handlers.PersonasExporterHandler;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author eliud
 */
public class DownloadSupportController {

    @Autowired
    PersonaCompletarDatosHandler completaDatosPersonaHandler;
    @Autowired
    PersonasExporterHandler exporter;

    public void processDownload(List<PersonaRowsFileDto> out, HttpServletResponse response) {
        try {
            ByteArrayOutputStream r = (ByteArrayOutputStream) exporter
                    .handle(new ExportPersonasRequestDto(new ArrayList(Collections2.transform(out,
                            new Function<PersonaDto, PersonaRowsFileDto>() {
                        @Override
                        public PersonaRowsFileDto apply(PersonaDto f) {
                            return completaDatosPersonaHandler.handle(f);
                        }
                    }))));
            response.setContentType("application/x-download");
            response.setHeader("Content-Disposition", "attachment; filename=out.xls");
            byte[] buffer = new byte[r.size()];
            r.write(buffer);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(buffer);
            outputStream.flush();
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
            throw ExceptionsManager.newNotFound();
        }
    }

    public ByteArrayOutputStream processDownload(List<PersonaRowsFileDto> out) {
        return (ByteArrayOutputStream) exporter
                .handle(new ExportPersonasRequestDto(new ArrayList(Collections2.transform(out,
                        new Function<PersonaDto, PersonaRowsFileDto>() {
                    @Override
                    public PersonaRowsFileDto apply(PersonaDto f) {
                        return completaDatosPersonaHandler.handle(f);
                    }
                }))));
    }

    public void produceResponseContent(HttpServletResponse response, List<PersonaRowsFileDto> content) {
        try {
            String mimeType = "application/octet-stream";
            response.setContentType(mimeType);
            ByteArrayOutputStream bOut = processDownload(content);
            response.setHeader("Content-Disposition", String.format("inline; filename=out.xls"));
            bOut.writeTo(response.getOutputStream());
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
            throw ExceptionsManager.newInternalErrorException("exporting_error", ex.getMessage(), ex);
        }
    }

}
