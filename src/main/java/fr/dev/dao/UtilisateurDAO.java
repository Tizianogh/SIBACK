package fr.dev.dao;

import fr.dev.ConnectionManager;
import fr.dev.model.Utilisateur;

import javax.rmi.CORBA.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class UtilisateurDAO {
    private final Connection con = ConnectionManager.getConnection();
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    public Utilisateur createUser(Utilisateur utilisateur) throws SQLException {

        List<Utilisateur> data = this.getUtilisateurByMail(utilisateur.getMail());

        if(!data.isEmpty()){
            return null;
        }

        String rq = "INSERT INTO Utilisateur(mail, mdp, role_libelle) VALUES (?,?,?)";
        try {
            pstmt = con.prepareStatement(rq);

            pstmt.setString(1, utilisateur.getMail());
            pstmt.setString(2, utilisateur.getMdp());
            pstmt.setString(3, utilisateur.getRoleLibelle());

            pstmt.execute();
            System.out.println("Utilisateur créé");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erreur, utilisateur non créé");
        }

        if (utilisateur == null) {
            System.err.println("La création de l'utilisateur n'a pas réussi");
            return null;
        }
        return utilisateur;
    }

    public List<Utilisateur> login(Utilisateur utilisateur) throws SQLException {
        List<Utilisateur> data = new ArrayList<>();

        String rq = "SELECT * FROM Utilisateur WHERE mail=? AND mdp=?";
        pstmt = con.prepareStatement(rq);
        pstmt.setString(1, utilisateur.getMail());
        pstmt.setString(2, utilisateur.getMdp());

        rs = pstmt.executeQuery();

        while (rs.next()) {
            Utilisateur user = new Utilisateur();

            user.setUuidUtilisateur(rs.getString("uuid_utilisateur"));
            user.setMail(rs.getString("mail"));
            user.setMdp(rs.getString("mdp"));
            user.setRoleLibelle(rs.getString("role_libelle"));

            data.add(user);
        }

        if (data.isEmpty()) {
            System.out.println("Aucun compte trouvé avec cet email et ce mdp");
        }

        return data;
    }

    public List<Utilisateur> getUtilisateurByUUID(String uuid) throws SQLException {
        List<Utilisateur> data = new ArrayList<>();
        String rq = "SELECT * FROM Utilisateur WHERE uuid_utilisateur=?";
        pstmt = con.prepareStatement(rq);

        pstmt.setString(1, uuid);
        rs = pstmt.executeQuery();

        while (rs.next()) {
            Utilisateur user = new Utilisateur();

            user.setUuidUtilisateur(rs.getString("uuid_utilisateur"));
            user.setMail(rs.getString("mail"));
            user.setMdp(rs.getString("mdp"));
            user.setRoleLibelle(rs.getString("role_libelle"));

            data.add(user);
        }

        if (data.isEmpty()) {
            System.out.println("Aucun compte trouvé pour cet uuid");
        }
        return data;
    }

    public List<Utilisateur> getUtilisateurByMail(String mail) throws SQLException {
        List<Utilisateur> data = new ArrayList<>();
        String rq = "SELECT * FROM Utilisateur WHERE mail=?";
        pstmt = con.prepareStatement(rq);

        pstmt.setString(1, mail);
        rs = pstmt.executeQuery();

        while (rs.next()) {
            Utilisateur user = new Utilisateur();

            user.setUuidUtilisateur(rs.getString("uuid_utilisateur"));
            user.setMail(rs.getString("mail"));
            user.setMdp(rs.getString("mdp"));
            user.setRoleLibelle(rs.getString("role_libelle"));

            data.add(user);
        }

        if (data.isEmpty()) {
            System.out.println("Aucun compte trouvé pour ce mail");
        }
        return data;
    }
}
