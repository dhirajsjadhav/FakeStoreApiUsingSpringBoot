package com.example.practice.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.practice.dtos.CreateProductRequestDto;
import com.example.practice.dtos.ProductResponseDto;
import com.example.practice.models.Product;
import com.example.practice.services.ProductService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
public class ProductController {
    ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    // @RequestMapping(value="/products/{id}", method=RequestMethod.GET) this
    // annotation is deprecated now instead use below
    @GetMapping(path = "/products/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable long id) { // @PathVariable maps whatever
                                                                                      // written in {} in @GetMapping
                                                                                      // annotation
        Product p = productService.getProductById(id);
        return new ResponseEntity<ProductResponseDto>(ProductResponseDto.from(p), HttpStatus.PAYMENT_REQUIRED);

        /*
         * ProductResponseDto dummyProductResponseDto = new ProductResponseDto();
         * dummyProductResponseDto.setId(id);
         * dummyProductResponseDto.setName("Product"+id);
         * dummyProductResponseDto.setDescription("Product Description");
         * dummyProductResponseDto.setPrice(12.34);
         * dummyProductResponseDto.setImageUrl("https://some.dummy.image");
         * return dummyProductResponseDto;
         * //object is returned but actually JSON is sent as response.
         * //Jackson library does this serialization
         */
    }

    @GetMapping("/products")
    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        if (products != null) {
            for (Product p : products) {
                productResponseDtos.add(ProductResponseDto.from(p));
            }
        }
        return productResponseDtos;
    }
    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody CreateProductRequestDto createProductRequestDto){
        Product product = productService.createProduct(createProductRequestDto.getTitle(),
                                                    createProductRequestDto.getPrice(), 
                                                    createProductRequestDto.getCategory(),
                                                    createProductRequestDto.getDescription(), 
                                                    createProductRequestDto.getImage());
        
        return product!=null?ProductResponseDto.from(product):null;
    }

}
