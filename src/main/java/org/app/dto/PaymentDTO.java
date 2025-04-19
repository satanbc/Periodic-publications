package org.app.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDTO {

    private Long id;
    private Long subscriptionId;
    private Double amount;
    private LocalDate date;
}
