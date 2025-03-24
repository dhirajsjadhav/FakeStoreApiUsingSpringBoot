package com.example.practice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.practice.exceptions.ProductNotFoundException;
import com.example.practice.models.Category;
import com.example.practice.models.Product;
import com.example.practice.repositories.CategoryRepository;
import com.example.practice.repositories.ProductReporsitory;

@Service("productDbService")
/*
We are using @Primary annotation to tell Spring that this is the primary implementation of ProductService interface.
If there are multiple implementations of ProductService interface, then the one with @Primary annotation will be used for autowiring. 
@Primary

*/
public class ProductDbService implements ProductService{
    ProductReporsitory productRepository;
    CategoryRepository categoryRepository;
    public ProductDbService(ProductReporsitory productRepository, CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }
    @Override
    public Product getProductById(long productId) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(optionalProduct.isPresent()){
            return optionalProduct.get();
        }
        throw new ProductNotFoundException("Product not found with id: "+productId);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();}

    @Override
    public Product createProduct(String title, double price, String category, String description, String image) {
        // TODO Auto-generated method stub
        Product product = new Product();
        product.setName(title);
        product.setPrice(price);

        Category categoryObj = getCategoryFromDb(category);
        product.setCategory(categoryObj);
        
        product.setDescription(description);
        product.setImageUrl(image);
        return productRepository.save(product);

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

    private Category getCategoryFromDb(String name){
        Optional<Category> optionalCategory = categoryRepository.findByName(name);
        if(optionalCategory.isPresent()){
            return optionalCategory.get();
        }
        Category category = new Category();
        category.setName(name);
        return categoryRepository.save(category);
    }

}
