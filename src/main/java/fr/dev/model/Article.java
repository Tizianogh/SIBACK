package fr.dev.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement public class Article {
    private String uuidArticle;
    private String titre;
    private String libelle;
    private float prix;
    private String uuidCategorie;
    private String uuidMarque;

    public Article() {
    }

    public Article(String uuidArticle, String titre, String libelle, float prix, String uuidCategorie, String uuidMarque) {
        this.uuidArticle = uuidArticle;
        this.titre = titre;
        this.libelle = libelle;
        this.prix = prix;
        this.uuidCategorie = uuidCategorie;
        this.uuidMarque = uuidMarque;
    }

    public String getUuidCategorie() {
        return uuidCategorie;
    }

    public String getUuidMarque() {
        return uuidMarque;
    }

    public String getLibelle() {
        return libelle;
    }

    public float getPrix() {
        return prix;
    }

    public String getUuidArticle() {
        return uuidArticle;
    }

    public String getTitre() {
        return titre;
    }

    public void setUUIDArticle(String uuid_article) {
        this.uuidArticle = uuid_article;
    }

    public void setUuidCategorie(String uuidCategorie) {
        this.uuidCategorie = uuidCategorie;
    }

    public void setUuidMarque(String uuidMarque) {
        this.uuidMarque = uuidMarque;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
}
