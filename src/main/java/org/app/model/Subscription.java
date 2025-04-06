package org.app.model;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {
    private Long id;
    private Long userId;
    private Long publicationId;
    private int months;
    private LocalDate startDate;
    private LocalDate endDate;
}
