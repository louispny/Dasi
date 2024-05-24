/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.sous_controller;

import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Eleve;
import metier.modele.Intervention;
import metier.service.Service;

/**
 *
 * @author ylaatar
 */
public class GetInfosEleveIntervention extends Action {
    @Override
    public void executer(HttpServletRequest request) {
            
        HttpSession session = request.getSession(true);
        Service service = new Service() ; 
        Intervention intervention = service.findIntervention((Long)session.getAttribute("intervention")) ;
        request.setAttribute("intervention", intervention) ;  
    }
    
}
