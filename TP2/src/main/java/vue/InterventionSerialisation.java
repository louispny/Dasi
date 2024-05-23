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
        if (intervention != null)
        {
            container.addProperty("valide", true);
            container.addProperty("redirectURL", "") ; 
            jsonUser.addProperty("id", intervention.getId());
        }
        else 
        {
            container.addProperty("valide", false);
            
        }
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
