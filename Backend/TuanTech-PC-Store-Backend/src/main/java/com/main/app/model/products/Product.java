package com.main.app.model.products;


import com.main.app.model.Product_Category;
import com.main.app.model.Product_Group;
import com.main.app.model.Product_Type;
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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer product_id;
    private String product_name;
    private String product_brand;
    private String product_model;
    private Double product_price;
    private Integer product_guaranty;
    private Integer product_release_year;
    private Integer stock_inventory;
    private String image_url;

    @OneToOne(mappedBy = "product" , cascade = CascadeType.ALL)
    private Product_Detail product_detail;

    @ManyToOne
    @JoinColumn(name = "product_category_id")
    private Product_Category product_category;

    @ManyToOne
    @JoinColumn(name = "product_type_id")
    private Product_Type product_type;

    @ManyToOne
    @JoinColumn(name = "product_group_id")
    private Product_Group product_group;
}
