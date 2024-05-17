/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.sous_controller;

import javax.servlet.http.HttpServletRequest;
import metier.modele.Intervenant;
import metier.service.Service;
import modele.TestUtilisateur;

/**
 *
 * @author lpigny
 */
public class AuthentifierUtilisateurAction extends Action {
    @Override
    public void executer(HttpServletRequest request){
        String todo = request.getParameter("todo");
        Service service = new Service() ; 
        Intervenant interv = service.authentifierIntervenant(request.getParameter("login"), request.getParameter("password")) ;
        if (interv != null)
        {
            request.setAttribute("user", interv);
        }
        else 
        {
            request.setAttribute("user", null);
        }
        
        System.out.println("Ca fonctionne : "+ todo);
    }
    
}
