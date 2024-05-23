/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.sous_controller;

import javax.servlet.http.HttpServletRequest;
import metier.modele.Eleve;
import metier.service.Service;
/**
 *
 * @author lpigny
 */
public class InscrireEleveAction extends Action {
    @Override
    public void executer(HttpServletRequest request){
        String todo = request.getParameter("todo");
        Service service = new Service() ; 
        Eleve un_eleve = new Eleve(request.getParameter("nom"), request.getParameter("prenom"), Integer.parseInt(request.getParameter("classe")), request.getParameter("email"), request.getParameter("password"));
        boolean inscription = service.inscrireEleve(un_eleve, request.getParameter("code")) ; 
        if (inscription)
        {
            request.setAttribute("eleve", un_eleve);
        }
        else 
        {
            request.setAttribute("eleve", null);
        }
        
        System.out.println("Ca fonctionne : "+ todo);
    }
    //0692155T
}
