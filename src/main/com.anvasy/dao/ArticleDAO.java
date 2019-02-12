package dao;

import database.DataBase;
import model.Article;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAO {

    private DataBase db;

    public ArticleDAO(DataBase db) {
        this.db = db;
    }

    public void insert(Article article) throws SQLException {
        PreparedStatement ps = db.getCn().prepareStatement(
                "insert into articles (topic,summary,content)"
                        + "values(?,?,?)");

        ps.setString(1, article.getTopic());
        ps.setString(2, article.getSummary());
        ps.setString(3, article.getContent());

        ps.execute();
    }

    public void delete(Article article) throws SQLException {
        PreparedStatement ps = db.getCn().prepareStatement(
                "delete from articles where id = ?");
        ps.setInt(1, article.getId());
        ps.execute();
    }

    public void update(Article article) throws SQLException {
        PreparedStatement ps = db.getCn().prepareStatement(
                "update articles set topic=?, summary=?, content=? where id = ?");
        ps.setString(1, article.getTopic());
        ps.setString(2, article.getSummary());
        ps.setString(3, article.getContent());
        ps.setInt(4, article.getId());

        ps.execute();
    }

    public List<Article> getAll() throws SQLException{
        List<Article> articles = new ArrayList<>();

        PreparedStatement preparedStatement = db.getCn().prepareStatement("select * from articles");
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            Article article = new Article();
            article.setId(rs.getInt("id"));
            article.setTopic(rs.getString("topic"));
            article.setSummary(rs.getString("summary"));
            article.setContent(rs.getString("content"));

            articles.add(article);
        }

        return articles;
    }

    public Article getArticle(int id) throws SQLException{
        Article article = new Article(id);

        PreparedStatement preparedStatement = db.getCn().prepareStatement("select * from articles where id = ?");
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            article.setTopic(rs.getString("topic"));
            article.setSummary(rs.getString("summary"));
            article.setContent(rs.getString("content"));
        }

        return article;
    }
}
