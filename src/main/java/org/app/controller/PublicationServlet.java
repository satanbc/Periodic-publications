package org.app.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import org.app.dto.PublicationDTO;
import org.app.service.PublicationService;
import org.app.model.Publication;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/publications")
public class PublicationServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(PublicationServlet.class);
    private final PublicationService publicationService = new PublicationService();
    private final Gson gson;

    public PublicationServlet() {
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, typeOfT, context) ->
                        LocalDate.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE))
                .create();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        List<PublicationDTO> publications = publicationService.getAllPublications();

        if (publications == null) {
            publications = List.of();
        }

        String json = gson.toJson(publications);
        response.getWriter().write(json);

        logger.info("Fetched {} publications", publications.size());
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
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            double monthlyPrice = Double.parseDouble(request.getParameter("monthlyPrice"));

            Publication newPub = Publication.builder()
                    .title(title)
                    .description(description)
                    .monthlyPrice(monthlyPrice)
                    .active(true)
                    .build();

            publicationService.addPublication(newPub);

            logger.info("New publication added: Title: {}, Monthly Price: {}", title, monthlyPrice);

            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"message\":\"Publication added successfully\"}");
        } catch (Exception e) {
            logger.error("Failed to add publication. Error: {}", e.getMessage(), e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"Failed to add publication\"}");
        }
    }
}
