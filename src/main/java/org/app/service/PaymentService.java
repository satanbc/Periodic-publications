package org.app.service;

import org.app.dao.PaymentDAO;
import org.app.dao.SubscriptionDAO;
import org.app.dto.PaymentDTO;
import org.app.mapper.PaymentMapper;
import org.app.model.Payment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class PaymentService {
    private static final Logger logger = LogManager.getLogger(PaymentService.class);
    private final PaymentDAO paymentDAO = new PaymentDAO();
    private final SubscriptionDAO subscriptionDAO = new SubscriptionDAO();

    public void addPayment(PaymentDTO paymentDTO) throws SQLException {
        Payment payment = new Payment(
                null,
                paymentDTO.getSubscriptionId(),
                paymentDTO.getAmount(),
                paymentDTO.getDate()
        );

        paymentDAO.addPayment(payment);

        Long subscriptionId = paymentDTO.getSubscriptionId();
        if (subscriptionId != null) {
            subscriptionDAO.updateStatus(subscriptionId, true);
        } else {
            throw new IllegalArgumentException("Subscription ID is null in PaymentDTO");
        }
    }
}
