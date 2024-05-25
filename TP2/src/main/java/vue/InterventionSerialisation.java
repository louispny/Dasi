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
import metier.modele.Intervention;

/**
 *
 * @author lpigny
 */
public class InterventionSerialisation extends Serialisation{
    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response)
    {
        System.out.println("serialisation :");
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create(); 
        JsonObject container = new JsonObject() ;
        JsonObject jsonUser = new JsonObject() ; 
        Intervention intervention = (Intervention)request.getAttribute("intervention");
        switch(request.getParameter("todo")) {
            case "demandeIntervention" : {
                
                if (intervention != null)
                {
                    container.addProperty("valide", true);
                    jsonUser.addProperty("id", intervention.getId());
                }
                else 
                {
                    container.addProperty("valide", false);

                }
                
            }
            case "getIntervention" : {
                if (intervention == null)
                    container.addProperty("valide", false) ; 
                else
                {
                    container.addProperty("valide", true) ;  
                    jsonUser.addProperty("etablissement",intervention.getEleve().getEtablissement().getNom()) ;
                    jsonUser.addProperty("nom",intervention.getEleve().getNom()) ; 
                    jsonUser.addProperty("prenom", intervention.getEleve().getPrenom()) ; 
                    String level = "Terminale" ; 
                    switch(intervention.getEleve().getNiveau()) {
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
                    jsonUser.addProperty("classe", level) ;
                    jsonUser.addProperty("matiere", intervention.getMatiere().getNom());
                    jsonUser.addProperty("details", intervention.getDetail()) ; 
                }
                
            }
        }
        container.add("intervention", jsonUser);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out ;
        try {
            out = response.getWriter();
            out.println(gson.toJson(container));
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(ProfilUtilisateurSerialisation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
