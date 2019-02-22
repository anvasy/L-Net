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

public class AddEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String id = httpServletRequest.getParameter("id");
        if(id.equals("0")) {
            httpServletRequest.setAttribute("article", new Article());
            httpServletRequest.getRequestDispatcher("/addedit.jsp").forward(httpServletRequest, httpServletResponse);
            return;
        }
        try (DataBase dataBase = new DataBase()) {
            ArticleDAO articleDAO = new ArticleDAO(dataBase);
            httpServletRequest.setAttribute("article", articleDAO.getArticle(Integer.valueOf(id)));
            httpServletRequest.getRequestDispatcher("/addedit.jsp").forward(httpServletRequest, httpServletResponse);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String id = httpServletRequest.getParameter("id");
        try (DataBase dataBase = new DataBase()) {
            ArticleDAO articleDAO = new ArticleDAO(dataBase);
            Article article = new Article();
            article.setTopic(httpServletRequest.getParameter("name"));
            article.setSummary(httpServletRequest.getParameter("summary"));
            article.setContent(httpServletRequest.getParameter("content"));
            if(Integer.valueOf(id) != 0) {
                article.setId(Integer.valueOf(id));
                articleDAO.update(article);
            } else {
                articleDAO.insert(article);
            }
            httpServletResponse.sendRedirect("/home");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
