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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ylaatar
 */
public class DureeSoutienNiveauSerialisation extends Serialisation {
    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("serialisation :");
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create(); 
        JsonObject container = new JsonObject() ;
        JsonArray classes = new JsonArray() ; 
        JsonArray data = new JsonArray() ;
        HashMap<String, Long> map = (HashMap<String, Long>)request.getAttribute("DureeSoutienNiveau") ; 
        if (map != null) {
            for (Map.Entry<String, Long> entry : map.entrySet()) {
                classes.add(entry.getKey());
                data.add(entry.getValue());
            } 
            container.add("ClasseSoutienNiveau", classes) ; 
            container.add("DureeSoutienNiveau", data) ;
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