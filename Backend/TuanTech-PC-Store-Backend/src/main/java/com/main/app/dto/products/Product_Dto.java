package com.main.app.dto.products;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product_Dto {
    private Integer product_id;
    private String product_name;
    private String product_brand;
    private String product_model;
    private Double product_price;
    private Integer product_guaranty;
    private Integer product_release_year;
    private Integer stock_inventory;

    //Don't need to map these field
    private Integer product_detail_id;
    private String specifications;

    private Integer product_category_id;
    private String product_category_name;

    private Integer product_type_id;
    private String product_type_name;

    private Integer product_group_id;
    private String product_group_name;
}
