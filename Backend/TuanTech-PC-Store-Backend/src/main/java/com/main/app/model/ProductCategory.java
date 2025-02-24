package com.main.app.model;

import com.main.app.model.products.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "product_category_tbl")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer product_category_id;
    private String product_category_name;

    @OneToMany(mappedBy = "product_category" , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<ProductType> product_type_list;

    @OneToMany(mappedBy = "product_category" , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Product> products_by_category_list;
}
