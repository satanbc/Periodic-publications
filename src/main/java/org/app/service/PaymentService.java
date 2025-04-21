package org.app.service;

import org.app.dao.PaymentDAO;
import org.app.dao.SubscriptionDAO;
import org.app.model.Payment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class PaymentService {
    private static final Logger logger = LogManager.getLogger(PaymentService.class);
    private final PaymentDAO paymentDAO = new PaymentDAO();
    private final SubscriptionDAO subscriptionDAO = new SubscriptionDAO();

    public void addPayment(Payment payment) throws SQLException {

        paymentDAO.addPayment(payment);

        Long subscriptionId = payment.getSubscriptionId();
        if (subscriptionId != null) {
            subscriptionDAO.updateStatus(subscriptionId, true);
        } else {
            throw new IllegalArgumentException("Subscription ID is null in PaymentDTO");
        }
    }
}
