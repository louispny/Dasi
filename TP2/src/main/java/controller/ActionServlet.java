/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import controller.sous_controller.AuthentifierEleveAction;
import controller.sous_controller.AuthentifierUtilisateurAction;
import controller.sous_controller.InscrireEleveAction;

import dao.JpaUtil;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpSession;
import metier.modele.Eleve;
import metier.modele.Intervenant;
import vue.ProfilEleveSerialisation;
import vue.ProfilUtilisateurSerialisation;

/**
 *
 * @author lpigny
 */
@WebServlet(name = "ActionServlet", urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @Override
    public void init() throws ServletException {
        super.init();
        JpaUtil.creerFabriquePersistance();
        
    }

    @Override
    public void destroy() {
        JpaUtil.fermerFabriquePersistance();
        super.destroy();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession(true);
            
            /* TODO output your page here. You may use following sample code. */
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date date = new Date();
            
            String todo = request.getParameter("todo");
            
            System.out.println("[TEST] Appel de l’ActionServlet");
            System.out.println("Trace : todo = " + todo );
            switch(todo) {
                case "connecter" : {
                    new AuthentifierUtilisateurAction().executer(request);
                    new AuthentifierEleveAction().executer(request);
                    Intervenant interv = (Intervenant)request.getAttribute("user");
                    Eleve eleve = (Eleve)request.getAttribute("eleve") ; 
                    if ( interv != null) {
                        session.setAttribute("id", interv.getId());
                    }
                    
                    if ( eleve != null) {
                        session.setAttribute("id", eleve.getId()) ;
                        
                    }
                    new ProfilUtilisateurSerialisation().appliquer(request, response);
                    break;
                }
                case "inscrire" : {
                    
                    new InscrireEleveAction().executer(request) ; 
                    Eleve eleve = (Eleve)request.getAttribute("eleve") ; 
                    if ( eleve != null) {
                        session.setAttribute("id", eleve.getId()); 
                    }
                    new ProfilEleveSerialisation().appliquer(request, response);
                    break ; 
                }
            }
            
            /*
            System.out.println(todo);
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ActionServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ActionServlet at " + request.getContextPath() + "</h1>");
            out.println("<p> La date : " + sdf.format(date) + "</p>");
            out.println("</body>");
            out.println("</html>");
            */
            
            
            
            
            
            
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
