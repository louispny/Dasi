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
 * @author lpigny
 */
public class EnvoyerNoteAction extends Action {
    
    @Override
    public void executer(HttpServletRequest request) {
        System.out.println("Envoi de la note");
        int note = (int) request.getAttribute("note");
        HttpSession session = request.getSession(true);
        Long idInterv = (Long) session.getAttribute("intervention");
        Service service = new Service();
        Intervention intervention = service.findIntervention(idInterv);
        service.envoiNote(intervention, note);

    }
    
}
