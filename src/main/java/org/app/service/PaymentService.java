package org.app.service;

import org.app.dao.PaymentDAO;
import org.app.dto.PaymentDTO;
import org.app.mapper.PaymentMapper;
import org.app.model.Payment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentService {
    private final PaymentDAO paymentDAO = new PaymentDAO();
    private final PaymentMapper mapper = PaymentMapper.INSTANCE;

    public void registerPayment(PaymentDTO dto) {
        Payment payment = Payment.builder()
                .subscriptionId(dto.getSubscriptionId())
                .amount(dto.getAmount())
                .paymentDate(LocalDateTime.now())
                .status("PAID") // або "PENDING"
                .build();

        paymentDAO.save(payment);
    }

    public List<PaymentDTO> getAllPayments() {
        return paymentDAO.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}
