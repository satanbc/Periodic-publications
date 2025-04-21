package org.app.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.app.model.Payment;
import org.app.service.PaymentService;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/payment/*")
public class PaymentServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(PaymentServlet.class);
    private final PaymentService paymentService = new PaymentService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            Long subscriptionId = Long.parseLong(request.getParameter("subscriptionId"));
            double amount = Double.parseDouble(request.getParameter("amount"));

            Payment payment = new Payment(null, subscriptionId, amount, LocalDate.now());

            paymentService.addPayment(payment);

            logger.info("Payment created successfully for Subscription ID: {}, Amount: {}", subscriptionId, amount);

            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"message\":\"Payment created successfully\"}");
        } catch (Exception e) {
            logger.error("Failed to create payment. Error: {}", e.getMessage(), e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"Failed to create payment\"}");
        }
    }
}
