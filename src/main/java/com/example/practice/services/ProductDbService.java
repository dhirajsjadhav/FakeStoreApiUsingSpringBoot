package com.example.practice.services;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.practice.exceptions.ProductNotFoundException;
import com.example.practice.models.Product;

@Service("productDbService")
/*
We are using @Primary annotation to tell Spring that this is the primary implementation of ProductService interface.
If there are multiple implementations of ProductService interface, then the one with @Primary annotation will be used for autowiring. 
@Primary

*/
public class ProductDbService implements ProductService{

    @Override
    public Product getProductById(long productId) throws ProductNotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProductById'");
    }

    @Override
    public List<Product> getAllProducts() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllProducts'");
    }

    @Override
    public Product createProduct(String title, double price, String category, String description, String image) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createProduct'");
    }

    @Override
    public String updateProduct(long productId, String title, Double price, String category, String description,
            String image) throws ProductNotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateProduct'");
    }

    @Override
    public String deleteProduct(long productId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteProduct'");
    }

}
