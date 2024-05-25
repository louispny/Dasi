/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.sous_controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Intervention;
import metier.service.Service;

/**
 *
 * @author ylaatar
 */
public class EnvoyerBilanAction extends Action {
    @Override
    public void executer(HttpServletRequest request){
        System.out.println("Envoi du bilan");
        String bilan = (String) request.getParameter("bilan");
        System.out.println("bilan : " + bilan);
        HttpSession session = request.getSession(true);
        Long idInterv = (Long) session.getAttribute("intervention");
        System.out.println("Id intervention : " + idInterv);
        Service service = new Service();
        Intervention intervention = service.findIntervention(idInterv);
        System.out.println("Intervenant : " + intervention.getIntervenant());
        service.envoiBilan(intervention, bilan);
    }
}

