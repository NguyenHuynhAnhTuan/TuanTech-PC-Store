package com.main.app.repository;

import com.main.app.model.ProductGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Product_Group_Repository extends JpaRepository<ProductGroup , Integer> {
}
