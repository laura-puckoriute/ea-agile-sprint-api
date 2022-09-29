package org.kainos.ea.util;

import org.kainos.ea.exception.DatabaseConnectionException;

import java.io.FileInputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static java.sql.Connection conn;

    public java.sql.Connection getConnection() throws DatabaseConnectionException, SQLException {
        String user;
        String password;
        String host;
        String database;

        if (conn != null) {
            return conn;
        }

        try {

            user            = System.getenv("USER");
            password        = System.getenv("PASSWORD");
            host            = System.getenv("HOST");
            database        = System.getenv("DATABASE");
            
            java.util.logging.Logger logger =  java.util.logging.Logger.getLogger(this.getClass().getName());
            logger.info("HOST: "+ host);

            if (user == null || password == null || host == null)
                throw new IllegalArgumentException(
                        "Environment variables USER, PASSWORD, DATABASE and HOST must exist.");
            conn = DriverManager.getConnection("jdbc:mysql://"
                    + host + "/" + database + "?allowPublicKeyRetrieval=true&useSSL=false", user, password);
            return conn;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
