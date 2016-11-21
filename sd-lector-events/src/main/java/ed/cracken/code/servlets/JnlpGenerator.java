/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed.cracken.code.servlets;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author eliud
 */
@WebServlet(name = "JnlpGenerator", urlPatterns = {"/jnlp"})
public class JnlpGenerator extends HttpServlet {

    private Mustache template;
    private String jnlpFile;

    @Override
    public void init() throws ServletException {
        super.init();
        MustacheFactory mf = new DefaultMustacheFactory();
        template = mf.compile(jnlpFile = "lector.jnlp");
    }

    private String getCurrentPath(HttpServletRequest request) {
        String uri = request.getScheme() + "://"
                + // "http" + "://
                request.getServerName()
                + // "myhost"
                ":"
                + // ":"
                request.getServerPort()
                + // "8080"
                request.getRequestURI()
                + // "/people"
                "?"
                + // "?"
                request.getQueryString();       // "lastname=Fox&age=30"
        return uri;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse aResponse) throws ServletException, IOException {
        JnlpModel model = new JnlpModel();
        model.codeBaseUrl = getCurrentPath(req);
        model.jnlpFile = this.jnlpFile;
        model.pushUrl = System.getProperty("SD_PUSH_URL") != null
                ? System.getProperty("SD_PUSH_URL") : "localhost:41825/MS_RRHH";
        model.sessionId = req.getParameter("sessionId");

        aResponse.setContentType("application/x-java-jnlp-file");
        template.execute(new PrintWriter(System.out), model);

        template.execute(aResponse.getWriter(), model);

    }
}
