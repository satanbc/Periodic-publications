package org.app.mapper;

import org.app.model.Publication;
import org.app.dto.PublicationDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PublicationMapper {

    PublicationMapper INSTANCE = Mappers.getMapper(PublicationMapper.class);

    PublicationDto toDto(Publication publication);

    Publication toEntity(PublicationDto publicationDto);
}
