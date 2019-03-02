
import com.anvasy.dao.ArticleDAO;
import com.anvasy.dao.RatesDAO;
import com.anvasy.dao.UsersDAO;
import com.anvasy.database.DataBase;
import com.anvasy.model.Article;
import com.anvasy.model.Rate;
import com.anvasy.model.User;
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

public class DAOTest {

    private DataBase dataBase;
    private ArticleDAO articleDAO;
    private UsersDAO usersDAO;
    private RatesDAO ratesDAO;

    private int uID;

    @Before
    public void init() {
        try {
            dataBase = new DataBase();
            articleDAO = new ArticleDAO(dataBase);
            usersDAO = new UsersDAO(dataBase);
            ratesDAO = new RatesDAO(dataBase);
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetOneAndInsert() throws SQLException {
        User user = new User();
        user.setName("TEST USER");
        user.setPassword("TEST PASSWORD");
        user.setId(0);
        assertEquals("TEST USER", user.getName());
        assertEquals("TEST PASSWORD", user.getPassword());
        uID = usersDAO.insert(user);
        assertNotEquals(uID, 0);
        assertEquals(usersDAO.getUser("TEST USER", "TEST PASSWORD"), uID);
        User testUser = new User("TEST", "TEST");
        testUser.getId();
    }

    @Test
    public void testGetAll() throws SQLException{
        List<Article> articles = articleDAO.getAll();
        assertNotNull(articles);
    }

    @Test
    public void testInsertUpdate() throws SQLException{
        int len = articleDAO.getAll().size();
        Article article = prepareArticle();
        int pos = articleDAO.insert(article);
        assertEquals(len, articleDAO.getAll().size() - 1);
        article.setId(pos);
        article.setTopic("TEST TOPIC");
        articleDAO.update(article);
        assertEquals("TEST TOPIC", articleDAO.getArticle(pos).getTopic());
        articleDAO.updateRate(2, pos);
        assertTrue(2 == articleDAO.getArticle(pos).getRate());
        assertEquals(1, articleDAO.getArticle(pos).getRateNumber());
        articleDAO.delete(pos);
        assertEquals(len, articleDAO.getAll().size());
    }

    @Test
    public void TestRates() throws SQLException {
        PreparedStatement ps = dataBase.getCn().prepareStatement("insert into articles (rate, rate_number) values(3,1)",
                Statement.RETURN_GENERATED_KEYS);
        ps.execute();
        ResultSet rs = ps.getGeneratedKeys();
        int aID = 0;
        if(rs.next())
            aID = rs.getInt(1);

        ps = dataBase.getCn().prepareStatement("insert into users (name, password) values('test','test')",
                Statement.RETURN_GENERATED_KEYS);
        ps.execute();
        rs = ps.getGeneratedKeys();
        int uID = 0;
        if(rs.next())
            uID = rs.getInt(1);

        ratesDAO.insert(prepareRate(aID, uID));
        assertTrue(ratesDAO.ifRated(uID, aID));

        ps = dataBase.getCn().prepareStatement("delete from users where id=?");
        ps.setInt(1, uID);
        ps.execute();
        ps = dataBase.getCn().prepareStatement("delete from articles where id=?");
        ps.setInt(1, aID);
        ps.execute();
    }


    @After
    public void onEnd() throws SQLException {
        PreparedStatement ps = dataBase.getCn().prepareStatement("delete from users where id=?");
        ps.setInt(1, uID);
        ps.execute();

        dataBase.close();
        dataBase = null;
        articleDAO = null;
        usersDAO = null;
        ratesDAO = null;
    }

    private Article prepareArticle() {
        Article article = new Article();
        article.setTopic("TEST");
        article.setSummary("TEST SUMMARY");
        article.setContent("TEST CONTENT");
        article.setRate(3);
        article.setRateNumber(1);
        article.setId(0);

        return article;
    }

    private Rate prepareRate(int aID, int uID) {
        Rate rate = new Rate();
        rate.setArticleId(aID);
        rate.setUserId(uID);
        return rate;
    }

}



