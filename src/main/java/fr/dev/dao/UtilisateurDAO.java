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

public class UtilisateurDAO {
    private final Connection con = ConnectionManager.getConnection();
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    public List<Utilisateur> login(Utilisateur utilisateur) throws SQLException {
        List<Utilisateur> data = new ArrayList<>();

        String rq = "SELECT * FROM Utilisateur WHERE mail=? AND mdp=?";
        pstmt = con.prepareStatement(rq);
        pstmt.setString(1, utilisateur.getMail());
        pstmt.setString(2, utilisateur.getMdp());

        rs = pstmt.executeQuery();

        while (rs.next()) {
            Utilisateur user = new Utilisateur();

            user.setUuidUtilisateur(rs.getString("uui_utilisateur"));
            user.setMail(rs.getString("mail"));
            user.setMdp(rs.getString("mdp"));
            user.setUuidRole(rs.getString("uuid_role"));

            data.add(user);
        }

        if (data.isEmpty()) {
            System.out.println("Aucun compte trouvé avec cet email et ce mdp");
        }

        return data;
    }
}
