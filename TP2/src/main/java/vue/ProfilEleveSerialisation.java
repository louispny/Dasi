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
            System.out.println("test purement test");
            jsonUser.addProperty("id",user.getId()) ; 
            jsonUser.addProperty("nom",user.getNom()) ; 
            jsonUser.addProperty("prenom",user.getPrenom()) ; 
            jsonUser.addProperty("mail",user.getMail()) ; 
            jsonUser.addProperty("classe", user.getNiveau());
            container.addProperty("inscription", true) ; 
            container.add("eleve", jsonUser);
        }
        else 
        {
            container.addProperty("inscription", false);
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
