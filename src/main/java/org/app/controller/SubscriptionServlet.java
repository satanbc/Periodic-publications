package org.app.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import org.app.service.SubscriptionService;
import org.app.dto.SubscriptionDTO;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/subscription/*")
public class SubscriptionServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(SubscriptionServlet.class);
    private final SubscriptionService subscriptionService = new SubscriptionService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // CORS headers
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        Long userId = Long.parseLong(request.getParameter("userId"));
        Long publicationId = Long.parseLong(request.getParameter("publicationId"));
        int months = Integer.parseInt(request.getParameter("months"));

        SubscriptionDTO subscriptionDTO = new SubscriptionDTO(publicationId, months);
        subscriptionService.createSubscription(userId, subscriptionDTO);

        logger.info("User " + userId + " subscribed to publication " + publicationId);
        response.sendRedirect("/subscription/list");
    }
}
