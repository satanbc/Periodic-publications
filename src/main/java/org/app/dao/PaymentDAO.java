package org.app.dao;

import org.app.model.Payment;
import org.app.util.DBConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {

    private static final Logger logger = LogManager.getLogger(PaymentDAO.class);

    public void save(Payment payment) {
        String sql = "INSERT INTO payments (subscription_id, amount, payment_date, status) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, payment.getSubscriptionId());
            stmt.setDouble(2, payment.getAmount());
            stmt.setTimestamp(3, Timestamp.valueOf(payment.getPaymentDate()));
            stmt.setString(4, payment.getStatus());
            stmt.executeUpdate();

        } catch (SQLException e) {
            logger.error("Error saving payment: " + payment, e);
        }
    }

    public List<Payment> findAll() {
        List<Payment> list = new ArrayList<>();
        String sql = "SELECT * FROM payments";

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(Payment.builder()
                        .id(rs.getLong("id"))
                        .subscriptionId(rs.getLong("subscription_id"))
                        .amount(rs.getDouble("amount"))
                        .paymentDate(rs.getTimestamp("payment_date").toLocalDateTime())
                        .status(rs.getString("status"))
                        .build());
            }

        } catch (SQLException e) {
            logger.error("Error retrieving payments", e);
        }

        return list;
    }
}
