package com.example.practice.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.practice.models.Product;

//JPARepository is an interface that provides methods for performing CRUD operations on the entity.
//It provides methods like save, findById, findAll, deleteById, etc.
//It is a generic interface that takes two parameters, the entity type and the type of the primary key of the entity.
//In this case, the entity type is Product and the primary key type is Long.
//The ProductRepository interface extends JpaRepository and provides methods for performing CRUD operations on the Product entity.
public interface ProductReporsitory extends JpaRepository<Product, Long> {
    Product save(Product product);

    List<Product> findAll();

    Optional<Product> findById(long id);

    List<Product> findByCategory_Name(String category);

    @Query("select p from Product p where p.category.name=:categoryName")
    List<Product> getProductsByCategoryName(@Param("categoryName") String categoryName);
    
    @Query(value="select * from product where cateogry_id in (select id from category where name=:categoryName)", nativeQuery = true)
    List<Product> getProductsByCategoryNameNative(@Param("categoryName") String categoryName);
}
