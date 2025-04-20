package org.app.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionDTO {

    private Long id;
    private String email;
    private Long publicationId;
    private int months;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean active;
    private BigDecimal totalPrice;
}
