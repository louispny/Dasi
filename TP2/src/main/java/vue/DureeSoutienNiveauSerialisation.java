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
        JsonArray matieres = new JsonArray() ; 
        JsonArray data = new JsonArray() ;
        JsonArray niveau = new JsonArray() ; 
        JsonArray donnees = new JsonArray() ;
        HashMap<Integer, Long> map2 = (HashMap<Integer, Long>)request.getAttribute("DureeSoutienNiveau") ;
        HashMap<String, Long> map = (HashMap<String, Long>)request.getAttribute("DureeSoutienMatiere") ; 
        if (map != null && map2 != null) {
            for (Map.Entry<String, Long> entry : map.entrySet()) {
                matieres.add(entry.getKey());
                data.add(entry.getValue());
            } 
            String lvl = "Terminale" ; 
            for (Map.Entry<Integer, Long> entry : map2.entrySet()) {
                switch(entry.getKey()) {
                    case 1 : {
                        lvl = "Première" ; 
                        break ; 
                    }
                    case 2 : {
                        lvl = "Seconde" ;
                        break ; 
                    }
                    case 3 : {
                        lvl = "Troisieme" ;
                        break ; 
                    }
                    case 4 : {
                        lvl = "Quatrieme" ;
                        break ; 
                    }
                    case 5 : {
                        lvl = "Cinquieme" ; 
                        break ; 
                    }
                    case 6 : {
                        lvl = "Sixieme" ; 
                        break ;
                    }
                }
                niveau.add(lvl);
                donnees.add(entry.getValue());
            } 
            container.add("ClasseSoutienMatiere", matieres) ; 
            container.add("DureeSoutienMatiere", data) ;
            container.add("DureeSoutienNiveau", donnees) ; 
            container.add("ClasseSoutienNiveau", niveau) ;
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
