/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.home;

import gt.org.isis.api.misc.exceptions.ExceptionsManager;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author eliud
 */
@Controller
public class FileDownloaderController extends DownloadSupportController {

    @Autowired
    FileContentQueue queue;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @RequestMapping(value = "/files", method = RequestMethod.GET)
    public void downloadFile(HttpServletResponse response,
            HttpServletRequest request,
            @RequestParam(value = "sessionId", required = false, defaultValue = "0") String sessionId) {
        try {
            String mimeType = "application/octet-stream";
            response.setContentType(mimeType);
            byte[] out = processDownload(queue.pull(sessionId));
            response.setHeader("Content-Disposition", String.format("inline; filename=out.xls"));
            response.setContentLength((int) out.length);
            ByteArrayInputStream in = new ByteArrayInputStream(out);

            FileCopyUtils.copy(in, response.getOutputStream());
        } catch (IOException ex) {
            throw ExceptionsManager.newNotFound();
        }
    }
}
