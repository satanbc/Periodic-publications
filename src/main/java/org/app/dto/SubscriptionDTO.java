package org.app.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionDTO {

    private Long id;
    private Long userId;
    private Long publicationId;
    private int months;

    public SubscriptionDTO(Long publicationId, int months) {
        this.publicationId = publicationId;
        this.months = months;
    }
}
