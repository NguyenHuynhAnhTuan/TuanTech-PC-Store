package com.main.app.repository.products;

import com.main.app.model.products.CPU_Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CPU_Product_Repository extends JpaRepository<CPU_Product , Integer> {

}
