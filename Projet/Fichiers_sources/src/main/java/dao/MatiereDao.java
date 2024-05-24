package dao;

import java.util.List;
import javax.persistence.Query;
import metier.modele.Matiere;

public class MatiereDao {

    public void creer(Matiere matiere) {
        JpaUtil.obtenirContextePersistance().persist(matiere); // sauvegarde dans la BD
    }

    public Matiere findById(String para_nom) {
        return JpaUtil.obtenirContextePersistance().find(Matiere.class, para_nom);
    }

    public List<Matiere> getAllMatieres() {
        Query query = JpaUtil.obtenirContextePersistance().createQuery("SELECT m FROM Matiere m ORDER BY m.nom ASC");
        return query.getResultList();
    }
}
