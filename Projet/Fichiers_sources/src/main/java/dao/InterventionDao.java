package dao;

import java.util.Collections;
import java.util.List;
import javax.persistence.TypedQuery;
import metier.modele.Eleve;
import metier.modele.Intervenant;
import metier.modele.Intervention;

public class InterventionDao {

    public void creer(Intervention intervention) {
        JpaUtil.obtenirContextePersistance().persist(intervention); // sauvegarde dans la BD
    }

    public void maj(Intervention intervention) {
        JpaUtil.obtenirContextePersistance().merge(intervention); // maj de l'intervention dans la BD
    }

    public List<Intervention> HistoriqueEleve(Eleve eleve) {
        TypedQuery<Intervention> query = JpaUtil.obtenirContextePersistance().createQuery(
                "SELECT i FROM Intervention i WHERE i.eleve = :eleve ORDER BY i.date_debut DESC", Intervention.class); // requete JPQL et type attendu
        query.setParameter("eleve", eleve); // on donne une valeur à notre attribut de selection
        List<Intervention> resultList = query.getResultList();
        return resultList.isEmpty() ? Collections.emptyList() : resultList;
    }

    public List<Intervention> HistoriqueIntervenant(Intervenant intervenant) {
        TypedQuery<Intervention> query = JpaUtil.obtenirContextePersistance().createQuery(
                "SELECT i FROM Intervention i WHERE i.intervenant = :intervenant ORDER BY i.date_debut DESC", Intervention.class); // requete JPQL et type attendu
        query.setParameter("intervenant", intervenant); // on donne une valeur à notre attribut de selection
        List<Intervention> resultList = query.getResultList();
        return resultList.isEmpty() ? Collections.emptyList() : resultList;
    }
}
