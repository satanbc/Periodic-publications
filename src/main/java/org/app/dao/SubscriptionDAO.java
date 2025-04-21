package org.app.dao;

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
                subscriptions.add(new Subscription(
                        rs.getLong("id"),
                        rs.getString("email"),
                        rs.getLong("publication_id"),
                        rs.getInt("months"),
                        rs.getDate("start_date").toLocalDate(),
                        rs.getDate("end_date").toLocalDate(),
                        rs.getBoolean("active"),
                        rs.getBigDecimal("total_price")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subscriptions;
    }

    public List<Subscription> findByEmail(String email) {
        List<Subscription> subscriptions = new ArrayList<>();
        String sql = "SELECT * FROM subscriptions WHERE email = ?";

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                subscriptions.add(new Subscription(
                        rs.getLong("id"),
                        rs.getString("email"),
                        rs.getLong("publication_id"),
                        rs.getInt("months"),
                        rs.getDate("start_date").toLocalDate(),
                        rs.getDate("end_date").toLocalDate(),
                        rs.getBoolean("active"),
                        rs.getBigDecimal("total_price")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subscriptions;
    }

    public Long addSubscription(Subscription subscription) {
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO subscriptions (email, publication_id, months, start_date, end_date, active, total_price) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id")) {

            stmt.setString(1, subscription.getEmail());
            stmt.setLong(2, subscription.getPublicationId());
            stmt.setInt(3, subscription.getMonths());
            stmt.setDate(4, Date.valueOf(subscription.getStartDate()));
            stmt.setDate(5, Date.valueOf(subscription.getEndDate()));
            stmt.setBoolean(6, subscription.isActive());
            stmt.setBigDecimal(7, subscription.getTotalPrice());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getLong("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Subscription getSubscriptionById(Long id) {
        String sql = "SELECT * FROM subscriptions WHERE id = ?";
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Subscription(
                        rs.getLong("id"),
                        rs.getString("email"),
                        rs.getLong("publication_id"),
                        rs.getInt("months"),
                        rs.getDate("start_date").toLocalDate(),
                        rs.getDate("end_date").toLocalDate(),
                        rs.getBoolean("active"),
                        rs.getBigDecimal("total_price")
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
