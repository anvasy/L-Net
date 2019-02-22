package com.anvasy.servlet;

import com.anvasy.dao.ArticleDAO;
import com.anvasy.database.DataBase;
import com.anvasy.model.Article;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ShowArticleServlet extends HttpServlet {

    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(HomePageServlet.class);

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String id = httpServletRequest.getParameter("id");
        try (DataBase dataBase = new DataBase()) {
            ArticleDAO articleDAO = new ArticleDAO(dataBase);
            articleDAO.delete(Integer.valueOf(id));
            httpServletResponse.sendRedirect("/home");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String id = httpServletRequest.getParameter("id");
        if(id.equals("")) {
            httpServletResponse.sendRedirect("/home.jsp");
            return;
        }
        try (DataBase dataBase = new DataBase()) {
            ArticleDAO articleDAO = new ArticleDAO(dataBase);
            Article article = articleDAO.getArticle(Integer.valueOf(id));
            httpServletRequest.setAttribute("article", article);
            httpServletRequest.getRequestDispatcher("/article.jsp").forward(httpServletRequest, httpServletResponse);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
