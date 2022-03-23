package com.emlakburada.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emlakburada.user.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
