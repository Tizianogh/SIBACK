package fr.dev.resources;

import fr.dev.dao.ArticleDao;
import fr.dev.model.Article;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.*;
import java.util.List;

@Path("articles")
public class ArticleResource {
    private final ArticleDao articleDao = new ArticleDao();

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
    public String findArticleAndDeleteByID(@PathParam("id") String id) throws SQLException {
        return articleDao.findArticleAndDeleteByID(id);
    }

}
