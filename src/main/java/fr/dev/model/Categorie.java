package fr.dev.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Categorie {
    private String uuidCategorie;
    private String libelle;

    public Categorie() {

    }

    public Categorie(String uuidCategorie, String libelle) {
        this.uuidCategorie = uuidCategorie;
        this.libelle = libelle;
    }

    public void setUuidCategorie(String uuidCategorie) {
        this.uuidCategorie = uuidCategorie;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getUuidCategorie() {
        return uuidCategorie;
    }

    public String getLibelle() {
        return libelle;
    }
}
