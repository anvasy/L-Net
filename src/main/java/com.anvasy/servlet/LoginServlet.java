package com.anvasy.servlet;

import com.anvasy.dao.UsersDAO;
import com.anvasy.database.DataBase;
import com.anvasy.model.User;
import org.apache.commons.lang.exception.ExceptionUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {

    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/login.jsp").forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String name = httpServletRequest.getParameter("name");
        String password = httpServletRequest.getParameter("password");
        try(DataBase dataBase = new DataBase()) {
            User user = new UsersDAO(dataBase).getUser(name, password);
            if(user != null) {
                HttpSession session = httpServletRequest.getSession();
                session.setAttribute("user", user.getId());
                session.setAttribute("role", user.getRole());
                httpServletResponse.sendRedirect("home");
            } else {
                PrintWriter out = httpServletResponse.getWriter();
                out.println("<script type=\"text/javascript\">alert('Incorrect username or password. Maybe both.');location='login.jsp';</script>");
            }
        } catch (SQLException | ClassNotFoundException e) { logger.error(ExceptionUtils.getStackTrace(e)); }
    }
}
