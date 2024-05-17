package vue;

import dao.JpaUtil;
import metier.service.Service;

public class JeuxdeTest {

    public static void main(String[] args) throws InterruptedException {
        JpaUtil.creerFabriquePersistance();

        Service unservice = new Service();
        unservice.initBD();

        /*
        ////////////// test du service inscrireEleve
        Eleve eleve_1 = new Eleve("griguer", "mehdi", 3, "mehdigriguer2003@gmail.com", "mdptest");
        unservice.inscrireEleve(eleve_1, "0692155T"); // eleve et son code d'établissement
        Eleve eleve_2 = new Eleve("perrigault", "simon", 6, "sim2004@gmail.com", "mdptest");
        unservice.inscrireEleve(eleve_2, "0692155T"); // eleve et son code d'établissement
        Eleve eleve_3 = new Eleve("sanchez", "lucas", 6, "lucas2003@gmail.com", "mdptest");
        unservice.inscrireEleve(eleve_3, "0692155V"); // eleve et son code d'établissement
        Eleve eleve_4 = new Eleve("gouineaud", "romane", 3, "mehdigriguer2003@gmail.com", "mdptest");
        unservice.inscrireEleve(eleve_4, "0692155T"); // eleve et son code d'établissement
        //////////////

        
        
        ////////////// test du service authentifierEleve
        Eleve eleve_1 = new Eleve("griguer", "mehdi", 3, "mehdigriguer2003@gmail.com", "mdptest");
        unservice.inscrireEleve(eleve_1, "0692155T"); // eleve et son code d'établissement
        Eleve eleve_2 = new Eleve("perrigault", "simon", 6, "sim2004@gmail.com", "mdptest");
        unservice.inscrireEleve(eleve_2, "0692155T"); // eleve et son code d'établissement
        
        unservice.authentifierEleve("mehdigriguer2003@gmail.com", "mdptest");
        unservice.authentifierEleve("mailfaux@gmail.com", "mdptest");
        unservice.authentifierEleve("sim2004@gmail.com", "mdpfaux");
        unservice.authentifierEleve("sim2004@gmail.com", "mdptest");
        //////////////

        
        
        ////////////// test du service authentifierIntervenant
        unservice.authentifierIntervenant("cr7@gmail.com", "mdpcr7");
        unservice.authentifierIntervenant("mailfaux@gmail.com", "mdplm10");
        unservice.authentifierIntervenant("lm10@gmail.com", "mdpfaux");
        unservice.authentifierIntervenant("lm10@gmail.com", "mdplm10");
        //////////////
        
        
        
        ////////////// test du service creerIntervention
        Eleve eleve_1 = new Eleve("griguer", "mehdi", 3, "mehdigriguer2003@gmail.com", "mdptest");
        unservice.inscrireEleve(eleve_1, "0692155T"); // eleve et son code d'établissement
        Eleve eleve = unservice.authentifierEleve("mehdigriguer2003@gmail.com", "mdptest");
        unservice.creerIntervention("français", eleve, "detail intervention");
        //////////////
        
        
        
        ////////////// test du service envoiBilan
        Eleve eleve_1 = new Eleve("griguer", "mehdi", 3, "mehdigriguer2003@gmail.com", "mdptest");
        unservice.inscrireEleve(eleve_1, "0692155T"); // eleve et son code d'établissement
        Eleve eleve = unservice.authentifierEleve("mehdigriguer2003@gmail.com", "mdptest");
        unservice.creerIntervention("français", eleve, "detail intervention");
        Thread.sleep(60000); // pause de 1 minute
        Intervenant intervenant = unservice.authentifierIntervenant("kb@gmail.com", "mdpkb");
        Intervention intervention_en_cours = unservice.getInterventionEnCours(intervenant);
        unservice.envoiBilan(intervention_en_cours, "bilan");
        //////////////
        
        
        
        ////////////// test du service envoiNote
        Eleve eleve_1 = new Eleve("griguer", "mehdi", 3, "mehdigriguer2003@gmail.com", "mdptest");
        unservice.inscrireEleve(eleve_1, "0692155T"); // eleve et son code d'établissement
        Eleve eleve_2 = new Eleve("perrigault", "simon", 6, "sim2004@gmail.com", "mdptest");
        unservice.inscrireEleve(eleve_2, "0692155T"); // eleve et son code d'établissement

        Intervention intervention_1 = unservice.creerIntervention("français", eleve_1, "detail intervention");
        Thread.sleep(60000); // pause de 1 minute
        unservice.envoiBilan(intervention_1, "bilan");
        unservice.envoiNote(intervention_1, 0);
        //////////////
        
        
        
        ////////////// test du service historiqueInterventionEleve
        Eleve eleve_1 = new Eleve("griguer", "mehdi", 3, "mehdigriguer2003@gmail.com", "mdptest");
        unservice.inscrireEleve(eleve_1, "0692155T"); // eleve et son code d'établissement
        Eleve eleve_2 = new Eleve("perrigault", "simon", 6, "sim2004@gmail.com", "mdptest");
        unservice.inscrireEleve(eleve_2, "0692155T"); // eleve et son code d'établissement

        Intervention intervention_1 = unservice.creerIntervention("français", eleve_1, "detail intervention");
        unservice.creerIntervention("maths", eleve_2, "detail intervention");
        
        Thread.sleep(60000); // pause de 1 minute
        unservice.envoiBilan(intervention_1, "bilan");
        unservice.envoiNote(intervention_1, 0);
        unservice.historiqueInterventionEleve(eleve_1);
        unservice.historiqueInterventionEleve(eleve_2);
        //////////////
        
        
        
        ////////////// test du service historiqueInterventionIntervenant
        Eleve eleve_1 = new Eleve("griguer", "mehdi", 3, "mehdigriguer2003@gmail.com", "mdptest");
        unservice.inscrireEleve(eleve_1, "0692155T"); // eleve et son code d'établissement
        Eleve eleve_2 = new Eleve("perrigault", "simon", 6, "sim2004@gmail.com", "mdptest");
        unservice.inscrireEleve(eleve_2, "0692155T"); // eleve et son code d'établissement

        Intervention intervention_1 = unservice.creerIntervention("français", eleve_1, "detail intervention");
        Thread.sleep(60000); // pause de 1 minute
        unservice.envoiBilan(intervention_1, "bilan");
        unservice.envoiNote(intervention_1, 0);

        Intervention intervention_2 = unservice.creerIntervention("maths", eleve_2, "detail intervention");
        Thread.sleep(60000); // pause de 1 minute
        unservice.envoiBilan(intervention_2, "bilan");
        unservice.envoiNote(intervention_2, 5);

        Intervenant intervenant_1 = unservice.authentifierIntervenant("kb@gmail.com", "mdpkb");
        if (intervenant_1 != null) {
            unservice.historiqueInterventionIntervenant(intervenant_1);
        }

        Intervenant intervenant_2 = unservice.authentifierIntervenant("cr7@gmail.com", "mdpcr7");
        if (intervenant_2 != null) {
            unservice.historiqueInterventionIntervenant(intervenant_2);
        }
        //////////////
        
        
        
        ////////////// test du service obtenirListeDesMatieres
        List<Matiere> listeMatieres = unservice.obtenirListeDesMatieres();
        for (Matiere matiere : listeMatieres) {
            System.out.println(matiere.getNom());
        }
        //////////////
        
        
        
        ////////////// test du service obtenirMinutesDeCoursParNiveau
        Eleve eleve_1 = new Eleve("griguer", "mehdi", 3, "mehdigriguer2003@gmail.com", "mdptest");
        unservice.inscrireEleve(eleve_1, "0692155T"); // eleve et son code d'établissement

        Intervention intervention_1 = unservice.creerIntervention("français", eleve_1, "detail intervention");
        Thread.sleep(60000); // pause de 1 minute
        unservice.envoiBilan(intervention_1, "bilan");
        unservice.envoiNote(intervention_1, 0);

        Intervenant intervenant_1 = unservice.authentifierIntervenant("kb@gmail.com", "mdpkb");
        if (intervenant_1 != null) {
            HashMap<Integer, Long> infos = unservice.obtenirMinutesDeCoursParNiveau(intervenant_1);
            for (Map.Entry<Integer, Long> entry : infos.entrySet()) {
            System.out.println("Niveau : " + entry.getKey() + ", Minutes de cours : " + entry.getValue());
            }
        }

        Intervenant intervenant_2 = unservice.authentifierIntervenant("cr7@gmail.com", "mdpcr7");
        if (intervenant_2 != null) {
            HashMap<Integer, Long> infos = unservice.obtenirMinutesDeCoursParNiveau(intervenant_2);
            for (Map.Entry<Integer, Long> entry : infos.entrySet()) {
            System.out.println("Niveau : " + entry.getKey() + ", Minutes de cours : " + entry.getValue());
            }
        }
        //////////////
        
        
        
        ////////////// test du service obtenirMinutesDeCoursParMatiere
        Eleve eleve_1 = new Eleve("griguer", "mehdi", 3, "mehdigriguer2003@gmail.com", "mdptest");
        unservice.inscrireEleve(eleve_1, "0692155T"); // eleve et son code d'établissement

        Intervention intervention_1 = unservice.creerIntervention("français", eleve_1, "detail intervention");
        Thread.sleep(60000); // pause de 1 minute
        unservice.envoiBilan(intervention_1, "bilan");
        unservice.envoiNote(intervention_1, 0);

        Intervenant intervenant_1 = unservice.authentifierIntervenant("kb@gmail.com", "mdpkb");
        if (intervenant_1 != null) {
            HashMap<String, Long> infos = unservice.obtenirMinutesDeCoursParMatiere(intervenant_1);
            for (Map.Entry<String, Long> entry : infos.entrySet()) {
                System.out.println("Matière : " + entry.getKey() + ", Minutes de cours : " + entry.getValue());
            }
        }

        Intervenant intervenant_2 = unservice.authentifierIntervenant("cr7@gmail.com", "mdpcr7");
        if (intervenant_2 != null) {
            HashMap<String, Long> infos = unservice.obtenirMinutesDeCoursParMatiere(intervenant_2);
            for (Map.Entry<String, Long> entry : infos.entrySet()) {
                System.out.println("Matière : " + entry.getKey() + ", Minutes de cours : " + entry.getValue());
            }
        }
        //////////////
        */

        JpaUtil.fermerFabriquePersistance();
    }
;
}
