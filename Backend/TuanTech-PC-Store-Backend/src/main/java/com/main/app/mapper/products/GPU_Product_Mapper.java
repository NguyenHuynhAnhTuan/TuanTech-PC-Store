package com.main.app.mapper.products;

import com.main.app.dto.products.GPU_Product_Dto;
import com.main.app.model.products.GPU_Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface GPU_Product_Mapper {
    GPU_Product_Mapper INSTANCE = Mappers.getMapper(GPU_Product_Mapper.class);

    @Mapping(target = "product_category_id" , ignore = true)
    @Mapping(target = "product_category_name" , ignore = true)
    @Mapping(target = "product_type_id" , ignore = true)
    @Mapping(target = "product_type_name" , ignore = true)
    @Mapping(target = "product_group_id" , ignore = true)
    @Mapping(target = "product_group_name" , ignore = true)
    GPU_Product_Dto toDto(GPU_Product gpuProduct);

    @Mapping(target = "product_category" , ignore = true)
    @Mapping(target = "product_type" , ignore = true)
    @Mapping(target = "product_group" , ignore = true)
    GPU_Product toEntity(GPU_Product_Dto gpuProductDto);
}
