package com.example.practice.services;

import java.util.List;

import com.example.practice.models.Product;

public interface ProductService {
    Product getProductById(long productId);
    List<Product> getAllProducts();
    Product createProduct(String title, double price, String category, String description, String image);
}
