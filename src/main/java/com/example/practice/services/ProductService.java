package com.example.practice.services;

import java.util.List;

import com.example.practice.exceptions.ProductNotFoundException;
import com.example.practice.models.Product;

public interface ProductService {
    Product getProductById(long productId) throws ProductNotFoundException;
    List<Product> getAllProducts();
    Product createProduct(String title, double price, String category, String description, String image);
}
