/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.sous_controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Intervenant;
import metier.service.Service;
/**
 *
 * @author lpigny
 */
public class GetInfosIntervenant extends Action {
    @Override
        
    public void executer(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Service service = new Service() ;
        System.out.println("recherche par id");
        System.out.println(session.getAttribute("id"));
        Intervenant intervenant = service.findIntervenant((Long)session.getAttribute("id"));
        System.out.println("affichage des infos");       
        System.out.println(session.getAttribute("id"));
        System.out.println(intervenant.getNom());
        System.out.println("------");
        request.setAttribute("user", intervenant);
    }
}