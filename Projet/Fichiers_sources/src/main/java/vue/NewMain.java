package vue;

import dao.JpaUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import metier.service.Service;
import metier.modele.Eleve;
import metier.modele.Intervenant;
import metier.modele.Intervention;
import metier.modele.Matiere;

public class NewMain {

    public static void main(String[] args) throws InterruptedException {
        JpaUtil.creerFabriquePersistance();

        Service unservice = new Service();
        //unservice.initBD();
        
        
        
        ////////////// test du service envoiBilan
        Eleve eleve_1 = new Eleve("griguer", "mehdi", 3, "mehdigriguer2003@gmail.com", "mdptest");
        unservice.inscrireEleve(eleve_1, "0692155T"); // eleve et son code d'établissement
        /*
        Eleve eleve = unservice.authentifierEleve("mehdigriguer2003@gmail.com", "mdptest");
        unservice.creerIntervention("français", eleve, "detail intervention");
        Thread.sleep(60000); // pause de 1 minute
        Intervenant intervenant = unservice.authentifierIntervenant("kb@gmail.com", "mdpkb");
        Intervention intervention_en_cours = unservice.getInterventionEnCours(intervenant);
        unservice.envoiBilan(intervention_en_cours, "bilan");
        //////////////*/

        JpaUtil.fermerFabriquePersistance();
    }
;
}
