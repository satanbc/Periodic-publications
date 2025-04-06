package org.app.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublicationDTO {

    private Long id;
    private String title;
    private String description;
    private double monthlyPrice;

    public PublicationDTO(String title, String description, double monthlyPrice) {
        this.title = title;
        this.description = description;
        this.monthlyPrice = monthlyPrice;
    }
}
