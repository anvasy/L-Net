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

public class RegisterServlet extends HttpServlet {

    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(RegisterServlet.class);

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/register.jsp").forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String name = httpServletRequest.getParameter("name");
        String password = httpServletRequest.getParameter("password");
        try(DataBase dataBase = new DataBase()) {
            UsersDAO usersDAO = new UsersDAO(dataBase);
            if(usersDAO.getUser(name, password) == -1) {
                HttpSession session = httpServletRequest.getSession();
                session.setAttribute("user", usersDAO.insert(new User(name, password)));
                httpServletResponse.sendRedirect("home");
            } else {
                PrintWriter out = httpServletResponse.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Username already exists.');");
                out.println("location='register.jsp';");
                out.println("</script>");
            }
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
    }
}
