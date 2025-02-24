package com.main.app.repository.products;

import com.main.app.model.products.GPU_Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GPU_Product_Repository extends JpaRepository<GPU_Product , Integer> {
}
