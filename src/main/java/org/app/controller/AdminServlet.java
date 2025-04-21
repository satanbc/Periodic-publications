package org.app.controller;

import org.apache.logging.log4j.LogManager;
import org.app.model.Publication;
import org.app.model.Subscription;
import org.app.service.PublicationService;
import org.apache.logging.log4j.Logger;
import org.app.service.SubscriptionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/*")
public class AdminServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(AdminServlet.class);
    private final PublicationService publicationService = new PublicationService();
    private final SubscriptionService subscriptionService = new SubscriptionService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Allow-Methods", "GET");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        String path = request.getPathInfo();
        if ("/publications".equals(path)) {
            List<Publication> publications = publicationService.getAllPublications();
            List<Subscription> subscriptions = subscriptionService.getAllSubscriptions();
            request.setAttribute("publications", publications);
            request.setAttribute("subscriptions", subscriptions);

            logger.info("GET request for publications and subscriptions. Publications count: {}, Subscriptions count: {}", publications.size(), subscriptions.size());

            request.getRequestDispatcher("/admin/publications.jsp").forward(request, response);
        } else {
            logger.warn("Unknown path in GET request: {}", path);
        }
    }
}
