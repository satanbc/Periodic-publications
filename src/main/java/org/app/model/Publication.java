package org.app.model;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Publication {

    private Long id;
    private String title;
    private String description;
    private double monthlyPrice;
    private boolean active;
}
