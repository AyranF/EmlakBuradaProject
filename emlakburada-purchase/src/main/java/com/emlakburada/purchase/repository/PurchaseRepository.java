package com.emlakburada.purchase.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emlakburada.purchase.model.Product;

public interface PurchaseRepository extends JpaRepository<Product, Integer>{

}
