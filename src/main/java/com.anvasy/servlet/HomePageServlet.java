package com.anvasy.servlet;


import com.anvasy.dao.ArticleDAO;
import com.anvasy.dao.UsersDAO;
import com.anvasy.database.DataBase;
import com.anvasy.model.User;
import com.anvasy.utils.OAuthUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

public class HomePageServlet extends HttpServlet {

    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(HomePageServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

            String code = request.getParameter("code");
            if (code != null) {
                JSONObject json = OAuthUtils.getVkAccess(code);
                String token = String.valueOf(json.get("access_token"));
                String userId = String.valueOf(json.get("user_id"));

                JSONObject dataJson = OAuthUtils.getVkUserdata(token, userId);

                try(DataBase dataBase = new DataBase()) {
                    UsersDAO usersDAO = new UsersDAO(dataBase);
                    User u = usersDAO.getUser(userId);
                    if (u != null) {
                        HttpSession session = request.getSession();
                        session.setAttribute("user", u.getId());
                        session.setAttribute("role", u.getRole());
                    } else {
                        User newUser = OAuthUtils.registerVK(dataJson, userId, dataBase);
                        HttpSession session = request.getSession();
                        session.setAttribute("user", newUser.getId());
                        session.setAttribute("role", newUser.getRole());
                    }
                } catch (SQLException | ClassNotFoundException e) { }
            }

        try(DataBase dataBase = new DataBase()) {
            request.setAttribute("articles", new ArticleDAO(dataBase).getAll());
            getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) { logger.error(ExceptionUtils.getStackTrace(e)); }
    }

}
