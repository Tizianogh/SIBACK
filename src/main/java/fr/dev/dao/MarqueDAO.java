package fr.dev.dao;

import fr.dev.ConnectionManager;
import fr.dev.model.Article;
import fr.dev.model.Categorie;
import fr.dev.model.Marque;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MarqueDAO {
    private final Connection con = ConnectionManager.getConnection();
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    public Marque createMarque(Marque marque) {
        String rq = "INSERT INTO MARQUE(libelle) VALUES (?)";

        try {
            pstmt = con.prepareStatement(rq);
            pstmt.setString(1, marque.getLibelle());

            pstmt.execute();
            System.out.println("Marque créée");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erreur, marque non créée");
        }

        return marque;
    }

    public List<Marque> getMarques() throws SQLException {
        List<Marque> data = new ArrayList<>();

        String rq = "SELECT * FROM MARQUE";
        pstmt = con.prepareStatement(rq);

        rs = pstmt.executeQuery();

        while (rs.next()) {
            Marque marque = new Marque();

            marque.setUuidMarque(rs.getString("uuid_marque"));
            marque.setLibelle(rs.getString("libelle"));

            data.add(marque);
        }

        if (data.isEmpty()) {
            System.out.println("Aucune marque en base de données");
        }

        return data;
    }
}
