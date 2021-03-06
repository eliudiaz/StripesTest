/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed.cracken.code.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ed.cracken.code.managers.IDsManager;
import ed.cracken.code.servlets.dto.Persona;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.Normalizer;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author eliud //
 */
@WebServlet(name = "PullsHandler", urlPatterns = {"/pull"})
public class PullsHandler extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            System.out.println("streaming..." + request.getParameter("sessionid"));
            String type = "application/json; charset=UTF-8";
            response.setContentType(type);
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Type", type);

            String session;
            if ((session = request.getParameter("sessionId")) == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
            IDsManager manager;
            if ((manager = (IDsManager) this.getServletContext()
                    .getAttribute("idsmanager")) == null
                    || manager.getIds().isEmpty()) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
            System.out.println(">> " + manager.getIds().toString());

            PrintWriter writer = response.getWriter();
            Persona p;

            if ((p = manager.getIds().get(session)) == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            } else {
                Gson g = new GsonBuilder().setPrettyPrinting().create();
                String j = Normalizer.normalize(g.toJson(p), Normalizer.Form.NFC);
                writer.write(j);
                System.out.println(">> sending >>" + j);
                manager.getIds().clear();
            }
            writer.flush();
            writer.close();

        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
