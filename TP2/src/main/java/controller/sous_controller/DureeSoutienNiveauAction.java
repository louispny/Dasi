/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.sous_controller;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Intervenant;
import metier.service.Service;

/**
 *
 * @author ylaatar
 */
public class DureeSoutienNiveauAction extends Action{
    @Override
    public void executer(HttpServletRequest request) {
        HttpSession session = request.getSession(true) ;
        Service service = new Service() ; 
        Intervenant intervenant = service.findIntervenant((Long)session.getAttribute("id")) ;
        HashMap<Integer, Long> stats2 = service.obtenirMinutesDeCoursParNiveau(intervenant) ;
        HashMap<String, Long> stats = service.obtenirMinutesDeCoursParMatiere(intervenant) ; 
        request.setAttribute("DureeSoutienMatiere", stats);
        request.setAttribute("DureeSoutienNiveau", stats2);

    }
}
