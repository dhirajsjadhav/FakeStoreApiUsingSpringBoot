package com.example.practice.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.practice.models.Product;
//JPARepository is an interface that provides methods for performing CRUD operations on the entity.
//It provides methods like save, findById, findAll, deleteById, etc.
//It is a generic interface that takes two parameters, the entity type and the type of the primary key of the entity.
//In this case, the entity type is Product and the primary key type is Long.
//The ProductRepository interface extends JpaRepository and provides methods for performing CRUD operations on the Product entity.
public interface ProductReporsitory extends JpaRepository<Product, Long> {

}
