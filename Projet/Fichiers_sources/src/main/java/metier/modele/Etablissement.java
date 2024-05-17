package metier.modele;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Etablissement {

    @Id // clé primaire
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String uai;
    private String nom;

    private String secteur;
    private String codeCommune;
    private String nomCommune;
    private String codeDepartement;
    private String nomDepartement;
    private String academie;
    private String ips;
    private Double latitude;
    private Double longitude;
    private int nbEnfantsInscrits;

    public Etablissement(String uai, String nom) {
        this.uai = uai;
        this.nom = nom;
        this.nbEnfantsInscrits = 0;
    }

    public Etablissement() { // pour chaque classe entité, JPA demande un constructeur vide
    }

    public String getUai() {
        return uai;
    }

    public String getNom() {
        return nom;
    }

    public Long getId() {
        return id;
    }

    public String getSecteur() {
        return secteur;
    }

    public String getCodeCommune() {
        return codeCommune;
    }

    public String getNomCommune() {
        return nomCommune;
    }

    public String getCodeDepartement() {
        return codeDepartement;
    }

    public String getAcademie() {
        return academie;
    }

    public String getIps() {
        return ips;
    }

    public String getNomDepartement() {
        return nomDepartement;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public int getNbEnfantsInscrits() {
        return nbEnfantsInscrits;
    }

    public void setUai(String uai) {
        this.uai = uai;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    public void setCodeCommune(String codeCommune) {
        this.codeCommune = codeCommune;
    }

    public void setNomCommune(String nomCommune) {
        this.nomCommune = nomCommune;
    }

    public void setCodeDepartement(String codeDepartement) {
        this.codeDepartement = codeDepartement;
    }

    public void setAcademie(String academie) {
        this.academie = academie;
    }

    public void setIps(String ips) {
        this.ips = ips;
    }

    public void setNomDepartement(String nomDepartement) {
        this.nomDepartement = nomDepartement;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setNbEnfantsInscrits(int nbEnfantsInscrits) {
        this.nbEnfantsInscrits = nbEnfantsInscrits;
    }

}
