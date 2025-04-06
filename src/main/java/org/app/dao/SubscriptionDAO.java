package org.app.dao;

import org.app.model.Subscription;
import org.app.util.DBConnectionManager;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionDAO {

    public void save(Subscription subscription) {
        String sql = "INSERT INTO subscriptions (user_id, publication_id, months, start_date, end_date) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, subscription.getUserId());
            stmt.setLong(2, subscription.getPublicationId());
            stmt.setInt(3, subscription.getMonths());
            stmt.setDate(4, Date.valueOf(subscription.getStartDate()));
            stmt.setDate(5, Date.valueOf(subscription.getEndDate()));
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Subscription> findByUserId(Long userId) {
        List<Subscription> list = new ArrayList<>();
        String sql = "SELECT * FROM subscriptions WHERE user_id = ?";

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(Subscription.builder()
                        .id(rs.getLong("id"))
                        .userId(rs.getLong("user_id"))
                        .publicationId(rs.getLong("publication_id"))
                        .months(rs.getInt("months"))
                        .startDate(rs.getDate("start_date").toLocalDate())
                        .endDate(rs.getDate("end_date").toLocalDate())
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
