package org.app.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    private Long id;
    private Long subscriptionId;
    private double amount;
    private LocalDateTime paymentDate;
    private String status;
}
