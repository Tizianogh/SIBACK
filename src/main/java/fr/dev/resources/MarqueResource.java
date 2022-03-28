package fr.dev.resources;

import fr.dev.dao.CategorieDAO;
import fr.dev.dao.MarqueDAO;
import fr.dev.model.Categorie;
import fr.dev.model.Marque;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("brands")
public class MarqueResource {
    private final MarqueDAO marqueDAO = new MarqueDAO();

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Marque createMarque(Marque marque) {
        return marqueDAO.createMarque(marque);
    }

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Marque> getCategories() throws SQLException {
        return marqueDAO.getMarques();
    }
}
