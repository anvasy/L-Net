package com.anvasy.servlet;

import com.anvasy.dao.UsersDAO;
import com.anvasy.database.DataBase;
import com.anvasy.model.User;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class RegisterServlet extends HttpServlet {

    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(RegisterServlet.class);

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/register.jsp").forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");
        String name = StringUtils.defaultString(httpServletRequest.getParameter("name"));
        String surname = StringUtils.defaultString(httpServletRequest.getParameter("surname"));
        try(DataBase dataBase = new DataBase()) {
            UsersDAO usersDAO = new UsersDAO(dataBase);
            if(usersDAO.getUser(username, password) == null) {
                HttpSession session = httpServletRequest.getSession();
                User user = new User(username, password);
                user.setName(name);
                user.setUsername(surname);
                user.setRegType("none");
                session.setAttribute("user", usersDAO.insert(user));
                session.setAttribute("role", "user");
                httpServletResponse.sendRedirect("home");
            } else {
                PrintWriter out = httpServletResponse.getWriter();
                out.println("<script type=\"text/javascript\">alert('Username already exists.');location='register.jsp';</script>");
            }
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
    }
}
