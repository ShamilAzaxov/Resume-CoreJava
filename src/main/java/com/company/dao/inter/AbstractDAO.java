package com.company.dao.inter;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class AbstractDAO {

    public Connection connect() throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/resume?useSSL=false";
        String username = "resume";
        String password = "resume";

        Connection connection = DriverManager.getConnection(url, username ,password);
        return connection;
    }
}
