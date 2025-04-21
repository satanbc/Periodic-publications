package org.app.service;

import org.app.dao.PublicationDAO;
import org.app.model.Publication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class PublicationService {
    private static final Logger logger = LogManager.getLogger(PublicationService.class);
    private final PublicationDAO publicationDAO = new PublicationDAO();

    public List<Publication> getAllPublications() {
        List<Publication> publications = publicationDAO.findAll();
        logger.info("Fetched {} publications", publications.size());
        return publications;
    }

    public void addPublication(Publication publication) {
        publicationDAO.addPublication(publication);
    }

    public Publication getPublicationById(Long id) throws SQLException {
        return publicationDAO.findById(id);
    }
    }
