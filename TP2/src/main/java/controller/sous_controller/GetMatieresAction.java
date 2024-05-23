/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.sous_controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import metier.modele.Matiere;
import metier.service.Service;

/**
 *
 * @author lpigny
 */
public class GetMatieresAction extends Action {
    @Override
    public void executer(HttpServletRequest request)
    {
        System.out.println("trace1");
        Service service = new Service();
        List<Matiere> list = service.obtenirListeDesMatieres();
        System.out.println(list);
        request.setAttribute("listeMatieres", list);
    }
}
