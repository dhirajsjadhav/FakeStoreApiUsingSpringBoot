package com.example.practice.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.practice.dtos.ProductResponseDto;
import com.example.practice.models.Product;
import com.example.practice.services.FakeStoreProductService;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
public class ProductController {
    FakeStoreProductService fakeStoreProductService;
    ProductController(FakeStoreProductService fakeStoreProductService){
        this.fakeStoreProductService = fakeStoreProductService;
    }

    //@RequestMapping(value="/products/{id}", method=RequestMethod.GET) this annotation is deprecated now instead use below
    @GetMapping(path = "/product/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable long id){ //@PathVariable maps whatever written in {} in @GetMapping annotation
        Product p = fakeStoreProductService.getProductById(id);
        return new ResponseEntity<ProductResponseDto>(ProductResponseDto.from(p),HttpStatus.PAYMENT_REQUIRED);
        
        
        /* 
        ProductResponseDto dummyProductResponseDto = new ProductResponseDto();
        dummyProductResponseDto.setId(id);
        dummyProductResponseDto.setName("Product"+id);
        dummyProductResponseDto.setDescription("Product Description");
        dummyProductResponseDto.setPrice(12.34);
        dummyProductResponseDto.setImageUrl("https://some.dummy.image");
        return dummyProductResponseDto;
        //object is returned but actually JSON is sent as response.
        //Jackson library does this serialization
        */
    }
}
