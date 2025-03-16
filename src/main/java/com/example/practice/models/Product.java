package com.example.practice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
    private double price;
    private String description;
    private String imageUrl;
    @ManyToOne
    private Category category;
}
