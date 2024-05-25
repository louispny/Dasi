/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Intervention;
import metier.modele.Matiere;
/**
 *
 * @author ylaatar
 */
public class HistoriqueEleveSerialisation extends Serialisation {
    @Override
    public void appliquer (HttpServletRequest request, HttpServletResponse response) {
        System.out.println("serialisation :");
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create(); 
        JsonObject container = new JsonObject() ;
        JsonArray interventionList = new JsonArray() ; 
        List<Intervention> liste = (List<Intervention>)request.getAttribute("historiqueEleve");
        if (liste != null)
        {
            for (Intervention intervention : liste)
            {
                JsonObject jsonIntervention = new JsonObject();
                jsonIntervention.addProperty("intervenantNom", intervention.getIntervenant().getNom());
                jsonIntervention.addProperty("intervenantPrenom", intervention.getIntervenant().getPrenom());
                jsonIntervention.addProperty("matiere", intervention.getMatiere().getNom());
                jsonIntervention.addProperty("duree", intervention.getDuree_en_minutes());
                jsonIntervention.addProperty("note", intervention.getNote());
                jsonIntervention.addProperty("bilan", intervention.getBilan());
                interventionList.add(jsonIntervention);
            }
            container.add("listeIntervention", interventionList);
            container.addProperty("valide", true);
        }
        else {
            container.addProperty("valide", false) ; 
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
