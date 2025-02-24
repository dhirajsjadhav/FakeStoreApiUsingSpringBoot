package com.example.practice.dtos;

import com.example.practice.models.Category;
import com.example.practice.models.Product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;

    public Product toProduct() {
        Product product = new Product();
        
        product.setId(this.id);
        product.setName(this.title);
        product.setPrice(this.price);
        
        Category category = new Category();
        category.setName(this.category);
        product.setCategory(category);
        
        product.setDescription(this.description);
        product.setImageUrl(this.image);
        
        return product;
    }
}