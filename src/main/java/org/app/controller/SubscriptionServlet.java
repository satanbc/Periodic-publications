package org.app.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import org.app.service.SubscriptionService;
import org.app.dto.SubscriptionDTO;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/subscription/*")
public class SubscriptionServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(SubscriptionServlet.class);
    private final SubscriptionService subscriptionService = new SubscriptionService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long userId = Long.parseLong(request.getParameter("userId"));
        Long publicationId = Long.parseLong(request.getParameter("publicationId"));
        int months = Integer.parseInt(request.getParameter("months"));

        SubscriptionDTO subscriptionDTO = new SubscriptionDTO(publicationId, months);
        subscriptionService.createSubscription(userId, subscriptionDTO);

        logger.info("User " + userId + " subscribed to publication " + publicationId);
        response.sendRedirect("/subscription/list");
    }
}
