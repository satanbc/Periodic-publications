package org.app.model;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    private Long id;
    private Long subscriptionId;
    private Double amount;
    private LocalDate date;
}
