package org.app.service;

import org.app.dao.PublicationDAO;
import org.app.dto.PublicationDTO;
import org.app.mapper.PublicationMapper;
import org.app.model.Publication;

import java.util.List;
import java.util.stream.Collectors;

public class PublicationService {
    private final PublicationDAO publicationDAO = new PublicationDAO();
    private final PublicationMapper mapper = PublicationMapper.INSTANCE;

    public List<PublicationDTO> getAllActivePublications() {
        return publicationDAO.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public void addPublication(PublicationDTO dto) {
        Publication publication = mapper.toEntity(dto);
        publication.setActive(true); // автоматично активна при створенні
        publicationDAO.save(publication);
    }
}
