package fr.dev.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.UUID;

@XmlRootElement
public class Article {
    private String libelle;
    private float prix;
    private UUID uuidArticle;

    public Article() {

    }

    public Article(String libelle, float prix) {
        this.libelle = libelle;
        this.prix = prix;
    }

    public UUID getIDArticle() {
        return uuidArticle;
    }

    public String getLibelle() {
        return libelle;
    }

    public float getPrix() {
        return prix;
    }
}
