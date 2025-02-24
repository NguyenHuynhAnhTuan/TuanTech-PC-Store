package com.main.app.repository;

import com.main.app.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Product_Type_Repository extends JpaRepository<ProductType , Integer> {
}
