package org.app.mapper;

import org.app.dto.SubscriptionDTO;
import org.app.model.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubscriptionMapper {
    SubscriptionMapper INSTANCE = Mappers.getMapper(SubscriptionMapper.class);

    SubscriptionDTO toDTO(Subscription subscription);
    Subscription toEntity(SubscriptionDTO dto);
}
