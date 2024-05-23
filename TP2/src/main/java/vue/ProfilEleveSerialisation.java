/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Eleve;
/**
 *
 * @author lpigny
 */
public class ProfilEleveSerialisation extends Serialisation {
    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("serialisation :");
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create(); 
        JsonObject container = new JsonObject() ;
        JsonObject jsonUser = new JsonObject() ; 
        Eleve user = (Eleve)request.getAttribute("eleve");
        if (user != null)
        {
            switch(request.getParameter("todo")){
                case "connexion" : {
                    container.addProperty("connexion", true) ;
                    break ; 
                }
                case "inscription" : {
                    container.addProperty("inscription", true) ;
                    break ; 
                }
                case "getEleve" : {
                    container.addProperty("getEleve", true) ; 
                    break ;
                }
            }
            jsonUser.addProperty("id",user.getId()) ; 
            jsonUser.addProperty("nom",user.getNom()) ; 
            jsonUser.addProperty("prenom",user.getPrenom()) ; 
            jsonUser.addProperty("mail",user.getMail()) ; 
            String level = "Terminale" ; 
            switch(user.getNiveau()) {
                case 1 : {
                    level = "Premiere" ;
                    break ;
                }
                case 2 : {
                    level = "Seconde" ; 
                    break ;
                }
                case 3 : {
                    level = "Troisieme" ; 
                    break ;
                }
                case 4 : {
                    level = "Quatrieme" ; 
                    break ;
                }
                case 5 : {
                    level = "Cinquieme" ; 
                    break ;
                }
                case 6 : {
                    level = "Sixieme" ; 
                    break ;
                }
            }
            jsonUser.addProperty("classe", level); 
            if ("connexion".equals(request.getParameter("todo"))) {
                container.add("utilisateur", jsonUser);
            }
            else container.add("eleve", jsonUser);
        }
        else 
        {
            if (user != null)
            {   
            switch(request.getParameter("todo")){
                case "connexion" : 
                    {
                    container.addProperty("connexion", false) ;
                    break ;
                    }
                case "inscription" : 
                    {
                    container.addProperty("inscription", false) ;
                    break ;
                    }
                case "getEleve" : 
                    {
                    container.addProperty("getEleve", false) ; 
                    break ;
                    }
            }
            }
        }
        response.setContentType("application/json;charset=UTF-8");
            PrintWriter out ;
            try {
                out = response.getWriter();
                out.println(gson.toJson(container));
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(ProfilEleveSerialisation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

        
