package metier.modele;

import javax.persistence.Entity;

@Entity
public class Etudiant extends Intervenant {
    
    private String universite;
    private String specialite;

    public Etudiant(String nom, String prenom, int niveau_min, int niveau_max, String mail, String motDePasse) {
        super(nom, prenom, niveau_min, niveau_max, mail, motDePasse);
    }

    public Etudiant() {
    }

    public String getUniversite() {
        return universite;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setUniversite(String universite) {
        this.universite = universite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }  
}
