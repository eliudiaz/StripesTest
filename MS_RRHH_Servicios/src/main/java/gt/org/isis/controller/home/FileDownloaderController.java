/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.home;

import gt.org.isis.api.misc.exceptions.ExceptionsManager;
import gt.org.isis.controller.dto.ExportPersonasRequestDto;
import gt.org.isis.controller.home.handlers.PersonasExporterHandler;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author eliud
 */
@Controller
public class FileDownloaderController {

    @Autowired
    FileContentQueue queue;
    @Autowired
    PersonasExporterHandler exporter;

    @RequestMapping(value = "/files/{id}", method = RequestMethod.GET)
    public void getFile(
            @PathVariable("id") Integer id,
            HttpServletResponse response) {
        try {
            ByteArrayOutputStream out = (ByteArrayOutputStream) exporter.handle(new ExportPersonasRequestDto(queue.pull(id)));
            response.setContentType("application/octet-stream");
            byte[] buffer = new byte[out.size()];
            out.write(buffer);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(buffer);
            outputStream.flush();
        } catch (IOException ex) {
            throw ExceptionsManager.newNotFound();
        }

    }
}
