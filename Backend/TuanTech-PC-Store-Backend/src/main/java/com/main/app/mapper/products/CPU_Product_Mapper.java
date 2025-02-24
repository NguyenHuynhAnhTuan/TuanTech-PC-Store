package com.main.app.mapper.products;

import com.main.app.dto.products.CPU_Product_Dto;
import com.main.app.model.products.CPU_Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CPU_Product_Mapper {
    CPU_Product_Mapper INSTANCE = Mappers.getMapper(CPU_Product_Mapper.class);

    @Mapping(target = "product_category_id" , ignore = true)
    @Mapping(target = "product_category_name" , ignore = true)
    @Mapping(target = "product_type_id" , ignore = true)
    @Mapping(target = "product_type_name" , ignore = true)
    @Mapping(target = "product_group_id" , ignore = true)
    @Mapping(target = "product_group_name" , ignore = true)
    CPU_Product_Dto toDto(CPU_Product cpuProduct);

    @Mapping(target = "product_category" , ignore = true)
    @Mapping(target = "product_type" , ignore = true)
    @Mapping(target = "product_group" , ignore = true)
    @Mapping(target = "pcle_support" , source = "pcle_support")
    @Mapping(target = "igpu" , source = "igpu")
    CPU_Product toEntity(CPU_Product_Dto cpuProductDto);
}
