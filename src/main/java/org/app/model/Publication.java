package org.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor // Lombok generates a no-argument constructor
@AllArgsConstructor // Lombok generates an all-arguments constructor
public class Publication {

    private Long id;
    private String title;
    private String description;
    private double monthlyPrice;
    private boolean active;
}
