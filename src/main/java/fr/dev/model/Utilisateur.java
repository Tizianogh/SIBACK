package fr.dev.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Utilisateur {
    private String uuidUtilisateur;
    private String mail;
    private String mdp;
    private String roleLibelle;

    public Utilisateur() {
    }

    public Utilisateur(String uuidUtilisateur, String mail, String mdp, String roleLibelle) {
        this.uuidUtilisateur = uuidUtilisateur;
        this.mail = mail;
        this.mdp = mdp;
        this.roleLibelle = roleLibelle;
    }

    public String getUuidUtilisateur() {
        return uuidUtilisateur;
    }

    public String getMail() {
        return mail;
    }

    public String getMdp() {
        return mdp;
    }

    public String getRoleLibelle() {
        return roleLibelle;
    }

    public void setUuidUtilisateur(String uuidUtilisateur) {
        this.uuidUtilisateur = uuidUtilisateur;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setRoleLibelle(String roleLibelle) {
        this.roleLibelle = roleLibelle;
    }
}
