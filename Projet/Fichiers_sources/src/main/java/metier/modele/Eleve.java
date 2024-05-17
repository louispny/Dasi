package metier.modele;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;

@Entity
public class Eleve {

    @Id // clé primaire
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nom;
    private String prenom;
    
    private int niveau; // de 6 à 0
    
    @ManyToOne // 1 eleve est associé à 1 etab, mais 1 etab peut être associée à plusieurs eleves
    private Etablissement etablissement;
    
    @Column(unique = true) // Contrainte d'unicité sur le mail
    private String mail;
    
    private String motDePasse;

    public Eleve(String nom, String prenom, int niveau, String mail, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.niveau = niveau;
        this.mail = mail;
        this.motDePasse = motDePasse;
    }
    
    public Eleve() {
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

    public int getNiveau() {
        return niveau;
    }

    public String getMail() {
        return mail;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public Etablissement getEtablissement() {
        return etablissement;
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

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }
}