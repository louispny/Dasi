package dao;

import java.util.Collections;
import java.util.List;
import javax.persistence.TypedQuery;
import metier.modele.Intervenant;
import metier.modele.Intervention;

public class IntervenantDao {

    public void creer(Intervenant intervenant) {
        JpaUtil.obtenirContextePersistance().persist(intervenant); // sauvegarde dans la BD
    }

    public void maj(Intervenant intervenant) {
        JpaUtil.obtenirContextePersistance().merge(intervenant); // maj de l'intervenant dans la BD
    }

    public Intervenant findByID(Long para_id) {
        TypedQuery<Intervenant> query = JpaUtil.obtenirContextePersistance().createQuery("SELECT i FROM Intervenant i WHERE i.id = :id", Intervenant.class);
        query.setParameter("id", para_id);
        List<Intervenant> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }
    
    public Intervenant findByMail(String para_mail) {
        TypedQuery<Intervenant> query = JpaUtil.obtenirContextePersistance().createQuery("SELECT i FROM Intervenant i WHERE i.mail = :mail", Intervenant.class);
        query.setParameter("mail", para_mail);
        List<Intervenant> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    public Intervenant findByNiveauANDisFREE(int niveau_eleve) {
        String requeteJPQL = "SELECT i FROM Intervenant i WHERE i.niveau_min >= :niveau AND i.niveau_max <= :niveau AND i.dispo = 1 ORDER BY i.nbInterventionFaite ASC";
        TypedQuery<Intervenant> query = JpaUtil.obtenirContextePersistance().createQuery(requeteJPQL, Intervenant.class);
        query.setParameter("niveau", niveau_eleve);
        List<Intervenant> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    public void envoiMail(String mailExpediteur, String mailDestinataire, String objet, String corps) {
        Message.envoyerMail(mailExpediteur, mailDestinataire, objet, corps);
    }

    public Intervention DerniereIntervention(Intervenant intervenant) {
        TypedQuery<Intervention> query = JpaUtil.obtenirContextePersistance().createQuery(
                "SELECT i FROM Intervention i WHERE i.intervenant = :intervenant AND i.date_fin = NULL AND i.date_debut = (SELECT MAX(i2.date_debut) FROM Intervention i2 WHERE i2.intervenant = :intervenant)", Intervention.class); // requete JPQL et type attendu
        query.setParameter("intervenant", intervenant); // on donne une valeur à notre attribut de selection
        return query.getSingleResult();
    }
}
