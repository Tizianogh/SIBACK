package fr.dev.resources;

import fr.dev.ConnectionManager;
import fr.dev.model.Article;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.sql.*;

@Path("articles") public class ArticleResource {
    private Connection con = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    @Path("/add") @POST @Consumes(MediaType.APPLICATION_JSON) public void createArticle(Article article) {
        String rq = "INSERT INTO ARTICLE(libelle, prix) VALUES (?,?)";

        try {
            System.out.println(article.getIDArticle());
            con = ConnectionManager.getConnection();
            this.pstmt = con.prepareStatement(rq);
            pstmt.setString(1, article.getLibelle());
            pstmt.setFloat(2, article.getPrix());

            pstmt.execute();
            System.out.println("Article créé");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erreur, article non créé");
        }
    }
}
