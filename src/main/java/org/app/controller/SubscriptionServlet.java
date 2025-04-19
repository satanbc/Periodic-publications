package org.app.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.app.dto.PaymentDTO;
import org.app.dto.SubscriptionDTO;
import org.app.model.Publication;
import org.app.service.PaymentService;
import org.app.service.PublicationService;
import org.app.service.SubscriptionService;
import org.app.util.LocalDateAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/subscription")
public class SubscriptionServlet extends HttpServlet {

    private final SubscriptionService subscriptionService = new SubscriptionService();
    private final PaymentService paymentService = new PaymentService();
    private final PublicationService publicationService = new PublicationService();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        String idParam = request.getParameter("id");

        try {
            if (idParam != null) {
                Long id = Long.parseLong(idParam);
                SubscriptionDTO subscription = subscriptionService.getSubscriptionById(id);
                if (subscription != null) {
                    response.getWriter().write(gson.toJson(subscription));
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write("{\"error\":\"Subscription not found\"}");
                }
            } else {
                List<SubscriptionDTO> subscriptions = subscriptionService.getAllSubscriptions();
                response.getWriter().write(gson.toJson(subscriptions));
            }
        } catch (Exception e) {
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
            Long userId = Long.parseLong(request.getParameter("userId"));
            Long publicationId = Long.parseLong(request.getParameter("publicationId"));
            int months = Integer.parseInt(request.getParameter("months"));

            // Отримуємо публікацію за ID для визначення ціни
            Publication publication = publicationService.getPublicationById(publicationId);
            if (publication == null) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"error\":\"Publication not found\"}");
                return;
            }

            // Обчислюємо суму на основі ціни публікації
            double totalAmount = publication.getMonthlyPrice() * months;

            LocalDate startDate = LocalDate.now();
            LocalDate endDate = startDate.plusMonths(months);

            SubscriptionDTO subscriptionDTO = new SubscriptionDTO(
                    null,
                    userId,
                    publicationId,
                    months,
                    startDate,
                    endDate,
                    false // Статус не активний до оплати
            );

            // Додаємо підписку
            Long subscriptionId = subscriptionService.addSubscription(userId, subscriptionDTO);

            // Повертаємо ID підписки та суму для оплати
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"subscriptionId\":" + subscriptionId + ", \"totalAmount\":" + totalAmount + "}");

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"Failed to create subscription\"}");
        }
    }


    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
    }
}
