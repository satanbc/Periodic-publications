package org.app.dao;

import org.app.dto.SubscriptionDTO;
import org.app.model.Subscription;
import org.app.util.DBConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionDAO {

    public List<Subscription> findAll() {
        List<Subscription> subscriptions = new ArrayList<>();
        String sql = "SELECT * FROM subscriptions";

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                subscriptions.add(Subscription.builder()
                        .id(rs.getLong("id"))
                        .userId(rs.getLong("user_id"))
                        .publicationId(rs.getLong("publication_id"))
                        .months(rs.getInt("months"))
                        .startDate(rs.getDate("start_date").toLocalDate())
                        .endDate(rs.getDate("end_date").toLocalDate())
                        .active(rs.getBoolean("active"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subscriptions;
    }

    public Long addSubscription(Subscription subscription) {
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO subscriptions (user_id, publication_id, months, start_date, end_date, active) VALUES (?, ?, ?, ?, ?, ?) RETURNING id")) {

            stmt.setLong(1, subscription.getUserId());
            stmt.setLong(2, subscription.getPublicationId());
            stmt.setInt(3, subscription.getMonths());
            stmt.setDate(4, Date.valueOf(subscription.getStartDate()));
            stmt.setDate(5, Date.valueOf(subscription.getEndDate()));
            stmt.setBoolean(6, subscription.isActive());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getLong("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public SubscriptionDTO getSubscriptionById(Long id) {
        String sql = "SELECT * FROM subscriptions WHERE id = ?";
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new SubscriptionDTO(
                        rs.getLong("id"),
                        rs.getLong("user_id"),
                        rs.getLong("publication_id"),
                        rs.getInt("months"),
                        rs.getDate("start_date").toLocalDate(),
                        rs.getDate("end_date").toLocalDate(),
                        rs.getBoolean("active")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateStatus(Long subscriptionId, boolean isActive) throws SQLException {
        String sql = "UPDATE subscriptions SET active = ? WHERE id = ?";
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, isActive);
            stmt.setLong(2, subscriptionId);
            stmt.executeUpdate();
        }
    }
}
