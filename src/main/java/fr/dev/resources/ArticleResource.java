package fr.dev.resources;

import fr.dev.dao.ArticleDAO;
import fr.dev.model.Article;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.*;
import java.util.List;

@Path("articles")
public class ArticleResource {
    private final ArticleDAO articleDao = new ArticleDAO();

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Article createArticle(Article article) {
        return articleDao.createArticle(article);
    }

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Article> getArticles() throws SQLException {
        return articleDao.getArticles();
    }

    @Path("/list/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Article> getArticleByID(@PathParam("id") String id)
            throws SQLException {
        return articleDao.getArticleByID(id);
    }

    @Path("/{category}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Article> getArticlesByCategory(@PathParam("category") String category)
            throws SQLException {
        return articleDao.getArticlesByCategory(category);
    }

    @Path("/update/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Article findAndUpdateArticle(
            @PathParam("id") String id, Article article) throws SQLException {
        return articleDao.findAndUpdateArticle(article, id);
    }

    @Path("/delete/{id}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Article> findArticleAndDeleteByID(@PathParam("id") String id) throws SQLException {
        return articleDao.findArticleAndDeleteByID(id);
    }

    @OPTIONS
    public Response getOptions() {
        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
    }
}
