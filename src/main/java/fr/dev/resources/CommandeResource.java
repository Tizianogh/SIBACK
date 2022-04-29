package fr.dev.resources;

import fr.dev.dao.CommandeDAO;
import fr.dev.model.Commande;
import fr.dev.model.ContientCommande;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

import static javax.ws.rs.core.Response.Status.NO_CONTENT;

@Path("/commandes") public class CommandeResource {
    private final CommandeDAO commandeDAO = new CommandeDAO();

    @Path("/add")
    @POST @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOrder(
            Commande commande) throws SQLException {
        Commande order = commandeDAO.createOrder(commande);

        if (order == null) {
            return Response.status(NO_CONTENT).entity("La commande n'a pas été créée").build();
        }
        return Response.ok(order, MediaType.APPLICATION_JSON).build();
    }

    @Path("/contenue/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON) public Response alimenteOrder(
            ContientCommande contientCommande) throws SQLException {
        ContientCommande ligne = commandeDAO.alimentationOrder(contientCommande);

        if (ligne == null) {
            return Response.status(NO_CONTENT).entity("La commande n'a pas été créée").build();
        }
        return Response.ok(ligne, MediaType.APPLICATION_JSON).build();
    }
}
