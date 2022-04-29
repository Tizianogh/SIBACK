package fr.dev.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.dev.ConnectionManager;
import fr.dev.model.Commande;
import fr.dev.model.ContientCommande;
import fr.dev.model.Utilisateur;

import java.sql.*;
import java.util.List;

public class CommandeDAO {
    private final UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
    private final Connection con = ConnectionManager.getConnection();
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    public Commande createOrder(Commande commande) throws SQLException {
        String rq = "INSERT INTO Commande(uuid_commande,date_commande, uuid_utilisateur, prix) VALUES (?,?,?,?)";

        List<Utilisateur> users = utilisateurDAO.getUtilisateurByUUID(commande.getUuidUtilisateur());

        if (users.isEmpty()) {
            return null;
        }

        try {
            pstmt = con.prepareStatement(rq);

            pstmt.setString(1, commande.getUuidCommande());
            pstmt.setDate(2, new Date(System.currentTimeMillis()));
            pstmt.setString(3, commande.getUuidUtilisateur());
            pstmt.setFloat(4, commande.getPrix());

            pstmt.execute();
            System.out.println("Commande créée");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erreur, commande non créée");
        }
        if (commande == null) {
            System.out.println("Erreur, commande non créée");
            return null;
        }

        return commande;
    }

    public ContientCommande alimentationOrder(ContientCommande commande) throws SQLException {
        String rq = "INSERT INTO Contient_commande (uuid_commande, contenue) VALUES (?,?)";
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            pstmt = con.prepareStatement(rq);

            pstmt.setString(1, commande.getUuidCommande());
            pstmt.setObject(2, objectMapper.writeValueAsString(commande.getContenue()));

            pstmt.execute();
            System.out.println("Commande créée");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erreur, commande non créée");
        } catch (JsonProcessingException e) {

        }

        if (commande == null) {
            return null;
        }

        return commande;
    }

}
