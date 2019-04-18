package com.anvasy.database;

import java.io.IOException;
import java.sql.*;

public class DataBase implements AutoCloseable{

    private Connection cn;

    public DataBase() throws ClassNotFoundException, SQLException, IOException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/l_net?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&characterEncoding=utf8";
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
