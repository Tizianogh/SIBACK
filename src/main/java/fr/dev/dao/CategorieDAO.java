package fr.dev.dao;

import fr.dev.ConnectionManager;
import fr.dev.model.Categorie;
import fr.dev.model.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategorieDAO {
    private final Connection con = ConnectionManager.getConnection();
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    public Categorie createCategorie(Categorie categorie) throws SQLException {
        List<Categorie> data = this.getCategorieByName(categorie.getLibelle());

        if (!data.isEmpty()){
            return null;
        }

        String rq = "INSERT INTO Categorie (libelle) VALUES (?)";

        try {
            pstmt = con.prepareStatement(rq);
            pstmt.setString(1, categorie.getLibelle());

            pstmt.execute();
            System.out.println("Catégorie créée");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erreur, catégorie non créée");
        }

        return categorie;
    }

    public List<Categorie> getCategories() throws SQLException {
        List<Categorie> data = new ArrayList<>();

        String rq = "SELECT * FROM Categorie";
        pstmt = con.prepareStatement(rq);

        rs = pstmt.executeQuery();

        while (rs.next()) {
            Categorie categorie = new Categorie();

            categorie.setUuidCategorie(rs.getString("uuid_categorie"));
            categorie.setLibelle(rs.getString("libelle"));

            data.add(categorie);
        }

        if (data.isEmpty()) {
            System.out.println("Aucune categorie en base de données");
        }

        return data;
    }

    public List<Categorie> getCategorieByName(String libelle) throws SQLException {
        List<Categorie> data = new ArrayList<>();

        String rq = "SELECT * FROM Categorie WHERE libelle=?";
        pstmt = con.prepareStatement(rq);

        pstmt.setString(1, libelle);
        rs = pstmt.executeQuery();

        while (rs.next()) {
            Categorie categorie = new Categorie();

            categorie.setUuidCategorie(rs.getString("uuid_categorie"));
            categorie.setLibelle(rs.getString("libelle"));

            data.add(categorie);
        }

        if (data.isEmpty()) {
            System.out.println("Une catégorié avec ce libelle existe déjà.");
        }

        return data;
    }
}
