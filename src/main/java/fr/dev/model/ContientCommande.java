package fr.dev.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement public class ContientCommande<T> {
    private String uuidContientCommande;
    private String uuidCommande;
    private List<T> contenue;

    public ContientCommande() {
    }

    public ContientCommande(String uuidContientCommande, String uuidCommande, List<T> contenue) {
        this.uuidContientCommande = uuidContientCommande;
        this.uuidCommande = uuidCommande;
        this.contenue = contenue;
    }

    public String getUuidContientCommande() {
        return uuidContientCommande;
    }

    public String getUuidCommande() {
        return uuidCommande;
    }

    public List<T> getContenue() {
        return contenue;
    }

    public void setUuidContientCommande(String uuidContientCommande) {
        this.uuidContientCommande = uuidContientCommande;
    }

    public void setUuidCommande(String uuidCommande) {
        this.uuidCommande = uuidCommande;
    }

    public void setContenue(List<T> contenue) {
        this.contenue = contenue;
    }
}
