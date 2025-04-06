package org.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor // Lombok generates a no-arg constructor
@AllArgsConstructor // Lombok generates an all-args constructor
public class PublicationDTO {

    private Long id;          // The ID field
    private String title;
    private String description;
    private double monthlyPrice;

    public PublicationDTO(String title, String description, double monthlyPrice) {
        this.title = title;
        this.description = description;
        this.monthlyPrice = monthlyPrice;
    }
}
