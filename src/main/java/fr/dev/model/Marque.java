package fr.dev.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Marque {
    private String uuidMarque;
    private String libelle;

    public Marque() {

    }

    public Marque(String uuidMarque, String libelle) {
        this.uuidMarque = uuidMarque;
        this.libelle = libelle;
    }

    public String getUuidMarque() {
        return uuidMarque;
    }

    public void setUuidMarque(String uuidMarque) {
        this.uuidMarque = uuidMarque;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }
}
