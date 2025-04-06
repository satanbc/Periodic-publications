package org.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.app.dto.PublicationDTO;
import org.app.service.PublicationService;
import org.app.model.Publication;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/publications")
public class PublicationServlet extends HttpServlet {

    private final PublicationService publicationService = new PublicationService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the list of active publications from the service
        List<PublicationDTO> publications = publicationService.getAllActivePublications();

        // Set the list of publications as a request attribute
        request.setAttribute("publications", publications);

        // Forward the request to the publications.jsp page to display the publications
        request.getRequestDispatcher("/WEB-INF/views/publications.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle POST requests (e.g., for adding new publications)
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        double monthlyPrice = Double.parseDouble(request.getParameter("monthlyPrice"));

        // Create a PublicationDTO and add the new publication
        PublicationDTO publicationDTO = new PublicationDTO();
        publicationDTO.setTitle(title);
        publicationDTO.setDescription(description);
        publicationDTO.setMonthlyPrice(monthlyPrice);

        publicationService.addPublication(publicationDTO);

        // Redirect back to the publications page after adding a publication
        response.sendRedirect("/publications");
    }
}
