package org.app.service;

import org.app.dao.PaymentDAO;
import org.app.dto.PaymentDTO;
import org.app.mapper.PaymentMapper;
import org.app.model.Payment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.List;

public class PaymentService {
    private static final Logger logger = LogManager.getLogger(PaymentService.class);
    private final PaymentDAO paymentDAO = new PaymentDAO();
    private final PaymentMapper mapper = PaymentMapper.INSTANCE;

    public void registerPayment(PaymentDTO dto) {
        if (dto.getAmount() <= 0) {
            throw new IllegalArgumentException("Payment amount must be positive");
        }

        Payment payment = Payment.builder()
                .subscriptionId(dto.getSubscriptionId())
                .amount(dto.getAmount())
                .paymentDate(LocalDateTime.now())
                .status("PAID")
                .build();

        paymentDAO.save(payment);
        logger.info("Registered new payment: {}", payment);
    }

    public List<PaymentDTO> getAllPayments() {
        List<PaymentDTO> payments = mapper.toDTOList(paymentDAO.findAll());
        logger.info("Fetched {} payments", payments.size());
        return payments;
    }
}
