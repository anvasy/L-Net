package com.anvasy.servlet;


import com.anvasy.dao.ArticleDAO;
import com.anvasy.database.DataBase;
import org.apache.commons.lang.exception.ExceptionUtils;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

public class HomePageServlet extends HttpServlet {

    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(HomePageServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try(DataBase dataBase = new DataBase()) {
            request.setAttribute("articles", new ArticleDAO(dataBase).getAll());
            getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) { logger.error(ExceptionUtils.getStackTrace(e)); }
    }

}
