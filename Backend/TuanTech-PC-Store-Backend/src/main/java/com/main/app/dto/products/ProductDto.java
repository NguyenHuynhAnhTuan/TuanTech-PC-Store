package com.main.app.dto.products;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class ProductDto {
    private Integer product_id;

    private String product_name;
    private String product_brand;
    private String product_model;
    private Double product_price;
    private Integer product_guaranty;
    private Integer product_release_year;

    //Don't need to map these field
    private Integer product_category_id;
    private String product_category_name;

    private Integer product_type_id;
    private String product_type_name;

    private Integer product_group_id;
    private String product_group_name;
}
