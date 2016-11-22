/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed.cracken.code.servlets;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import ed.cracken.code.managers.IDsManager;
import ed.cracken.code.servlets.dto.Persona;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author eliud
 */
@WebServlet(name = "PushProcessor", urlPatterns = {"/pushProcessor"})
public class PushHandler extends HttpServlet {

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
        response.setContentType("application/json");
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
        String json = "";
        StringBuilder sb = new StringBuilder();
        while ((json = br.readLine()) != null) {
            sb.append(json);
        }
        br.close();

        System.out.println(">> " + sb.toString());
        JsonReader reader = new JsonReader(new StringReader(sb.toString()));
        reader.setLenient(true);
        Persona persona = new Gson().fromJson(reader, Persona.class);
        IDsManager idsManager;
        if ((idsManager = (IDsManager) request.getServletContext().getAttribute("idsmanager")) == null) {
            idsManager = new IDsManager();
            request.getServletContext().setAttribute("idsmanager", idsManager);
        }
        idsManager.getIds().put(persona.getSession(), persona);

        response.setStatus(HttpServletResponse.SC_ACCEPTED);

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
