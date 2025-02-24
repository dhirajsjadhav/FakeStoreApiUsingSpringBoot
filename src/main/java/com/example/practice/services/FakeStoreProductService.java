package com.example.practice.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.practice.dtos.FakeStoreProductDto;
import com.example.practice.models.Product;
@Service
public class FakeStoreProductService implements ProductService {

    RestTemplate restTemplate;

    FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(long productId) {
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + productId, FakeStoreProductDto.class);
        return fakeStoreProductDto.toProduct();
    }

}
