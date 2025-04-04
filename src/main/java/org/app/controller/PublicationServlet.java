package org.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.app.dto.PublicationDto;
import org.app.mapper.PublicationMapper;
import org.app.model.Publication;
import org.app.service.PublicationService;
import org.app.utils.JsonUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/publications/*")
public class PublicationServlet extends HttpServlet {

    private final PublicationService publicationService = new PublicationService();
    private final ObjectMapper objectMapper = new ObjectMapper(); // Jackson

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();

        resp.setContentType("application/json");
        if (pathInfo == null || pathInfo.equals("/")) {
            List<Publication> publications = publicationService.findAll();
            JsonUtil.writeJson(resp, publications);
        } else {
            try {
                int id = Integer.parseInt(pathInfo.substring(1));
                Publication publication = publicationService.findById(id);
                if (publication != null) {
                    JsonUtil.writeJson(resp, publication);
                } else {
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Publication not found");
                }
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid ID format");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PublicationDto dto = objectMapper.readValue(req.getReader(), PublicationDto.class);
        Publication publication = PublicationMapper.INSTANCE.toEntity(dto);
        publicationService.create(publication);
        resp.setStatus(HttpServletResponse.SC_CREATED);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID is required for update");
            return;
        }

        int id = Integer.parseInt(pathInfo.substring(1));
        PublicationDto dto = objectMapper.readValue(req.getReader(), PublicationDto.class);
        Publication publication = PublicationMapper.INSTANCE.toEntity(dto);
        publication.setId(id);
        boolean updated = publicationService.update(publication);

        if (updated) {
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Publication not found");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID is required for delete");
            return;
        }

        int id = Integer.parseInt(pathInfo.substring(1));
        boolean deleted = publicationService.delete(id);
        if (deleted) {
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Publication not found");
        }
    }
}
