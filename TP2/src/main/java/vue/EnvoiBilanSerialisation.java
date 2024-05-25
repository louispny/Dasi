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

/**
 *
 * @author ylaatar
 */
public class EnvoiBilanSerialisation extends Serialisation {
    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) {
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create(); 
        JsonObject obj = new JsonObject() ;
        obj.addProperty("validation", Boolean.TRUE);
        response.setContentType("application/json;charset=UTF-8");
            PrintWriter out ;
            try {
                out = response.getWriter();
                out.println(gson.toJson(obj));
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(ProfilUtilisateurSerialisation.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
