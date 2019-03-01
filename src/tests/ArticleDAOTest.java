
import com.anvasy.dao.ArticleDAO;
import com.anvasy.database.DataBase;
import com.anvasy.model.Article;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.*;

public class ArticleDAOTest {

    /*private DataBase dataBase;
    private ArticleDAO articleDAO;

    @Before
    public void init() {
        try {
            dataBase = new DataBase();
            articleDAO = new ArticleDAO(dataBase);
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testgetAllandOne() throws SQLException{
        List<Article> articles = articleDAO.getAll();
        assertNotNull(articles);
        assertNotNull(articles.get(0));
        Article article = articleDAO.getArticle(1);
        assertNotNull(article);
        article = articleDAO.getArticle(3);
        assertNull(article.getContent());
    }

    @Test
    public void testInsertUpdate() throws SQLException{
        int len = articleDAO.getAll().size();
        articleDAO.insert(prepareArticle(0));
        assertEquals(len, articleDAO.getAll().size() - 1);
        articleDAO.update(prepareArticle(2));
        assertEquals("TEST", articleDAO.getArticle(2).getTopic());

        PreparedStatement ps = dataBase.getCn().prepareStatement(
                "insert into articles (topic,summary,content) values('T','TT','TTT')", Statement.RETURN_GENERATED_KEYS);
        ps.execute();

        ResultSet rs = ps.getGeneratedKeys();
        int generatedKey = 0;
        if (rs.next())
            generatedKey = rs.getInt(1);
        len = articleDAO.getAll().size();

        articleDAO.delete(generatedKey);

        assertEquals(len, articleDAO.getAll().size() + 1);
    }

    @After
    public void onEnd() throws SQLException{
        dataBase.close();
        dataBase = null;
        articleDAO = null;
    }

    private Article prepareArticle(int id) {
        Article article = new Article("TEST", "TEST CONTENT", "TEST SUMMARY");
        article.setId(id);

        return article;
    }*/

}



