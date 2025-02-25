package com.main.app.service.products;

import com.main.app.dto.products.GPU_Product_Dto;
import com.main.app.errorhandler.ResourceAlreadyExistException;
import com.main.app.errorhandler.ResourceNotFoundException;
import com.main.app.mapper.products.GPU_Product_Mapper;
import com.main.app.model.ProductCategory;
import com.main.app.model.ProductGroup;
import com.main.app.model.ProductType;
import com.main.app.model.products.GPU_Product;
import com.main.app.model.products.Product;
import com.main.app.repository.Product_Category_Repository;
import com.main.app.repository.Product_Group_Repository;
import com.main.app.repository.Product_Type_Repository;
import com.main.app.repository.products.GPU_Product_Repository;
import com.main.app.repository.products.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GPU_Product_Service {
    private final ProductRepository productRepository;
    private final GPU_Product_Repository gpuProductRepository;
    private final GPU_Product_Mapper gpuProductMapper;

    private final Product_Category_Repository productCategoryRepository;
    private final Product_Type_Repository productTypeRepository;
    private final Product_Group_Repository productGroupRepository;

    @Autowired
    public GPU_Product_Service(ProductRepository productRepository, GPU_Product_Repository gpuProductRepository, GPU_Product_Mapper gpuProductMapper, Product_Category_Repository productCategoryRepository, Product_Type_Repository productTypeRepository, Product_Group_Repository productGroupRepository) {
        this.productRepository = productRepository;
        this.gpuProductRepository = gpuProductRepository;
        this.gpuProductMapper = gpuProductMapper;
        this.productCategoryRepository = productCategoryRepository;
        this.productTypeRepository = productTypeRepository;
        this.productGroupRepository = productGroupRepository;
    }

    private GPU_Product_Dto mapToDto(GPU_Product entity) {
        GPU_Product_Dto dto = gpuProductMapper.toDto(entity);
        dto.setProduct_category_id(entity.getProduct_category().getProduct_category_id());
        dto.setProduct_category_name(entity.getProduct_category().getProduct_category_name());
        dto.setProduct_type_id(entity.getProduct_type().getProduct_type_id());
        dto.setProduct_type_name(entity.getProduct_type().getProduct_type_name());
        dto.setProduct_group_id(entity.getProduct_group().getProduct_group_id());
        dto.setProduct_group_name(entity.getProduct_group().getProduct_group_name());
        return dto;
    }

    private GPU_Product mapToEntity(GPU_Product_Dto dto) {
        ProductCategory pCategoryInfo = productCategoryRepository.findById(dto.getProduct_category_id()).orElseThrow(() -> new ResourceNotFoundException("Product Category Not Found!!!"));
        ProductType pTypeInfo = productTypeRepository.findById(dto.getProduct_type_id()).orElseThrow(() -> new ResourceNotFoundException("Product Type Not Found!!!"));
        ProductGroup pGroupInfo = productGroupRepository.findById(dto.getProduct_group_id()).orElseThrow(() -> new ResourceNotFoundException("Product Group Not Found!!!"));

        GPU_Product entity = gpuProductMapper.toEntity(dto);
        entity.setProduct_category(pCategoryInfo);
        entity.setProduct_type(pTypeInfo);
        entity.setProduct_group(pGroupInfo);

        return entity;
    }


    //Get Mapping
    public List<GPU_Product_Dto> service_getAll_GPU_Product() {
        return gpuProductRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    public GPU_Product_Dto service_detail_GPU_Product(int id) {
        return gpuProductRepository.findById(id)
                .map(this::mapToDto)
                .orElseThrow(() -> new ResourceNotFoundException("GPU By Id: " + id + " Not Found!!!"));

    }

    //Post Mapping
    public GPU_Product_Dto service_create_GPU_Product(GPU_Product_Dto newDto) {
        if(productRepository.existsByProductName(newDto.getProduct_name())) {
            throw new ResourceAlreadyExistException("This GPU Name Already Exist!!!");
        }

        if(productRepository.existsByProductModel(newDto.getProduct_model())) {
            throw new ResourceAlreadyExistException("This Model Already Exist!!!");
        }

        GPU_Product newEntity = mapToEntity(newDto);
        newEntity = gpuProductRepository.save(newEntity);
        return mapToDto(newEntity);
    }

    //Put Mapping
    public GPU_Product_Dto service_update_GPU_Product(GPU_Product_Dto updateDto) {
        GPU_Product gpuProductInfo = gpuProductRepository.findById(updateDto.getProduct_id())
                .orElseThrow(() -> new ResourceNotFoundException("GPU By Id: " + updateDto.getProduct_id() + " Not Found!!!"));


        if(productRepository.existsByProductName(updateDto.getProduct_name()) && !gpuProductInfo.getProduct_name().equals(updateDto.getProduct_name())) {
            throw new ResourceAlreadyExistException("This GPU Name Already Exist!!!");
        }

        if(productRepository.existsByProductModel(updateDto.getProduct_model()) && !gpuProductInfo.getProduct_model().equals(updateDto.getProduct_model())) {
            throw new ResourceAlreadyExistException("This GPU Model Already Exist!!!");
        }

        gpuProductInfo = mapToEntity(updateDto);
        gpuProductInfo = gpuProductRepository.save(gpuProductInfo);
        return mapToDto(gpuProductInfo);
    }


}
