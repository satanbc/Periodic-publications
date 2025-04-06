package org.app.mapper;

import org.app.dto.PaymentDTO;
import org.app.model.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PaymentMapper {
    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    PaymentDTO toDTO(Payment payment);
    Payment toEntity(PaymentDTO dto);

    List<PaymentDTO> toDTOList(List<Payment> payments);
    List<Payment> toEntityList(List<PaymentDTO> dtos);
}
