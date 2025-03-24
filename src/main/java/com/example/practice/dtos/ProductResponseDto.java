package com.example.practice.dtos;

import com.example.practice.models.Product;

import lombok.Getter;
import lombok.Setter;

/*we will use this dto mimicing Product Model 
so that the outiside world can't get the real implemenation

*/
@Getter
@Setter
public class ProductResponseDto {
    private long id;
    private String title;
    private double price;
    private String description;
    private String image;
    //in Product Model we used Category Class Object
    //but in this dto we do not want to expose internal details of how this model is created
    //so instead of Category, String is used
    private String category;


    public static ProductResponseDto from(Product product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setTitle(product.getName());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setDescription(product.getDescription());
        productResponseDto.setImage(product.getImageUrl());
        productResponseDto.setCategory(product.getCategory().getName());
        return productResponseDto;
    }
}
