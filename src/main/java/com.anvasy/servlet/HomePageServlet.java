package com.anvasy.servlet;


import com.anvasy.dao.ArticleDAO;
import com.anvasy.database.DataBase;
import com.anvasy.model.Article;
import org.apache.commons.lang.exception.ExceptionUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class HomePageServlet extends HttpServlet {

    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(HomePageServlet.class);

     @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try(DataBase dataBase = new DataBase()) {
            ArticleDAO articleDAO = new ArticleDAO(dataBase);
            List<Article> articles = articleDAO.getAll();
            request.setAttribute("test", "test");
            request.setAttribute("articles", articles);
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/home.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException  {
    }
}
