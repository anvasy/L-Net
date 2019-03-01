package com.anvasy.servlet;

import com.anvasy.dao.ArticleDAO;
import com.anvasy.dao.RatesDAO;
import com.anvasy.database.DataBase;
import com.anvasy.model.Rate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class BonusServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.getSession().removeAttribute("user");
        httpServletResponse.sendRedirect("/home");
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        int userId = Integer.valueOf(httpServletRequest.getSession().getAttribute("user").toString());
        String articleId = httpServletRequest.getParameter("arid");
        try(DataBase dataBase = new DataBase()) {
            RatesDAO ratesDAO = new RatesDAO(dataBase);
            if(ratesDAO.ifRated(userId, Integer.valueOf(articleId))) {
                PrintWriter out = httpServletResponse.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('You already rated this article.');");
                out.println("location='home';");
                out.println("</script>");
            } else {
                Rate rate = new Rate();
                rate.setUserId(Integer.valueOf(httpServletRequest.getSession().getAttribute("user").toString()));
                rate.setArticleId(Integer.valueOf(httpServletRequest.getParameter("arid")));
                ratesDAO.insert(rate);
                ArticleDAO articleDAO = new ArticleDAO(dataBase);
                articleDAO.updateRate(Integer.valueOf(httpServletRequest.getParameter("rated")), Integer.valueOf(articleId));
                httpServletResponse.sendRedirect("/home");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
