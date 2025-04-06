package org.app.controller;

import org.app.service.PaymentService;
import org.app.dto.PaymentDTO;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/payment/*")
public class PaymentServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(PaymentServlet.class);
    private final PaymentService paymentService = new PaymentService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long subscriptionId = Long.parseLong(request.getParameter("subscriptionId"));
        double amount = Double.parseDouble(request.getParameter("amount"));

        PaymentDTO paymentDTO = new PaymentDTO(subscriptionId, amount);
        paymentService.registerPayment(paymentDTO);

        logger.info("Payment registered for subscription " + subscriptionId + " with amount " + amount);
        response.sendRedirect("/payment/list");
    }
}
