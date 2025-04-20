package org.app.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.app.dto.SubscriptionDTO;
import org.app.model.Publication;
import org.app.service.SubscriptionService;
import org.app.service.PublicationService;
import org.app.util.LocalDateAdapter;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/subscription")
public class SubscriptionServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(SubscriptionServlet.class);
    private final SubscriptionService subscriptionService = new SubscriptionService();
    private final PublicationService publicationService = new PublicationService();
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .create();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String idParam = request.getParameter("id");
        String emailParam = request.getParameter("email");

        try {
            if (idParam != null) {
                Long id = Long.parseLong(idParam);
                SubscriptionDTO subscription = subscriptionService.getSubscriptionById(id);
                if (subscription != null) {
                    response.getWriter().write(gson.toJson(subscription));
                    logger.info("Fetched subscription with ID: {}", id);
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write("{\"error\":\"Subscription not found\"}");
                    logger.warn("Subscription not found for ID: {}", id);
                }
            } else if (emailParam != null) {
                List<SubscriptionDTO> subscriptions = subscriptionService.getSubscriptionsByEmail(emailParam);
                if (!subscriptions.isEmpty()) {
                    response.getWriter().write(gson.toJson(subscriptions));
                    logger.info("Fetched {} subscriptions for email: {}", subscriptions.size(), emailParam);
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write("{\"error\":\"No subscriptions found for this email\"}");
                    logger.warn("No subscriptions found for email: {}", emailParam);
                }
            } else {
                List<SubscriptionDTO> subscriptions = subscriptionService.getAllSubscriptions();
                response.getWriter().write(gson.toJson(subscriptions));
                logger.info("Fetched all subscriptions. Total count: {}", subscriptions.size());
            }
        } catch (Exception e) {
            logger.error("Failed to retrieve subscription(s). Error: {}", e.getMessage(), e);
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"Failed to retrieve subscription(s)\"}");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            String email = request.getParameter("email");
            Long publicationId = Long.parseLong(request.getParameter("publicationId"));
            int months = Integer.parseInt(request.getParameter("months"));

            Publication publication = publicationService.getPublicationById(publicationId);
            if (publication == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("{\"error\":\"Publication not found\"}");
                logger.warn("Publication not found for ID: {}", publicationId);
                return;
            }

            BigDecimal monthlyPrice = BigDecimal.valueOf(publication.getMonthlyPrice());
            BigDecimal totalPrice = monthlyPrice.multiply(BigDecimal.valueOf(months));

            LocalDate startDate = LocalDate.now();
            LocalDate endDate = startDate.plusMonths(months);

            SubscriptionDTO subscriptionDTO = new SubscriptionDTO(
                    null,
                    email,
                    publicationId,
                    months,
                    startDate,
                    endDate,
                    false,
                    totalPrice
            );

            Long subscriptionId = subscriptionService.addSubscription(subscriptionDTO);

            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"subscriptionId\":" + subscriptionId + "}");
            logger.info("Created new subscription. Subscription ID: {}", subscriptionId);

        } catch (Exception e) {
            logger.error("Failed to create subscription. Error: {}", e.getMessage(), e);
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"Failed to create subscription\"}");
        }
    }
}
