package com.main.app.service.products;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.app.dto.products.Product_Dto;
import com.main.app.errorhandler.ResourceAlreadyExistException;
import com.main.app.errorhandler.ResourceNotFoundException;
import com.main.app.mapper.products.Product_Mapper;
import com.main.app.model.Product_Category;
import com.main.app.model.Product_Group;
import com.main.app.model.Product_Type;
import com.main.app.model.products.Product;
import com.main.app.model.products.Product_Detail;
import com.main.app.repository.Product_Category_Repository;
import com.main.app.repository.Product_Group_Repository;
import com.main.app.repository.Product_Type_Repository;
import com.main.app.repository.products.Product_Detail_Repository;
import com.main.app.repository.products.Product_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Product_Service {
    private final Product_Repository productRepository;
    private final Product_Detail_Repository productDetailRepository;
    private final Product_Category_Repository productCategoryRepository;
    private final Product_Type_Repository productTypeRepository;
    private final Product_Group_Repository productGroupRepository;
    private final Product_Mapper productMapper;

    @Autowired
    public Product_Service(Product_Repository productRepository, Product_Detail_Repository productDetailRepository, Product_Category_Repository productCategoryRepository, Product_Type_Repository productTypeRepository, Product_Group_Repository productGroupRepository, Product_Mapper productMapper) {
        this.productRepository = productRepository;
        this.productDetailRepository = productDetailRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.productTypeRepository = productTypeRepository;
        this.productGroupRepository = productGroupRepository;
        this.productMapper = productMapper;
    }

    private Product mappingToEntity(Product_Dto dto) {
        Product_Category pCateInfo = productCategoryRepository.findById(dto.getProduct_category_id())
                .orElseThrow(() -> new ResourceNotFoundException("Product Category By Id Not Found!!!"));
        Product_Type pTypeInfo = productTypeRepository.findById(dto.getProduct_type_id())
                .orElseThrow(() -> new ResourceNotFoundException("Product Type By Id Not Found!!!"));
        Product_Group pGroupInfo = productGroupRepository.findById(dto.getProduct_group_id())
                .orElseThrow(() -> new ResourceNotFoundException("Product Group By Id Not Found!!!"));

        Product entity = productMapper.toEntity(dto);
        entity.setProduct_category(pCateInfo);
        entity.setProduct_type(pTypeInfo);
        entity.setProduct_group(pGroupInfo);

        return entity;
    }

    private Product_Dto mappingToDto(Product entity) {
        Product_Dto dto = productMapper.toDto(entity);
        dto.setProduct_detail_id(entity.getProduct_detail().getProduct_detail_id());
        dto.setSpecifications(entity.getProduct_detail().getSpecifications());
        dto.setProduct_category_id(entity.getProduct_category().getProduct_category_id());
        dto.setProduct_category_name(entity.getProduct_category().getProduct_category_name());
        dto.setProduct_type_id(entity.getProduct_type().getProduct_type_id());
        dto.setProduct_type_name(entity.getProduct_type().getProduct_type_name());
        dto.setProduct_group_id(entity.getProduct_group().getProduct_group_id());
        dto.setProduct_group_name(entity.getProduct_group().getProduct_group_name());
        return dto;
    }

    private String myJsonStringify(String jsonString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Object jsonObject = objectMapper.readValue(jsonString , Object.class);
        String compactJsonString = objectMapper.writeValueAsString(jsonObject);
        System.out.println(compactJsonString);
        return compactJsonString;
    }

    public List<Product_Dto> service_all_product() {
        return productRepository.findAll()
                .stream()
                .map(this::mappingToDto)
                .toList();
    }

    public Product_Dto service_detail_product(int id) {
        return productRepository.findById(id)
                .map(this::mappingToDto)
                .orElseThrow(() -> new ResourceNotFoundException("Product By Id: " + id + " Not Found!!!"));
    }

    public Product_Dto service_create_product(Product_Dto newDto) throws JsonProcessingException {
        if(productRepository.existsByProductName(newDto.getProduct_name())) {
            throw new ResourceAlreadyExistException("This Product Name Already Exist!!!");
        }

        if(productRepository.existsByProductModel(newDto.getProduct_model())) {
            throw new ResourceAlreadyExistException("This Product Model Already Exist!!!");
        }

        Product_Detail newProductDetail = new Product_Detail();
        newProductDetail.setSpecifications(myJsonStringify(newDto.getSpecifications()));
        Product newEntity = mappingToEntity(newDto);
        newEntity.setProduct_detail(newProductDetail);
        newProductDetail.setProduct(newEntity);

        return mappingToDto(productRepository.save(newEntity));
    }


}
