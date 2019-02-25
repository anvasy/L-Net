package com.anvasy.servlet;

import com.anvasy.dao.ArticleDAO;
import com.anvasy.database.DataBase;
import org.apache.commons.lang.exception.ExceptionUtils;

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
            new ArticleDAO(dataBase).delete(Integer.valueOf(id));
            logger.info("Article with id " + id + "was deleted.");
            httpServletResponse.sendRedirect("/home");
        } catch (SQLException | ClassNotFoundException e) { logger.error(ExceptionUtils.getStackTrace(e)); }
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String id = httpServletRequest.getParameter("id");
        if(id.equals("")) {
            httpServletResponse.sendRedirect("/home.jsp");
            return;
        }
        try (DataBase dataBase = new DataBase()) {
            httpServletRequest.setAttribute("article", new ArticleDAO(dataBase).getArticle(Integer.valueOf(id)));
            httpServletRequest.getRequestDispatcher("/article.jsp").forward(httpServletRequest, httpServletResponse);
        } catch (SQLException | ClassNotFoundException e) { logger.error(ExceptionUtils.getStackTrace(e)); }
    }
}
