package metier.modele;

import javax.persistence.Entity;

@Entity
public class Enseignant extends Intervenant {
    
    private String type_etablissement;

    public Enseignant(String nom, String prenom, int niveau_min, int niveau_max, String mail, String motDePasse) {
        super(nom, prenom, niveau_min, niveau_max, mail, motDePasse);
    }

    public Enseignant() {
    }

    public String getType_etablissement() {
        return type_etablissement;
    }

    public void setType_etablissement(String type_etablissement) {
        this.type_etablissement = type_etablissement;
    }
   
}