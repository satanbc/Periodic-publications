package org.app.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionDTO {

    private Long id;
    private Long userId;
    private Long publicationId;
    private int months;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean active;
}
