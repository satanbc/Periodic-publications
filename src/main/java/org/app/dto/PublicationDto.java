package org.app.dto;

import java.math.BigDecimal;

public class PublicationDto {

    private String title;
    private String description;
    private BigDecimal price;
    private String frequency;

    public PublicationDto() {
    }

    public PublicationDto(String title, String description, BigDecimal price, String frequency) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.frequency = frequency;
    }

    // Геттери і сеттери
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "PublicationDto{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", frequency='" + frequency + '\'' +
                '}';
    }
}
