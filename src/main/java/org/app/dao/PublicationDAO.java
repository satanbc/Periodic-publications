package org.app.dao;

import org.app.model.Publication;
import org.app.util.DBConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PublicationDAO {

    public List<Publication> findAll() {
        List<Publication> publications = new ArrayList<>();
        String sql = "SELECT * FROM publications WHERE active = true";

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                publications.add(Publication.builder()
                        .id(rs.getLong("id"))
                        .title(rs.getString("title"))
                        .description(rs.getString("description"))
                        .monthlyPrice(rs.getDouble("monthly_price"))
                        .active(rs.getBoolean("active"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace(); // заміни на логування
        }

        return publications;
    }

    public void save(Publication publication) {
        String sql = "INSERT INTO publications (title, description, monthly_price, active) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, publication.getTitle());
            stmt.setString(2, publication.getDescription());
            stmt.setDouble(3, publication.getMonthlyPrice());
            stmt.setBoolean(4, publication.isActive());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
