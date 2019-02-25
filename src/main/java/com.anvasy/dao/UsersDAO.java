package com.anvasy.dao;

import com.anvasy.database.DataBase;
import com.anvasy.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO {

    private DataBase db;

    public UsersDAO(DataBase dataBase) {
        this.db = dataBase;
    }

    public void insert(User user) throws SQLException {
        PreparedStatement ps = db.getCn().prepareStatement(
                "insert into users (name,password)"
                        + "values(?,?)");

        ps.setString(1, user.getName());
        ps.setString(2, user.getPassword());
        ps.execute();
    }

    public void delete(int id) throws SQLException {
        PreparedStatement ps = db.getCn().prepareStatement(
                "delete from users where id = ?");
        ps.setInt(1, id);
        ps.execute();
    }

    public void update(User user) throws SQLException {
        PreparedStatement ps = db.getCn().prepareStatement(
                "update users set name=?, password=? where id = ?");
        ps.setString(1, user.getName());
        ps.setString(2, user.getPassword());
        ps.setInt(3, user.getId());

        ps.execute();
    }

    public List<User> getAll() throws SQLException{
        List<User> users = new ArrayList<>();

        PreparedStatement preparedStatement = db.getCn().prepareStatement("select * from users");
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));

            users.add(user);
        }
        return users;
    }

    public boolean getUser(String name, String password) throws SQLException {
        PreparedStatement preparedStatement = db.getCn().prepareStatement("select * from users where name = ? and password = ?");
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, password);
        ResultSet rs = preparedStatement.executeQuery();
        return rs.next();
    }

}
