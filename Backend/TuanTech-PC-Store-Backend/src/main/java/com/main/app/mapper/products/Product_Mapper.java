package com.main.app.mapper.products;

import com.main.app.dto.products.Product_Dto;
import com.main.app.model.products.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface Product_Mapper {
    @Mapping(target = "product_detail_id" , ignore = true)
    @Mapping(target = "specifications" , ignore = true)
    @Mapping(target = "product_category_id" , ignore = true)
    @Mapping(target = "product_category_name" , ignore = true)
    @Mapping(target = "product_type_id" , ignore = true)
    @Mapping(target = "product_type_name" , ignore = true)
    @Mapping(target = "product_group_id" , ignore = true)
    @Mapping(target = "product_group_name" , ignore = true)
    Product_Dto toDto(Product entity);

    @Mapping(target = "product_detail" , ignore = true)
    @Mapping(target = "product_category" , ignore = true)
    @Mapping(target = "product_type" , ignore = true)
    @Mapping(target = "product_group" , ignore = true)
    Product toEntity(Product_Dto dto);
}
