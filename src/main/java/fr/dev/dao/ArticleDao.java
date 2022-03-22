package fr.dev.dao;

import fr.dev.ConnectionManager;
import fr.dev.model.Article;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleDao {
    private final Connection con = ConnectionManager.getConnection();
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    public Article createArticle(Article article) {
        String rq = "INSERT INTO ARTICLE(libelle, prix) VALUES (?,?)";

        try {
            this.pstmt = con.prepareStatement(rq);
            pstmt.setString(1, article.getLibelle());
            pstmt.setFloat(2, article.getPrix());

            pstmt.execute();
            System.out.println("Article créé");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erreur, article non créé");
        }

        return article;
    }

    public List<Article> getArticles() throws SQLException {
        List<Article> data = new ArrayList<>();

        String rq = "SELECT * FROM ARTICLE";
        pstmt = con.prepareStatement(rq);

        rs = pstmt.executeQuery();

        while (rs.next()) {
            Article article = new Article();

            article.setUUIDArticle(rs.getString("uuid_article"));
            article.setLibelle(rs.getString("libelle"));
            article.setPrix(rs.getFloat("prix"));

            data.add(article);
        }

        if (data.isEmpty()) {
            System.out.println("Aucun article en base de données");
        }

        return data;
    }

    public List<Article> getArticleByID(String uuidArticle) throws SQLException {
        List<Article> data = new ArrayList<>();

        String rq = "SELECT * FROM ARTICLE WHERE UUID_ARTICLE=?";
        pstmt = con.prepareStatement(rq);
        pstmt.setString(1, uuidArticle);

        rs = pstmt.executeQuery();

        while (rs.next()) {
            Article article = new Article();

            article.setUUIDArticle(rs.getString("uuid_article"));
            article.setLibelle(rs.getString("libelle"));
            article.setPrix(rs.getFloat("prix"));

            data.add(article);
        }

        if (data.isEmpty()) {
            String.format("Aucun article trouvé pour l'id %s", uuidArticle);
            return null;
        }

        return data;
    }

    public Article findAndUpdateArticle(Article article, String uuid) throws SQLException {
        List<Article> foundArticle = this.getArticleByID(uuid);
        if (foundArticle.isEmpty()) {
            String.format("Aucun article trouvé pour l'id %s", uuid);
            return null;
        }

        String rq = "UPDATE ARTICLE SET LIBELLE=?, PRIX=? WHERE UUID_ARTICLE=?";

        try {
            pstmt = con.prepareStatement(rq);
            pstmt.setString(1, article.getLibelle());
            pstmt.setFloat(2, article.getPrix());
            pstmt.setString(3, foundArticle.get(0).getIDArticle());

            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e + "Erreur, modification échouée.");
        }

        return article;
    }

    public String findArticleAndDeleteByID(String uuid) throws SQLException {
        List<Article> foundArticle = this.getArticleByID(uuid);
        if (foundArticle.isEmpty()) {
            return "Article non existant";
        }

        String rq = "DELETE FROM ARTICLE WHERE UUID_ARTICLE=?";

        try {
            pstmt = con.prepareStatement(rq);
            pstmt.setString(1, uuid);

            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e + "data insert unsuccess.");
        }

        return String.format("Article %s supprimé", foundArticle.get(0).getIDArticle());
    }
}
