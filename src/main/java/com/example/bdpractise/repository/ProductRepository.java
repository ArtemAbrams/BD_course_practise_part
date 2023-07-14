package com.example.bdpractise.repository;

import com.example.bdpractise.entityRelation.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
