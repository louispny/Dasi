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
/**
 *
 * @author lpigny
 */
public class GetInfosEleve extends Action {
    @Override
        
    public void executer(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Service service = new Service() ;
        Eleve eleve = service.findEleve((Long)session.getAttribute("id"));
    }
}