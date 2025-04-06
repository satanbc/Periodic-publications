package org.app.service;

import org.app.dao.PublicationDAO;
import org.app.dto.PublicationDTO;
import org.app.mapper.PublicationMapper;
import org.app.model.Publication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class PublicationService {
    private static final Logger logger = LogManager.getLogger(PublicationService.class);
    private final PublicationDAO publicationDAO = new PublicationDAO();
    private final PublicationMapper mapper = PublicationMapper.INSTANCE;

    public List<PublicationDTO> getAllActivePublications() {
        List<PublicationDTO> publications = mapper.toDTOList(publicationDAO.findAll());
        logger.info("Fetched {} active publications", publications.size());
        return publications;
    }

    public void addPublication(PublicationDTO dto) {
        if (dto.getMonthlyPrice() <= 0 || dto.getTitle() == null || dto.getTitle().isBlank()) {
            throw new IllegalArgumentException("Invalid publication data");
        }

        Publication publication = mapper.toEntity(dto);
        publication.setActive(true);
        publicationDAO.save(publication);
        logger.info("Added new publication: {}", publication);
    }
}
