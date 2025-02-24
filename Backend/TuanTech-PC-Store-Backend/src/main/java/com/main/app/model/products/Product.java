package com.main.app.model.products;

import com.main.app.model.ProductCategory;
import com.main.app.model.ProductGroup;
import com.main.app.model.ProductType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_tbl")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer product_id;

    private String product_name;
    private String product_brand;
    private String product_model;
    private Double product_price;
    private Integer product_guaranty;

    private Integer product_release_year;

    @ManyToOne
    @JoinColumn(name = "product_category_id")
    private ProductCategory product_category;

    @ManyToOne
    @JoinColumn(name = "product_type_id")
    private ProductType product_type;

    @ManyToOne
    @JoinColumn(name = "product_group_id")
    private ProductGroup product_group;
}
