package fr.dev.resources;

import fr.dev.dao.UtilisateurDAO;
import fr.dev.model.Utilisateur;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Path("users")
public class UtilisateurResource {
    private static UtilisateurDAO utilisateurDAO = new UtilisateurDAO();

    @Path("/login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Utilisateur utilisateur) throws SQLException {
        List<Utilisateur> users = utilisateurDAO.login(utilisateur);
        if (users.isEmpty()) {
            return Response.status(NOT_FOUND).entity("L'email ou le mot de passe est incorrect").build();
        }
        return Response.ok(users, MediaType.APPLICATION_JSON).build();
    }

    @Path("/register")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(Utilisateur utilisateur) throws SQLException {
        Utilisateur user = utilisateurDAO.createUser(utilisateur);
        if(user == null){
            return Response.status(BAD_REQUEST).entity("Un compte avec cet email existe déjà").build();
        }

        return Response.ok(user, MediaType.APPLICATION_JSON).build();
    }
}
