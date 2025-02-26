package com.main.app.model;

import com.main.app.model.products.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "product_group_tbl")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product_Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer product_group_id;
    private String product_group_name;

    @ManyToOne
    @JoinColumn(name = "product_type_id" , nullable = false)
    private Product_Type product_type;

    @OneToMany(mappedBy = "product_group" , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Product> products_by_group_list;
}
