package fr.dev.resources;

import fr.dev.dao.UtilisateurDAO;
import fr.dev.model.Marque;
import fr.dev.model.Utilisateur;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("users")
public class UtilisateurResource {
    private static UtilisateurDAO utilisateurDAO = new UtilisateurDAO();

    @Path("/login")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Utilisateur> login(Utilisateur utilisateur) throws SQLException {
        return utilisateurDAO.login(utilisateur);
    }
}
