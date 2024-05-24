package metier.modele;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Intervention {
    
    @Id // clé primaire
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne // 1 intervention est associée à 1 matière, mais 1 matière peut être associée à plusieurs interventions
    private Matiere matiere;
    
    @ManyToOne // 1 intervention est associée à 1 intervenant, mais 1 intervenant peut être associée à plusieurs interventions
    private Intervenant intervenant;
    // if intervenant == null, l'intervention n'as pas eu de réponse
    
    @ManyToOne // 1 intervention est associée à 1 eleve, mais 1 eleve peut être associée à plusieurs interventions
    private Eleve eleve;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date date_debut;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date date_fin;
    
    private String detail;
    private long duree_en_minutes;
    private String bilan;
    private int note; // de 0 à 5
    

    public Intervention(Matiere matiere, Eleve eleve, String detail) {
        this.matiere = matiere;
        this.eleve = eleve;
        this.detail = detail;
    }

    public Intervention() {
    }

    public Long getId() {
        return id;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public Intervenant getIntervenant() {
        return intervenant;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public String getDetail() {
        return detail;
    }

    public String getBilan() {
        return bilan;
    }

    public long getDuree_en_minutes() {
        return duree_en_minutes;
    }

    public int getNote() {
        return note;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public void setIntervenant(Intervenant intervenant) {
        this.intervenant = intervenant;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }
    
    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setBilan(String bilan) {
        this.bilan = bilan;
    }

    public void setDuree_en_minutes(long duree_en_minutes) {
        this.duree_en_minutes = duree_en_minutes;
    }

    public void setNote(int note) {
        this.note = note;
    }
}
