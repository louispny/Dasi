/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.sous_controller;

import javax.servlet.http.HttpServletRequest;
import metier.modele.Intervention;

/**
 *
 * @author ylaatar
 */
public class GetInfosEleveIntervention extends Action {
    @Override
    public void executer(HttpServletRequest request) {
        HttpSession session = 
        Intervention = (Intervention)session.getAttribute("intervention") ; 
    }
    
}
