package metier.modele;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Matiere {
    
    @Id
    private String nom;

    public Matiere(String nom) {
        this.nom = nom;
    }

    public Matiere() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
