package com.example.practice.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.practice.dtos.CreateProductRequestDto;
import com.example.practice.dtos.ProductResponseDto;
import com.example.practice.dtos.UpdateProductRequestDto;
import com.example.practice.exceptions.ProductNotFoundException;
import com.example.practice.models.Product;
import com.example.practice.services.ProductService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class ProductController {
    ProductService productService;

    ProductController(@Qualifier("productDbService") ProductService productService) {
        // @Qualifier is used to specify which implementation of ProductService to use.
        // If there are multiple implementations of ProductService interface, then the one with @Qualifier annotation will be used for autowiring.
        // condition is that each @Service annotation should have a unique name.
        this.productService = productService;
    }

    // @RequestMapping(value="/products/{id}", method=RequestMethod.GET) this
    // annotation is deprecated now instead use below
    @GetMapping(path = "/products/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable("id") long id) throws ProductNotFoundException { // @PathVariable maps whatever
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
        for (Product p : products) {
            productResponseDtos.add(ProductResponseDto.from(p));
        }

        return productResponseDtos;
    }

    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody CreateProductRequestDto createProductRequestDto) {
        Product product = productService.createProduct(
                createProductRequestDto.getTitle(),
                createProductRequestDto.getPrice(),
                createProductRequestDto.getCategory(),
                createProductRequestDto.getDescription(),
                createProductRequestDto.getImage());

        return ProductResponseDto.from(product);
    }
    @PutMapping("/products/{id}") 
    public String updateProduct(@PathVariable("id") long id, @RequestBody UpdateProductRequestDto updateProductRequestDto) throws ProductNotFoundException{
        String response=productService.updateProduct(id, updateProductRequestDto.getTitle(), 
            updateProductRequestDto.getPrice(),
            updateProductRequestDto.getCategory(), 
            updateProductRequestDto.getDescription(), 
            updateProductRequestDto.getImage());
        return response;
    }
    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable("id") long id) throws ProductNotFoundException{
        String response = productService.deleteProduct(id);
        if(response.equals("null")){
            throw new ProductNotFoundException("Product with id "+id+" not found");
        }
        return "Product deleted successfully";
    }

    // @ExceptionHandler(NullPointerException.class)
    // public ErrorDto handleNullPointerException() {
    //     ErrorDto errorDto = new ErrorDto();
    //     errorDto.setMessage("Null Pointer Exception Occured");
    //     errorDto.setStatus("Failure");
    //     return errorDto;
    // }

}
