package metier.modele;

import javax.persistence.Entity;

@Entity
public class Autre extends Intervenant {
    
    private String activite;

    public Autre(String nom, String prenom, int niveau_min, int niveau_max, String mail, String motDePasse) {
        super(nom, prenom, niveau_min, niveau_max, mail, motDePasse);
    }

    public Autre() {
    }

    public String getActivite() {
        return activite;
    }

    public void setActivite(String activite) {
        this.activite = activite;
    }

}