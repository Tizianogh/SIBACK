package fr.dev.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement public class Article {
    private String libelle;
    private float prix;
    private String uuidArticle;

    public Article() {

    }

    public Article(String uuidArticle, String libelle, float prix) {
        this.libelle = libelle;
        this.prix = prix;
        this.uuidArticle = uuidArticle;
    }

    public String getIDArticle() {
        return uuidArticle;
    }

    public String getLibelle() {
        return libelle;
    }

    public float getPrix() {
        return prix;
    }

    public void setUUIDArticle(String uuid_article) {
        this.uuidArticle = uuid_article;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
}
