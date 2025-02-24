package com.main.app.repository.products;

import com.main.app.model.products.Mainboard_Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Mainboard_Product_Repository extends JpaRepository<Mainboard_Product , Integer> {
}
