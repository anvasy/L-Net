package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase implements AutoCloseable{

    private Connection cn;

    public DataBase() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/l_net?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "root";
        String password = "mrdrprpt";       //CONSTANTS
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
