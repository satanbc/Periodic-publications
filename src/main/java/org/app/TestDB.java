package org.app;

import org.app.utils.DBConnection;

import java.sql.Connection;

public class TestDB {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("Connected: " + !conn.isClosed());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
