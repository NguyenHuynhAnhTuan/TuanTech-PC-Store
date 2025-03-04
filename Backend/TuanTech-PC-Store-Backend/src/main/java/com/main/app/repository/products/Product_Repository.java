package com.main.app.repository.products;

import com.main.app.model.products.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Product_Repository extends JpaRepository<Product , Integer> {

    @Query("SELECT COUNT(p) > 0 FROM Product p WHERE p.product_name = :productName")
    boolean existsByProductName(@Param("productName") String productName);

    @Query("SELECT COUNT(p) > 0 FROM Product p WHERE p.product_model = :productModel")
    boolean existsByProductModel(@Param("productModel") String productModel);

    @Query("SELECT COUNT(p) > 0 FROM Product p WHERE p.product_name = :productName AND p.product_model = :productModel")
    boolean existsByProductNameAndProductModel(@Param("productName") String productName,
                                               @Param("productModel") String productModel);

    @Query("SELECT p FROM Product p WHERE p.product_category.product_category_name = :productCategory")
    List<Product> findByProductCategory(@Param("productCategory") String productCategory);

    @Query("SELECT p FROM Product p WHERE p.product_type.product_type_name = :productType")
    List<Product> findByProductType(@Param("productType") String productType);

    @Query("SELECT p FROM Product p WHERE p.product_group.product_group_name = :productGroup")
    List<Product> findByProductGroup(@Param("productGroup") String productGroup);
}
