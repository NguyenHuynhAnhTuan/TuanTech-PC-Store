package com.main.app.repository;

import com.main.app.model.Product_Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Product_Category_Repository extends JpaRepository<Product_Category , Integer> {
}
