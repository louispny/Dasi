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
 * @author lpigny
 */
public class DeconnecterSerialisation extends Serialisation {
    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response)
    {
        
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create(); 
        JsonObject obj = new JsonObject() ;
        obj.addProperty("deconnecter", request.getAttribute("deconnecter"));
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
