package com.anvasy.servlet;

import com.anvasy.dao.ArticleDAO;
import com.anvasy.dao.UsersDAO;
import com.anvasy.database.DataBase;
import com.anvasy.model.User;
import com.anvasy.utils.OAuthUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class AuthServlet extends HttpServlet {

    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AuthServlet.class);

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String code = httpServletRequest.getParameter("code");
        JSONObject json = OAuthUtils.getGitAccess(code);
        String token = String.valueOf(json.get("access_token"));
        JSONObject dataJson = OAuthUtils.getGitUserData(token);

        try(DataBase dataBase = new DataBase()) {
            UsersDAO usersDAO = new UsersDAO(dataBase);
            User user = usersDAO.getUser(String.valueOf(dataJson.get("id")));
            if(user != null) {
                HttpSession session = httpServletRequest.getSession();
                session.setAttribute("user", user.getId());
                session.setAttribute("role", user.getRole());
            } else {
                User newUser = OAuthUtils.registerGit(dataJson, dataBase);
                HttpSession session = httpServletRequest.getSession();
                session.setAttribute("user", newUser.getId());
                session.setAttribute("role", newUser.getRole());
            }
            httpServletRequest.setAttribute("articles", new ArticleDAO(dataBase).getAll());
            getServletContext().getRequestDispatcher("/home.jsp").forward(httpServletRequest, httpServletResponse);
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
    }


}
