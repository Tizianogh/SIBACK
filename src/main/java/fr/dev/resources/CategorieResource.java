package fr.dev.resources;

import fr.dev.dao.CategorieDAO;
import fr.dev.model.Article;
import fr.dev.model.Categorie;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("categories")
public class CategorieResource {
    private final CategorieDAO categorieDAO = new CategorieDAO();

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCategorie(Categorie categorie) throws SQLException {
        Categorie cat = this.categorieDAO.createCategorie(categorie);
        if(cat == null){
            return Response.status(Response.Status.BAD_REQUEST).entity("Un catégorie avec ce nom existe déjà").build();
        }

        return Response.ok(cat, MediaType.APPLICATION_JSON).build();
    }

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Categorie> getCategories() throws SQLException {
        return categorieDAO.getCategories();
    }
}
