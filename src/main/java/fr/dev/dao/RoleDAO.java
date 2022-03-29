package fr.dev.dao;

import fr.dev.ConnectionManager;
import fr.dev.model.Marque;
import fr.dev.model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDAO {
    private final Connection con = ConnectionManager.getConnection();
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    public Role createRole(Role role) {
        String rq = "INSERT INTO Role(libelle) VALUES (?)";

        try {
            pstmt = con.prepareStatement(rq);

            pstmt.setString(1, role.getLibelle());

            pstmt.execute();
            System.out.println("Role créé");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erreur, role non créé");
        }

        return role;
    }
}
