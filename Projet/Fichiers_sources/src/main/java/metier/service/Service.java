package metier.service;

import com.google.maps.model.LatLng;
import dao.EducNetApi;
import dao.EleveDao;
import dao.EtablissementDao;
import dao.GeoNetApi;
import dao.IntervenantDao;
import dao.JpaUtil;
import dao.MatiereDao;
import dao.InterventionDao;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import metier.modele.Eleve;
import metier.modele.Etablissement;
import metier.modele.Etudiant;
import metier.modele.Enseignant;
import metier.modele.Autre;
import metier.modele.Intervenant;
import metier.modele.Intervention;
import metier.modele.Matiere;

public class Service {

    public void initBD() {

        MatiereDao matiereDao = new MatiereDao();
        IntervenantDao intervenantDao = new IntervenantDao();

        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();

            Matiere matiereFrancais = new Matiere("Français");
            matiereDao.creer(matiereFrancais);
            Matiere matiereMaths = new Matiere("Maths");
            matiereDao.creer(matiereMaths);
            Matiere matierePhysique = new Matiere("Physique");
            matiereDao.creer(matierePhysique);
            Matiere matiereChimie = new Matiere("Chimie");
            matiereDao.creer(matiereChimie);
            Matiere matiereHistoire = new Matiere("Histoire");
            matiereDao.creer(matiereHistoire);
            Matiere matiereGeographie = new Matiere("Geographie");
            matiereDao.creer(matiereGeographie);
            Matiere matiereSVT = new Matiere("Svt");
            matiereDao.creer(matiereSVT);
            Matiere matiereAnglais = new Matiere("Anglais");
            matiereDao.creer(matiereAnglais);
            Matiere matiereEspagnol = new Matiere("Espagnol");
            matiereDao.creer(matiereEspagnol);
            Matiere matiereAllemand = new Matiere("Allemand");
            matiereDao.creer(matiereAllemand);
            Matiere matiereItalien = new Matiere("Italien");
            matiereDao.creer(matiereItalien);
            System.out.println("Création des matières dans la bd.");
            Etudiant intervenant_1 = new Etudiant("benzema", "karim", 6, 2, "kb@gmail.com", "mdpkb");
            intervenant_1.setUniversite("Sorbonne");
            intervenant_1.setSpecialite("langues contemporaines");
            intervenantDao.creer(intervenant_1);
            Enseignant intervenant_2 = new Enseignant("ronaldo", "cristiano", 4, 0, "cr7@gmail.com", "mdpcr7");
            intervenant_2.setType_etablissement("Lycée");
            intervenantDao.creer(intervenant_2);
            Autre intervenant_3 = new Autre("messi", "lionel", 5, 1, "lm10@gmail.com", "mdplm10");
            intervenant_3.setActivite("Retraité");
            intervenantDao.creer(intervenant_3);
            Enseignant enseignant1 = new Enseignant("Doe", "John", 5, 1, "john.doe@example.com", "password");
            enseignant1.setType_etablissement("Collège");
            intervenantDao.creer(enseignant1);

            Enseignant enseignant2 = new Enseignant("Smith", "Jane", 4, 0, "jane.smith@example.com", "password");
            enseignant2.setType_etablissement("Lycée");
            intervenantDao.creer(enseignant2);

            Enseignant enseignant3 = new Enseignant("Johnson", "Michael", 3, 2, "michael.johnson@example.com", "password");
            enseignant3.setType_etablissement("Collège");
            intervenantDao.creer(enseignant3);

            Enseignant enseignant4 = new Enseignant("Williams", "Emily", 2, 1, "emily.williams@example.com", "password");
            enseignant4.setType_etablissement("Lycée");
            intervenantDao.creer(enseignant4);

            Enseignant enseignant5 = new Enseignant("Brown", "David", 1, 0, "david.brown@example.com", "password");
            enseignant5.setType_etablissement("Collège");
            intervenantDao.creer(enseignant5);
            System.out.println("Création des intervenants dans la bd.");

            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Erreur dans l'initialisation de la bd.");
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance(); // dans tous les cas, on ferme l'entity manager
        }
    }

    public boolean inscrireEleve(Eleve eleve, String para_uai) {

        boolean resultat = true;
        EleveDao eleveDao = new EleveDao();

        Etablissement etab;
        EtablissementDao etablissementDao = new EtablissementDao();

        int nbElevesInscrits;

        try {
            JpaUtil.creerContextePersistance();

            // vérifier que le mail de l'élève n'est pas déjà dans notre bd
            if (eleveDao.findByMail(eleve.getMail()) != null) {
                System.out.println("L'adresse e-mail " + eleve.getMail() + " est déjà enregistrée dans la base de données.");
                resultat = false;
            } else {
                etab = etablissementDao.findByUai(para_uai);
                System.out.println("Utilisation d'EtablissementDao pour trouver un établissement par son code uai dans notre bd.");

                if (etab != null) {
                    System.out.println("Etablissement " + etab.getNom() + " existe déjà dans notre bd.");
                } else {
                    System.out.println("Etablissement n'existe pas déjà dans notre bd, on le cherche en ligne avec EducNetApi.");
                    EducNetApi api = new EducNetApi();
                    List<String> result = api.getInformationCollege(para_uai);

                    if (result == null) {
                        result = api.getInformationLycee(para_uai);
                    }

                    if (result != null) {
                        System.out.println("Etablissement " + result.get(1) + " trouvé en ligne avec EducNetApi.");
                        etab = new Etablissement(result.get(0), result.get(1));
                        etab.setSecteur(result.get(2));
                        etab.setCodeCommune(result.get(3));
                        etab.setNomCommune(result.get(4));
                        etab.setCodeDepartement(result.get(5));
                        etab.setNomDepartement(result.get(6));
                        etab.setAcademie(result.get(7));
                        etab.setIps(result.get(8));
                        LatLng coordGPS = GeoNetApi.getLatLng(etab.getCodeCommune());
                        etab.setLatitude(coordGPS.lat);
                        etab.setLongitude(coordGPS.lng);
                    } else {
                        System.out.println("Etablissement de " + eleve.getNom() + " " + eleve.getPrenom() + " n'existe pas.");
                        resultat = false;
                    }
                }

                if (resultat == true) {
                    JpaUtil.ouvrirTransaction();

                    nbElevesInscrits = etab.getNbEnfantsInscrits();
                    etab.setNbEnfantsInscrits(nbElevesInscrits + 1);

                    if (etablissementDao.findByUai(para_uai) == null) // étab n'est pas dans ma bd, il faut le crée
                    {
                        etablissementDao.creerEtablissement(etab); // sauvegarde dans la BD (on a rendu l'établissement persistant)
                        System.out.println("Utilisation d'EtablissementDao pour créer l'établissement " + etab.getNom() + " dans la bd.");
                    }

                    eleve.setEtablissement(etab); // on associe l'établissement à l'élève
                    eleveDao.creer(eleve); // sauvegarde dans la BD (on a rendu l'élève persistant)
                    System.out.println("Utilisation d'EleveDao pour créer l'élève " + eleve.getNom() + " " + eleve.getPrenom() + " dans la bd, après l'avoir associé avec son établissement.");

                    JpaUtil.validerTransaction();
                }
            }
        } catch (Exception ex) {
            System.err.println("Erreur dans l'inscription de l'élève : " + eleve.getNom() + " " + eleve.getPrenom() + ".");
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            resultat = false;
        } finally {
            JpaUtil.fermerContextePersistance();
        }

        return resultat;
    }

    public Eleve authentifierEleve(String mail, String motDePasse) {

        EleveDao eleveDao = new EleveDao();
        Eleve e;
        JpaUtil.creerContextePersistance();

        e = eleveDao.findByMail(mail);
        System.out.println("Utilisation d'EleveDao pour trouver un élève par son mail dans notre bd.");

        if (e == null) // mail faux
        {
            System.out.println("Aucun compte associé à cette adresse mail : " + mail);
        } else {
            if (!motDePasse.equals(e.getMotDePasse())) // mdp faux
            {
                System.out.println("Mail existe mais mdp faux.");
                e = null;
            }
        }

        if (e != null) {
            System.out.println("Bienvenue sur votre compte " + e.getPrenom() + ".");
        }

        JpaUtil.fermerContextePersistance();
        return e;
    }

    public Eleve findEleve(Long id) {

        EleveDao eleveDao = new EleveDao();
        Eleve e;
        JpaUtil.creerContextePersistance();

        e = eleveDao.findByID(id);

        if (e == null) // id faux
        {
            System.out.println("Aucun compte associé à cet id : " + id);
        }

        JpaUtil.fermerContextePersistance();
        return e;
    }
    
    public Intervenant findIntervenant(Long id) {
        IntervenantDao intervenantDao = new IntervenantDao() ; 
        Intervenant i ; 
        JpaUtil.creerContextePersistance();
        i = intervenantDao.findByID(id) ; 
        if (i == null) //id faux
        {
            System.out.println("Aucun compte associé à cet id : " + id) ; 
        }
        JpaUtil.fermerContextePersistance();
        return i ; 
    }

    public Intervention findIntervention(Long id) {
        InterventionDao interventionDao = new InterventionDao() ; 
        Intervention i ; 
        JpaUtil.creerContextePersistance();
        i = interventionDao.findById(id) ; 
        if (i == null) //id faux
        {
            System.out.println("Aucun compte associé à cet id : " + id) ; 
        }
        JpaUtil.fermerContextePersistance();
        return i ; 
    }

    public Intervenant authentifierIntervenant(String mail, String motDePasse) {

        IntervenantDao intervenantDao = new IntervenantDao();
        Intervenant i;
        JpaUtil.creerContextePersistance();

        i = intervenantDao.findByMail(mail);
        System.out.println("Utilisation d'IntervenantDao pour trouver un intervenant par son mail dans notre bd.");

        if (i == null) // mail faux
        {
            System.out.println("Aucun compte associé à cette adresse mail : " + mail);
        } else {
            if (!motDePasse.equals(i.getMotDePasse())) // mdp faux
            {
                System.out.println("Mail existe mais mdp faux.");
                i = null;
            }
        }

        if (i != null) {
            System.out.println("Bienvenue sur votre compte " + i.getPrenom() + ".");
        }

        JpaUtil.fermerContextePersistance();
        return i;
    }

    // if intervenant nulle => pas d'intervention
    // if date_debut non nulle et date_fin nulle => intervention en cours
    // if duree_en_minutes is not nulle => intervention finie
    public Intervention creerIntervention(String para_matiere, Eleve para_eleve, String para_detail) {

        Intervention intervention = null;
        InterventionDao interventionDao = new InterventionDao();
        IntervenantDao intervenantDao = new IntervenantDao();
        MatiereDao matiereDao = new MatiereDao();
        Intervenant intervenant;
        Matiere matiere;

        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();

            matiere = matiereDao.findById(para_matiere); // vérif existence dans ma bd
            System.out.println("Utilisation de MatiereDao pour trouver la matière par son id dans notre bd.");
            intervention = new Intervention(matiere, para_eleve, para_detail);

            interventionDao.creer(intervention); // sauvegarde dans la BD (on a rendu l'intervention persistante)
            System.out.println("Utilisation d'InterventionDao pour créer l'intervention en " + intervention.getMatiere().getNom() + " de " + intervention.getEleve().getPrenom() + " dans la bd.");

            intervenant = intervenantDao.findByNiveauANDisFREE(para_eleve.getNiveau());
            System.out.println("Utilisation d'IntervenantDao pour trouver, par son niveau, un intervenant libre dans notre bd.");

            if (intervenant == null) {
                System.out.println("Aucun intervenant disponible, refaire demande plus tard.");
            } else {
                Date maintenant = new Date();
                intervention.setDate_debut(maintenant);
                intervention.setIntervenant(intervenant);
                interventionDao.maj(intervention);
                System.out.println("Utilisation d'InterventionDao pour mettre à jour l'intervention en lançant la date de début et en rajoutant l'intervenant : " + intervention.getIntervenant().getPrenom() + " " + intervention.getIntervenant().getNom() + ".");

                intervenant.setNbInterventionFaite(intervenant.getNbInterventionFaite() + 1);
                intervenant.setDispo(false);
                intervenantDao.maj(intervenant);
                System.out.println("Utilisation d'IntervenantDao pour mettre à jour le statut de l'intervenant : " + intervention.getIntervenant().getPrenom() + " " + intervention.getIntervenant().getNom() + ". Il est maintenant indisponible.");
            }

            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            System.err.println("La matière " + para_matiere + " n'existe pas dans notre bd.");
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }

        return intervention;
    }

    // avant de lancer envoiBilan vérifier que l'intervention est encore en cours
    public boolean envoiBilan(Intervention intervention, String bilan) {
        boolean resultat;
        InterventionDao interventionDao = new InterventionDao();
        IntervenantDao intervenantDao = new IntervenantDao();
        Intervenant intervenant;

        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();

            intervention.setBilan(bilan);
            Date maintenant = new Date();
            intervention.setDate_fin(maintenant);
            LocalDateTime debut = intervention.getDate_debut().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            LocalDateTime fin = intervention.getDate_fin().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            intervention.setDuree_en_minutes(ChronoUnit.MINUTES.between(debut, fin));
            interventionDao.maj(intervention);
            System.out.println("Utilisation d'InterventionDao pour mettre à jour l'intervention en rajoutant le bilan et la durée de l'intervention qui est de : " + intervention.getDuree_en_minutes() + " minutes.");

            intervenantDao.envoiMail(intervention.getIntervenant().getMail(), intervention.getEleve().getMail(), "Bilan de l'intervention en " + intervention.getMatiere().getNom() + ".", intervention.getBilan());
            intervenant = intervention.getIntervenant();
            intervenant.setDispo(true);
            intervenantDao.maj(intervenant);
            System.out.println("Utilisation d'IntervenantDao pour mettre à jour le statut de l'intervenant : " + intervention.getIntervenant().getPrenom() + " " + intervention.getIntervenant().getNom() + ". Il est maintenant disponible.");

            resultat = true;
            JpaUtil.validerTransaction();
            
        } catch (Exception ex) {
            System.err.println("Erreur dans l'envoi du mail bilan par l'intervenant.");
            resultat = false;
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
        }

        return resultat;
    }

    // avant de lancer envoiNote vérifier que l'intervention est finie
    public boolean envoiNote(Intervention intervention, int note) {
        boolean resultat;
        InterventionDao interventionDao = new InterventionDao();

        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();

            intervention.setNote(note);
            interventionDao.maj(intervention);
            System.out.println("Utilisation d'InterventionDao pour mettre à jour la note donnée par l'élève : " + intervention.getEleve().getPrenom() + " " + intervention.getEleve().getNom() + ". La note est : " + intervention.getNote());

            resultat = true;
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            System.err.println("Erreur dans l'envoi de la note de l'intervention par l'élève.");
            resultat = false;
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
        }

        return resultat;
    }

    public List<Intervention> historiqueInterventionEleve(Eleve eleve) {
        List<Intervention> resultat;
        InterventionDao interventionDao = new InterventionDao();

        JpaUtil.creerContextePersistance();

        resultat = interventionDao.HistoriqueEleve(eleve);
        System.out.println("Utilisation d'InterventionDao pour accéder à l'historique de l'élève : " + eleve.getPrenom() + " " + eleve.getNom());

        if (resultat == null) {
            System.out.println("L'élève " + eleve.getPrenom() + " " + eleve.getNom() + " n'a toujours pas fait d'intervention.");
        } else {
            System.out.println("Historique d'interventions de l'élève " + eleve.getPrenom() + " " + eleve.getNom() + " :");
            for (Intervention element : resultat) {
                if (element.getDate_debut() == null) {
                    System.out.println("Intervention en " + element.getMatiere().getNom() + " non réalisée.");
                } else {
                    System.out.println("Intervention en " + element.getMatiere().getNom() + " avec " + element.getIntervenant().getPrenom() + " " + element.getIntervenant().getNom() + " le " + element.getDate_debut() + ".");
                }
            }
        }

        JpaUtil.fermerContextePersistance();

        return resultat;
    }

    public List<Intervention> historiqueInterventionIntervenant(Intervenant intervenant) {
        List<Intervention> resultat;
        InterventionDao interventionDao = new InterventionDao();

        JpaUtil.creerContextePersistance();

        resultat = interventionDao.HistoriqueIntervenant(intervenant);
        System.out.println("Utilisation d'InterventionDao pour accéder à l'historique de l'intervenant : " + intervenant.getPrenom() + " " + intervenant.getNom());

        if (resultat == null) {
            System.out.println("L'intervenant " + intervenant.getPrenom() + " " + intervenant.getNom() + " n'a toujours pas fait d'intervention.");
        } else {
            System.out.println("Historique d'interventions de l'intervenant " + intervenant.getPrenom() + " " + intervenant.getNom() + " :");
            for (Intervention element : resultat) {
                System.out.println("Intervention en " + element.getMatiere().getNom() + " avec " + element.getEleve().getPrenom() + " " + element.getEleve().getNom() + " le " + element.getDate_debut() + ".");
            }
        }

        JpaUtil.fermerContextePersistance();

        return resultat;
    }
    
    public Intervention getInterventionEnCours(Intervenant intervenant){
        Intervention resultat;
        IntervenantDao intervenantDao = new IntervenantDao();
        
        JpaUtil.creerContextePersistance();
        resultat = intervenantDao.DerniereIntervention(intervenant);
        if (resultat == null) {
            System.out.println("L'intervenant " + intervenant.getPrenom() + " " + intervenant.getNom() + " n'a toujours pas fait d'intervention.");
        } else {
            System.out.println("Sa dernière intervention est avec " + resultat.getEleve().getNom() + " " + resultat.getEleve().getPrenom() + " en " + resultat.getMatiere().getNom() + ".");
        }
        return resultat;
    }

    public List<Matiere> obtenirListeDesMatieres() {
        MatiereDao matiereDao = new MatiereDao();
        List<Matiere> resultat;

        try {
            JpaUtil.creerContextePersistance();
            resultat = matiereDao.getAllMatieres();
            System.out.println("Utilisation de MatiereDao pour accéder à la liste des matières de la bd.");
        } catch (Exception ex) {
            System.err.println("Erreur dans l'extraction des matières.");
            ex.printStackTrace();
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    public HashMap<Integer, Long> obtenirMinutesDeCoursParNiveau(Intervenant intervenant) {
        HashMap<Integer, Long> resultat = new HashMap<>();
        for (int i = 6; i >= 0; i--) { // init de la hashmap
            resultat.put(i, 0L);
        }

        List<Intervention> infos;
        InterventionDao interventionDao = new InterventionDao();

        JpaUtil.creerContextePersistance();

        infos = interventionDao.HistoriqueIntervenant(intervenant);
        System.out.println("Utilisation d'InterventionDao pour accéder à l'historique de l'intervenant : " + intervenant.getPrenom() + " " + intervenant.getNom() + " pour faire ses stats.");

        int Cle_NiveauEleve;
        long Valeur_Temps;
        for (Intervention element : infos) {
            Cle_NiveauEleve = element.getEleve().getNiveau();
            Valeur_Temps = resultat.get(Cle_NiveauEleve);
            resultat.put(Cle_NiveauEleve, Valeur_Temps + element.getDuree_en_minutes());
        }

        JpaUtil.fermerContextePersistance();

        return resultat;
    }

    public HashMap<String, Long> obtenirMinutesDeCoursParMatiere(Intervenant intervenant) {
        HashMap<String, Long> resultat = new HashMap<>();

        List<Intervention> infos;
        InterventionDao interventionDao = new InterventionDao();

        List<Matiere> listeMatieres = this.obtenirListeDesMatieres();
        if (listeMatieres != null) {
            for (Matiere matiere : listeMatieres) { // init de la hashmap
                resultat.put(matiere.getNom(), 0L);
            }
        }

        JpaUtil.creerContextePersistance();

        infos = interventionDao.HistoriqueIntervenant(intervenant);
        System.out.println("Utilisation d'InterventionDao pour accéder à l'historique de l'intervenant : " + intervenant.getPrenom() + " " + intervenant.getNom() + " pour faire ses stats.");

        long Valeur_Temps;
        String Cle_Matiere;
        for (Intervention element : infos) {
            Cle_Matiere = element.getMatiere().getNom();
            Valeur_Temps = resultat.get(Cle_Matiere);
            resultat.put(Cle_Matiere, Valeur_Temps + element.getDuree_en_minutes());
        }

        JpaUtil.fermerContextePersistance();

        return resultat;
    }
}
