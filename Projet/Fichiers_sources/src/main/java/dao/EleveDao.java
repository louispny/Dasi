package dao;

import java.util.List;
import javax.persistence.TypedQuery;
import metier.modele.Eleve;

public class EleveDao {

    public void creer(Eleve eleve) {
        JpaUtil.obtenirContextePersistance().persist(eleve); // sauvegarde dans la BD
    }

    public Eleve findByMail(String para_mail) {
        TypedQuery<Eleve> query = JpaUtil.obtenirContextePersistance().createQuery("SELECT e FROM Eleve e WHERE e.mail = :mail", Eleve.class);
        query.setParameter("mail", para_mail);
        List<Eleve> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }
}
