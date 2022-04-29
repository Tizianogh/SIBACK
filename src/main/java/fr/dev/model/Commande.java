package fr.dev.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;

@XmlRootElement public class Commande {
    private String uuidCommande;
    private Date dateCommande;
    private String uuidUtilisateur;

    private float prix;

    private Commande() {

    }

    public Commande(String uuidCommande, Date dateCommande, String uuidUtilisateur, float prix) {
        this.uuidCommande = uuidCommande;
        this.dateCommande = dateCommande;
        this.uuidUtilisateur = uuidUtilisateur;
        this.prix = prix;
    }

    public String getUuidCommande() {
        return uuidCommande;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public String getUuidUtilisateur() {
        return uuidUtilisateur;
    }

    public void setUuidCommande(String uuidCommande) {
        this.uuidCommande = uuidCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public void setUuidUtilisateur(String uuidUtilisateur) {
        this.uuidUtilisateur = uuidUtilisateur;
    }

    public float getPrix() {
        return this.prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
}
