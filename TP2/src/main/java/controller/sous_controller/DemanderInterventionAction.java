
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.sous_controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Eleve;
import metier.service.Service;
import metier.modele.Intervention;
/**
 *
 * @author lpigny
 */
public class DemanderInterventionAction extends Action {
    @Override
    public void executer(HttpServletRequest request){
        Service service = new Service() ; 
        Eleve eleve = (Eleve)request.getAttribute("eleve") ; 
        Intervention intervention = service.creerIntervention(request.getParameter("matiere"), eleve, request.getParameter("detail")) ; 
        if (intervention != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("intervention", intervention.getId());
            request.setAttribute("intervention", intervention);
        }
        else {
            request.setAttribute("intervention", null);
        }
    }
}