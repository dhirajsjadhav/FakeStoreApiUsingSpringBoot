package com.example.practice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductRequestDto {
    private String title;
    private Double price;
    private String description;
    private String image;
    private String category;

    @Override
    public String toString() {
        return "UpdateProductRequestDto [title=" + title + ", price=" + price + ", description=" + description + ", image=" + image + ", category=" + category + "]";
    }
}
