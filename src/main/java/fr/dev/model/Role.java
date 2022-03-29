package fr.dev.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Role {
    private String uuidRole;
    private String libelle;

    public Role() {
    }

    public Role(String uuidRole, String libelle) {
        this.uuidRole = uuidRole;
        this.libelle = libelle;
    }

    public String getUuidRole() {
        return uuidRole;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setUuidRole(String uuidRole) {
        this.uuidRole = uuidRole;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
