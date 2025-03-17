package com.example.practice.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Category extends BaseModel{
    private String description;
    @OneToMany(mappedBy = "category")
    private List<Product> products;
    // @OneToMany
    // private List<Product> featuredProducts;
}
