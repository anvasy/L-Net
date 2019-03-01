package com.anvasy.dao;

import com.anvasy.database.DataBase;
import com.anvasy.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsersDAO {

    private DataBase db;

    public UsersDAO(DataBase dataBase) {
        this.db = dataBase;
    }

    public int insert(User user) throws SQLException {
        PreparedStatement ps = db.getCn().prepareStatement(
                "insert into users (name,password)"
                        + "values(?,?)", Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, user.getName());
        ps.setString(2, user.getPassword());
        ps.execute();

        ResultSet rs = ps.getGeneratedKeys();
        if(rs.next())
            return rs.getInt(1);

        return -1;
    }

    public int getUser(String name, String password) throws SQLException {
        PreparedStatement preparedStatement = db.getCn().prepareStatement("select * from users where name = ? and password = ?");
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, password);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next())
            return rs.getInt("id");
        return -1;
    }

}
