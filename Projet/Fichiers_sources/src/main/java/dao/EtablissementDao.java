package dao;

import java.util.List;
import javax.persistence.TypedQuery;
import metier.modele.Etablissement;

public class EtablissementDao {

    public void creerEtablissement(Etablissement etablissement) {
        JpaUtil.obtenirContextePersistance().persist(etablissement); // sauvegarde dans la BD
    }

    public Etablissement findByUai(String para_uai) {
        TypedQuery<Etablissement> query = JpaUtil.obtenirContextePersistance().createQuery("SELECT e FROM Etablissement e WHERE e.uai = :uai", Etablissement.class);
        query.setParameter("uai", para_uai);
        List<Etablissement> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

}
