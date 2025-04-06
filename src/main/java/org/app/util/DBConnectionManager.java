package org.app.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManager {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "admin";

    private static final Logger logger = LogManager.getLogger(DBConnectionManager.class);

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            logger.error("PostgreSQL JDBC Driver not found", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        logger.info("Attempting to connect to the database...");

        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            logger.info("Connection established successfully.");
            return connection;
        } catch (SQLException e) {
            logger.error("Failed to establish connection to the database", e);
            throw e;
        }
    }
}
