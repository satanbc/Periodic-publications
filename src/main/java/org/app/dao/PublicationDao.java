package org.app.dao;

import org.app.model.Publication;
import org.app.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PublicationDao {

    private static final String FIND_ALL = "SELECT * FROM publications";
    private static final String FIND_BY_ID = "SELECT * FROM publications WHERE id = ?";
    private static final String INSERT = "INSERT INTO publications (title, description, price, frequency) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE publications SET title = ?, description = ?, price = ?, frequency = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM publications WHERE id = ?";

    public List<Publication> findAll() {
        List<Publication> publications = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(FIND_ALL);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                publications.add(mapToPublication(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return publications;
    }

    public Publication findById(int id) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(FIND_BY_ID)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapToPublication(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void create(Publication publication) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT)) {

            stmt.setString(1, publication.getTitle());
            stmt.setString(2, publication.getDescription());
            stmt.setBigDecimal(3, publication.getPrice());
            stmt.setString(4, publication.getFrequency());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean update(Publication publication) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE)) {

            stmt.setString(1, publication.getTitle());
            stmt.setString(2, publication.getDescription());
            stmt.setBigDecimal(3, publication.getPrice());
            stmt.setString(4, publication.getFrequency());
            stmt.setInt(5, publication.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Publication mapToPublication(ResultSet rs) throws SQLException {
        Publication p = new Publication();
        p.setId(rs.getInt("id"));
        p.setTitle(rs.getString("title"));
        p.setDescription(rs.getString("description"));
        p.setPrice(rs.getBigDecimal("price"));
        p.setFrequency(rs.getString("frequency"));
        return p;
    }
}
