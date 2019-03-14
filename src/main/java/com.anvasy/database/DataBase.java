package com.anvasy.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DataBase implements AutoCloseable{

    private Connection cn;

    public DataBase() throws ClassNotFoundException, SQLException, IOException {
        /*FileInputStream fis = new FileInputStream("src/resources/database.properties");
        Properties property = new Properties();
        property.load(fis);*/

        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/l_net?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "root";
        String password = "mrdrprpt";
        cn = DriverManager.getConnection(url, user, password);
    }

    public Connection getCn() {
        return cn;
    }

    @Override
    public void close() throws SQLException {
        cn.close();
    }
}
