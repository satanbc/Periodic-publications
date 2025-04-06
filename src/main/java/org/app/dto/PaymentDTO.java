package org.app.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDTO {

    private Long id;
    private Long subscriptionId;
    private double amount;

    public PaymentDTO(Long subscriptionId, double amount) {
        this.subscriptionId = subscriptionId;
        this.amount = amount;
    }
}
