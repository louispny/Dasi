/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.sous_controller;

import javax.servlet.http.HttpServletRequest;
import metier.modele.Eleve;
import metier.modele.Intervenant;
import metier.service.Service;
/**
 *
 * @author lpigny
 */
public class AuthentifierUtilisateurAction extends Action {
    @Override
    public void executer(HttpServletRequest request){
        String todo = request.getParameter("todo");
        Service service = new Service() ;
        Eleve un_eleve = service.authentifierEleve(request.getParameter("login"), request.getParameter("password")) ;
        if (un_eleve != null)
        {
            request.setAttribute("eleve", un_eleve);
        }
        else 
        {
            request.setAttribute("eleve", null);
        }
        
        System.out.println("Ca fonctionne : "+ todo);
    }
    
}