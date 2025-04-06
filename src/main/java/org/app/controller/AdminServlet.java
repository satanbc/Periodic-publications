package org.app.controller;

import org.apache.logging.log4j.LogManager;
import org.app.service.PublicationService;
import org.app.dto.PublicationDTO;
import org.app.model.Publication;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/*")
public class AdminServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(AdminServlet.class);
    private final PublicationService publicationService = new PublicationService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        if ("/publications".equals(path)) {
            List<PublicationDTO> publications = publicationService.getAllActivePublications();
            request.setAttribute("publications", publications);
            request.getRequestDispatcher("/admin/publications.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        double monthlyPrice = Double.parseDouble(request.getParameter("monthlyPrice"));

        PublicationDTO publicationDTO = new PublicationDTO(title, description, monthlyPrice);
        publicationService.addPublication(publicationDTO);

        logger.info("New publication added: " + title);
        response.sendRedirect("/admin/publications");
    }
}
