package com.anvasy.servlet;

import com.anvasy.dao.ArticleDAO;
import com.anvasy.database.DataBase;
import com.anvasy.model.Article;
import org.apache.commons.lang.exception.ExceptionUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddEditServlet extends HttpServlet {

    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AddEditServlet.class);

    @Override
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String id = httpServletRequest.getParameter("id");
        if(id.equals("0")) {
            httpServletRequest.setAttribute("article", new Article());
            httpServletRequest.getRequestDispatcher("/addedit.jsp").forward(httpServletRequest, httpServletResponse);
            return;
        }
        try (DataBase dataBase = new DataBase()) {
            httpServletRequest.setAttribute("article", new ArticleDAO(dataBase).getArticle(Integer.valueOf(id)));
            httpServletRequest.getRequestDispatcher("/addedit.jsp").forward(httpServletRequest, httpServletResponse);
        } catch (SQLException | ClassNotFoundException e) { logger.error(ExceptionUtils.getStackTrace(e)); }
    }

    @Override
    public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String id = httpServletRequest.getParameter("id");
        try (DataBase dataBase = new DataBase()) {
            ArticleDAO articleDAO = new ArticleDAO(dataBase);
            Article article = new Article(httpServletRequest.getParameter("name"),
                    httpServletRequest.getParameter("summary"), httpServletRequest.getParameter("content"));
            if(Integer.valueOf(id) != 0) {
                article.setId(Integer.valueOf(id));
                articleDAO.update(article);
                logger.info("New article on " + article.getTopic() + " added.");
            } else {
                articleDAO.insert(article);
                logger.info("Article with id " + id + " was changed.");
            }
            httpServletResponse.sendRedirect("/home");
        } catch (SQLException | ClassNotFoundException e) { logger.error(ExceptionUtils.getStackTrace(e)); }
    }
}
