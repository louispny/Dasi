package metier.modele;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

@Entity
public class Intervenant {
       
    @Id // clé primaire
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nom;
    private String prenom;
    
    private int niveau_min;;
    private int niveau_max;
    
    @Column(unique = true) // Contrainte d'unicité sur le mail
    private String mail;
    
    private String motDePasse;
    private boolean dispo; // 1 oui 0 non
    private int nbInterventionFaite;

    public Intervenant(String nom, String prenom, int niveau_min, int niveau_max, String mail, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.niveau_min = niveau_min;
        this.niveau_max = niveau_max;
        this.mail = mail;
        this.motDePasse = motDePasse;
        this.nbInterventionFaite = 0;
        this.dispo = true;
    }

    public Intervenant() {
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getNiveau_min() {
        return niveau_min;
    }

    public int getNiveau_max() {
        return niveau_max;
    }

    public String getMail() {
        return mail;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public int getNbInterventionFaite() {
        return nbInterventionFaite;
    }

    public boolean getDispo() {
        return dispo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNiveau_min(int niveau_min) {
        this.niveau_min = niveau_min;
    }

    public void setNiveau_max(int niveau_max) {
        this.niveau_max = niveau_max;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    } 

    public void setNbInterventionFaite(int nbInterventionFaite) {
        this.nbInterventionFaite = nbInterventionFaite;
    }
    
    public void setDispo(boolean dispo) {
        this.dispo = dispo;
    }

}
