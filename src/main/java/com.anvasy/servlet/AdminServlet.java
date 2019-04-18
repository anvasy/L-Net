package com.anvasy.servlet;

import com.anvasy.dao.UsersDAO;
import com.anvasy.database.DataBase;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AdminServlet extends HttpServlet {
    private Logger logger = org.apache.log4j.Logger.getLogger(AdminServlet.class);

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        try(DataBase dataBase = new DataBase()) {
            httpServletRequest.setAttribute("users", new UsersDAO(dataBase).getAll(
                    Integer.valueOf(String.valueOf(httpServletRequest.getSession().getAttribute("user")))));
            getServletContext().getRequestDispatcher("/admin.jsp").forward(httpServletRequest, httpServletResponse);
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String id = httpServletRequest.getParameter("id");
        try (DataBase dataBase = new DataBase()) {
            new UsersDAO(dataBase).changeRole(Integer.valueOf(id));
            httpServletResponse.sendRedirect("admin");
        } catch (SQLException | ClassNotFoundException e) { logger.error(ExceptionUtils.getStackTrace(e)); }
    }
}
