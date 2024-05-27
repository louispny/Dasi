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
        unservice.initBD();
        
        
        
        ////////////// test du service envoiBilan
        Eleve eleve_1 = new Eleve("griguer", "mehdi", 3, "mehdigriguer2003@gmail.com", "mdptest");
        unservice.inscrireEleve(eleve_1, "0692155T"); // eleve et son code d'établissement*/
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

/*
-- Supprimer toutes les données de la table intervention
DELETE FROM intervention;

-- Insérer de nouvelles données avec l'ID de l'intervenant à 2
insert into intervention(id, bilan, date_debut, date_fin, detail, duree_en_minutes, note, eleve_id, intervenant_id, matiere_nom) values(1, 'pas mal', '2024-04-12 14:30:00.000', '2024-04-12 14:55:00.000', 'je veux progresser', 25, 4, 10, 2, 'histoire');
insert into intervention(id, bilan, date_debut, date_fin, detail, duree_en_minutes, note, eleve_id, intervenant_id, matiere_nom) values(2, 'eleve nul', '2024-04-13 09:00:00.000', '2024-04-13 09:40:00.000', 'je veux progresser', 40, 2, 51, 2, 'chimie');
insert into intervention(id, bilan, date_debut, date_fin, detail, duree_en_minutes, note, eleve_id, intervenant_id, matiere_nom) values(3, 'bon élève', '2024-04-14 11:15:00.000', '2024-04-14 11:45:00.000', 'je veux améliorer mes compétences', 30, 3, 10, 2, 'maths');
insert into intervention(id, bilan, date_debut, date_fin, detail, duree_en_minutes, note, eleve_id, intervenant_id, matiere_nom) values(4, 'peut mieux faire', '2024-04-15 13:00:00.000', '2024-04-15 13:35:00.000', 'besoin d aide', 35, 4, 51, 2, 'physique');
insert into intervention(id, bilan, date_debut, date_fin, detail, duree_en_minutes, note, eleve_id, intervenant_id, matiere_nom) values(5, 'très motivé', '2024-04-16 10:00:00.000', '2024-04-16 10:45:00.000', 'objectif: perfectionnement', 45, 5, 10, 2, 'français');
insert into intervention(id, bilan, date_debut, date_fin, detail, duree_en_minutes, note, eleve_id, intervenant_id, matiere_nom) values(6, 'satisfaisant', '2024-04-17 08:30:00.000', '2024-04-17 08:50:00.000', 'doit pratiquer davantage', 20, 2, 51, 2, 'geographie');
insert into intervention(id, bilan, date_debut, date_fin, detail, duree_en_minutes, note, eleve_id, intervenant_id, matiere_nom) values(7, 'excellente progression', '2024-04-18 14:00:00.000', '2024-04-18 14:50:00.000', 'travail acharné', 50, 5, 10, 2, 'anglais');
insert into intervention(id, bilan, date_debut, date_fin, detail, duree_en_minutes, note, eleve_id, intervenant_id, matiere_nom) values(8, 'en progrès', '2024-04-19 15:00:00.000', '2024-04-19 15:55:00.000', 'encore des efforts à fournir', 55, 3, 51, 2, 'svt');
insert into intervention(id, bilan, date_debut, date_fin, detail, duree_en_minutes, note, eleve_id, intervenant_id, matiere_nom) values(9, 'bonne participation', '2024-04-20 16:00:00.000', '2024-04-20 17:00:00.000', 'actif en classe', 60, 4, 10, 2, 'espagnol');

-- Sélectionner les 100 premières lignes de la table intervention
SELECT * FROM DASI.INTERVENTION FETCH FIRST 100 ROWS ONLY;
*/
