package fr.dev.resources;

import fr.dev.dao.CategorieDAO;
import fr.dev.model.Article;
import fr.dev.model.Categorie;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("categories")
public class CategorieResource {
    private final CategorieDAO categorieDAO = new CategorieDAO();

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Categorie createCategorie(Categorie categorie) {
        return categorieDAO.createCategorie(categorie);
    }

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Categorie> getCategories() throws SQLException {
        return categorieDAO.getCategories();
    }
}
