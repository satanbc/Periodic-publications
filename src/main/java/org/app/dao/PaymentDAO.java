package org.app.dao;

import org.app.model.Payment;
import org.app.util.DBConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {

    private static final Logger logger = LogManager.getLogger(PaymentDAO.class);

    public void addPayment(Payment payment) {
        String sql = "INSERT INTO payments (subscription_id, amount, paid_at) VALUES (?, ?, ?)";
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, payment.getSubscriptionId());
            stmt.setDouble(2, payment.getAmount());
            stmt.setDate(3, Date.valueOf(payment.getDate()));
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Payment> findAll() {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT id, subscription_id, amount, paid_at FROM payments";

        try (PreparedStatement stmt = DBConnectionManager.getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Payment payment = new Payment();
                payment.setId(rs.getLong("id"));
                payment.setSubscriptionId(rs.getLong("subscription_id"));
                payment.setAmount(rs.getDouble("amount"));
                payment.setDate(rs.getObject("date", LocalDate.class));

                payments.add(payment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return payments;
    }
}
