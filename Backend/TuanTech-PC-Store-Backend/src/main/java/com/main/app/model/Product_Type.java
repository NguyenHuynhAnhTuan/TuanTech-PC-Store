package com.main.app.model;

import com.main.app.model.products.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "product_type_tbl")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product_Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer product_type_id;
    private String product_type_name;

    @ManyToOne
    @JoinColumn(name = "product_category_id" , nullable = false)
    private Product_Category product_category;

    @OneToMany(mappedBy = "product_type" , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Product_Group> product_group_list;

    @OneToMany(mappedBy = "product_type" , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Product> products_by_type_list;
}
