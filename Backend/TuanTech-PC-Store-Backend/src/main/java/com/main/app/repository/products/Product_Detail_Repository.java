package com.main.app.repository.products;

import com.main.app.model.products.Product_Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Product_Detail_Repository extends JpaRepository<Product_Detail , Integer> {
}
