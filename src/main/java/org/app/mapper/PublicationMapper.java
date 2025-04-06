package org.app.mapper;

import org.app.model.Publication;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.app.dto.PublicationDTO;

import java.util.List;

@Mapper
public interface PublicationMapper {
    PublicationMapper INSTANCE = Mappers.getMapper(PublicationMapper.class);

    PublicationDTO toDTO(Publication publication);
    Publication toEntity(PublicationDTO dto);

    List<PublicationDTO> toDTOList(List<Publication> publications);
    List<Publication> toEntityList(List<PublicationDTO> dtos);
}
