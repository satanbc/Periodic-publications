package org.app.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import org.app.dto.PublicationDTO;
import org.app.service.PublicationService;
import org.app.model.Publication;

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

    private final PublicationService publicationService = new PublicationService();
    private final Gson gson;

    public PublicationServlet() {
        // Registering the LocalDate serializer/deserializer
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, typeOfT, context) ->
                        LocalDate.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE))
                .create();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // CORS headers
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        List<PublicationDTO> publications = publicationService.getAllPublications();

        if (publications == null) {
            publications = List.of(); // ensure it's not null
        }

        String json = gson.toJson(publications); // Convert to JSON using the custom Gson instance
        response.getWriter().write(json);
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
                    .active(true)  // Make sure it's set to active
                    .build();

            publicationService.addPublication(newPub);

            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"message\":\"Publication added successfully\"}");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"Failed to add publication\"}");
        }
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
    }
}

