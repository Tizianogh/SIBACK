package fr.dev.dao;

import fr.dev.ConnectionManager;
import fr.dev.model.Article;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArticleDAO {
    private final Connection con = ConnectionManager.getConnection();
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    public Article createArticle(Article article) {
        String rq = "INSERT INTO Article(titre, libelle, prix, uuid_categorie, uuid_marque, url_image) VALUES (?,?,?,?,?,?)";

        try {
            pstmt = con.prepareStatement(rq);

            pstmt.setString(1, article.getTitre());
            pstmt.setString(2, article.getLibelle());
            pstmt.setFloat(3, article.getPrix());
            pstmt.setString(4, article.getUuidCategorie());
            pstmt.setString(5, article.getUuidMarque());
            pstmt.setString(6, article.getUrlImage());

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

        String rq = "SELECT * FROM Article";
        pstmt = con.prepareStatement(rq);

        rs = pstmt.executeQuery();

        while (rs.next()) {
            Article article = new Article();

            article.setUUIDArticle(rs.getString("uuid_article"));
            article.setTitre(rs.getString("titre"));
            article.setLibelle(rs.getString("libelle"));
            article.setPrix(rs.getFloat("prix"));
            article.setUuidCategorie(rs.getString("uuid_categorie"));
            article.setUuidMarque(rs.getString("uuid_marque"));
            article.setUrlImage(rs.getString("url_image"));

            data.add(article);
        }

        if (data.isEmpty()) {
            System.out.println("Aucun article en base de données");
        }

        return data;
    }

    public List<Article> getArticleByID(String uuidArticle) throws SQLException {
        List<Article> data = new ArrayList<>();

        String rq = "SELECT * FROM Article WHERE uuid_article=?";
        pstmt = con.prepareStatement(rq);
        pstmt.setString(1, uuidArticle);

        rs = pstmt.executeQuery();

        while (rs.next()) {
            Article article = new Article();

            article.setUUIDArticle(rs.getString("uuid_article"));
            article.setTitre(rs.getString("titre"));
            article.setLibelle(rs.getString("libelle"));
            article.setPrix(rs.getFloat("prix"));
            article.setUuidCategorie(rs.getString("uuid_categorie"));
            article.setUuidMarque(rs.getString("uuid_marque"));
            article.setUrlImage(rs.getString("url_image"));

            data.add(article);
        }

        if (data.isEmpty()) {
            String.format("Aucun article trouvé pour l'id %s", uuidArticle);
            return null;
        }

        return data;
    }

    public List<Article> getArticlesByCategory(String category) throws SQLException {
        List<Article> data = new ArrayList<>();

        String rq = "SELECT *\n"
                + "FROM Article, Categorie, Marque\n"
                + "WHERE Article.uuid_categorie=Categorie.uuid_categorie\n"
                + "AND Article.uuid_marque=Marque.uuid_marque\n"
                + "AND Categorie.libelle=?";

        pstmt = con.prepareStatement(rq);
        pstmt.setString(1, category);

        rs = pstmt.executeQuery();

        while (rs.next()) {
            Article article = new Article();

            article.setUUIDArticle(rs.getString("uuid_article"));
            article.setTitre(rs.getString("titre"));
            article.setLibelle(rs.getString("libelle"));
            article.setPrix(rs.getFloat("prix"));
            article.setUuidCategorie(rs.getString("uuid_categorie"));
            article.setUuidMarque(rs.getString("uuid_marque"));
            article.setUrlImage(rs.getString("url_image"));

            data.add(article);
        }

        if (data.isEmpty()) {
            String.format("Aucun article trouvé pour la catégorie %s", category);
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

        String rq = "UPDATE Article SET titre=?, libelle=?, prix=?, uuid_categorie=?, uuid_marque=?, url_image=? WHERE uuid_article=?";

        try {
            pstmt = con.prepareStatement(rq);
            pstmt.setString(1, article.getTitre());
            pstmt.setString(2, article.getLibelle());
            pstmt.setFloat(3, article.getPrix());
            pstmt.setString(4, article.getUuidCategorie());
            pstmt.setString(5, article.getUuidMarque());
            pstmt.setString(6, article.getUrlImage());
            pstmt.setString(7, foundArticle.get(0).getUuidArticle());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            System.out.println(e + "Erreur, modification échouée.");
        }

        return article;
    }

    public List<Article> findArticleAndDeleteByID(String uuid) throws SQLException {
        List<Article> foundArticle = this.getArticleByID(uuid);
        if (foundArticle.isEmpty()) {
            return Collections.emptyList();
        }

        String rq = "DELETE FROM Article WHERE uuid_article=?";

        try {
            pstmt = con.prepareStatement(rq);
            pstmt.setString(1, uuid);

            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e + "data insert unsuccess.");
        }

        List<Article> data = this.getArticles();

        return data;
    }
}
