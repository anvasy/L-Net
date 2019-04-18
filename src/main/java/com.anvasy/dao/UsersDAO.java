package com.anvasy.dao;

import com.anvasy.database.DataBase;
import com.anvasy.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO {

    private DataBase db;

    public UsersDAO(DataBase dataBase) {
        this.db = dataBase;
    }

    public int insert(User user) throws SQLException {
        PreparedStatement ps = db.getCn().prepareStatement(
                "insert into users (username,password,name,surname,reg_type)"
                        + "values(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getName());
        ps.setString(4, user.getSurname());
        ps.setString(5, user.getRegType());
        ps.execute();

        ResultSet rs = ps.getGeneratedKeys();
        if(rs.next())
            return rs.getInt(1);

        return -1;
    }

    public User getUser(String name) throws SQLException {
        PreparedStatement preparedStatement = db.getCn().prepareStatement("select * from users where username = ?");
        preparedStatement.setString(1, name);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            User user = new User();
            user.setName(rs.getString("name"));
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setRole(rs.getString("role"));
            user.setSurname(rs.getString("surname"));

            return user;
        }
        return null;
    }

    public User getUser(String name, String password) throws SQLException {
        PreparedStatement preparedStatement = db.getCn().prepareStatement("select * from users where username = ? and password = ?");
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, password);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            User user = new User(name, password);
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            user.setRole(rs.getString("role"));
            return user;
        }
        return null;
    }

    public void changeRole(int id) throws SQLException {
        String role = "user";

        PreparedStatement preparedStatement = db.getCn().prepareStatement("select role from users where id = ?");
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next())
            role = rs.getString("role");

        if(role.equals("user"))
            role = "admin";
        else
            role = "user";

        PreparedStatement ps = db.getCn().prepareStatement("update users set role=? where id = ?");
        ps.setString(1, role);
        ps.setInt(2, id);
        ps.execute();
    }

    public List<User> getAll(int id) throws SQLException {
        List<User> users = new ArrayList<>();

        PreparedStatement preparedStatement = db.getCn().prepareStatement("select * from users where id <> ?");
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            user.setUsername(rs.getString("username"));
            user.setRole(rs.getString("role"));
            user.setRegType(rs.getString("reg_type"));

            users.add(user);
        }

        return users;
    }
}
