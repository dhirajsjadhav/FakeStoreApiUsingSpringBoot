package com.example.practice.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.practice.dtos.FakeStoreProductDto;
import com.example.practice.dtos.FakeStoreProductRequestDto;
import com.example.practice.exceptions.ProductNotFoundException;
import com.example.practice.models.Product;

@Service
public class FakeStoreProductService implements ProductService {

    RestTemplate restTemplate;

    FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(long productId) throws ProductNotFoundException {

        FakeStoreProductDto fakeStoreProductDto = restTemplate
                .getForObject("https://fakestoreapi.com/products/" + productId, FakeStoreProductDto.class);

        if (fakeStoreProductDto == null)
            throw new ProductNotFoundException("Product with id " + productId + " not found");

        return fakeStoreProductDto.toProduct();

    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] list = restTemplate.getForObject("https://fakestoreapi.com/products",
                FakeStoreProductDto[].class);
        // in above line we can not use List.class as it is not a concrete class. ALso
        // we can not use List<>.class as generics
        // are not available at runtime. So we use array of FakeStoreProductDto and then
        // convert it to List<Product>.

        List<Product> products = new ArrayList<>();

        for (FakeStoreProductDto fakeStoreProductDto : list) {
            products.add(fakeStoreProductDto.toProduct());
        }

        return products;

    }

    @Override
    public Product createProduct(String title, double price, String category, String description, String image) {
        FakeStoreProductRequestDto fakeStoreProductRequestDto = new FakeStoreProductRequestDto();
        fakeStoreProductRequestDto.setTitle(title);
        fakeStoreProductRequestDto.setPrice(price);
        fakeStoreProductRequestDto.setCategory(category);
        fakeStoreProductRequestDto.setDescription(description);
        fakeStoreProductRequestDto.setImage(image);

        FakeStoreProductDto fakeStoreProductDto = restTemplate.postForObject("https://fakestoreapi.com/products",
                fakeStoreProductRequestDto,
                FakeStoreProductDto.class);
        return fakeStoreProductDto.toProduct();
    }

}
