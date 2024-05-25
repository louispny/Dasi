/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.sous_controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Eleve;
import metier.modele.Intervention;
import metier.service.Service;

/**
 *
 * @author ylaatar
 */
public class GetHistoriqueEleveAction extends Action {
        @Override
        public void executer(HttpServletRequest request) {
            Service service = new Service() ; 
             HttpSession session = request.getSession(true);
            List<Intervention> liste ; 
            Eleve eleve = service.findEleve((Long)session.getAttribute("id")) ; 
            liste = service.historiqueInterventionEleve(eleve) ; 
            request.setAttribute("historiqueEleve", liste) ; 
            
        }
}
