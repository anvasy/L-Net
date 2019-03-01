package com.anvasy.dao;

import com.anvasy.database.DataBase;
import com.anvasy.model.Rate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RatesDAO {

    private DataBase db;

    public RatesDAO(DataBase dataBase) {
        this.db = dataBase;
    }

    public void insert(Rate rate) throws SQLException {
        PreparedStatement ps = db.getCn().prepareStatement(
                "insert into rated_articles (user_id,article_id)"
                        + "values(?,?)");

        ps.setInt(1, rate.getUserId());
        ps.setInt(2, rate.getArticleId());

        ps.execute();
    }

    public boolean ifRated(int userId, int articleId) throws SQLException {
        PreparedStatement ps = db.getCn().prepareStatement("select * from rated_articles where article_id=? and user_id=?");
        ps.setInt(1, articleId);
        ps.setInt(2, userId);
        ResultSet rs = ps.executeQuery();

        return rs.next();
    }
}