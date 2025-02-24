package com.main.app.service.products;

import com.main.app.dto.products.CPU_Product_Dto;
import com.main.app.errorhandler.ResourceAlreadyExistException;
import com.main.app.errorhandler.ResourceNotFoundException;
import com.main.app.mapper.products.CPU_Product_Mapper;
import com.main.app.model.ProductCategory;
import com.main.app.model.ProductGroup;
import com.main.app.model.ProductType;
import com.main.app.model.products.CPU_Product;
import com.main.app.repository.Product_Category_Repository;
import com.main.app.repository.Product_Group_Repository;
import com.main.app.repository.Product_Type_Repository;
import com.main.app.repository.products.CPU_Product_Repository;
import com.main.app.repository.products.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CPU_Product_Service {
    private final ProductRepository productRepository;
    private final CPU_Product_Repository cpuProductRepository;
    private final CPU_Product_Mapper cpuProductMapper;

    private final Product_Category_Repository productCategoryRepository;
    private final Product_Type_Repository productTypeRepository;
    private final Product_Group_Repository productGroupRepository;

    @Autowired
    public CPU_Product_Service(ProductRepository productRepository, CPU_Product_Repository cpuProductRepository,
                               CPU_Product_Mapper cpuProductMapper,
                               Product_Category_Repository productCategoryRepository,
                               Product_Type_Repository productTypeRepository,
                               Product_Group_Repository productGroupRepository) {
        this.productRepository = productRepository;
        this.cpuProductRepository = cpuProductRepository;
        this.cpuProductMapper = cpuProductMapper;
        this.productCategoryRepository = productCategoryRepository;
        this.productTypeRepository = productTypeRepository;
        this.productGroupRepository = productGroupRepository;
    }

    //Mapping Process
    private CPU_Product_Dto mapToDto(CPU_Product entity) {
        CPU_Product_Dto dto = cpuProductMapper.toDto(entity);
        dto.setProduct_category_id(entity.getProduct_category().getProduct_category_id());
        dto.setProduct_category_name(entity.getProduct_category().getProduct_category_name());
        dto.setProduct_type_id(entity.getProduct_type().getProduct_type_id());
        dto.setProduct_type_name(entity.getProduct_type().getProduct_type_name());
        dto.setProduct_group_id(entity.getProduct_group().getProduct_group_id());
        dto.setProduct_group_name(entity.getProduct_group().getProduct_group_name());
        return dto;
    }

    private CPU_Product mapToEntity(CPU_Product_Dto dto) {
        ProductCategory pCategoryInfo = productCategoryRepository.findById(dto.getProduct_category_id()).orElseThrow(() -> new ResourceNotFoundException("Product Category Not Found!!!"));
        ProductType pTypeInfo = productTypeRepository.findById(dto.getProduct_type_id()).orElseThrow(() -> new ResourceNotFoundException("Product Type Not Found!!!"));
        ProductGroup pGroupInfo = productGroupRepository.findById(dto.getProduct_group_id()).orElseThrow(() -> new ResourceNotFoundException("Product Group Not Found!!!"));

        CPU_Product entity = cpuProductMapper.toEntity(dto);
        entity.setProduct_category(pCategoryInfo);
        entity.setProduct_type(pTypeInfo);
        entity.setProduct_group(pGroupInfo);

        return entity;
    }

    //Get Mapping Request
    public List<CPU_Product_Dto> service_getAll_CPU_Product() {
        return cpuProductRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    public List<CPU_Product_Dto> service_getAll_CPU_Product_by_Group(String byGroup) {
        return cpuProductRepository.findAll()
                .stream()
                .filter(item -> item.getProduct_group().getProduct_group_name().toLowerCase().trim().equals(byGroup.toLowerCase().trim()))
                .map(this::mapToDto)
                .toList();
    }

    public List<CPU_Product_Dto> service_getAll_CPU_Product_by_Model(String byModel) {
        return cpuProductRepository.findAll()
                .stream()
                .filter(item -> item.getProduct_model().toLowerCase().trim().contains(byModel.toLowerCase().trim()))
                .map(this::mapToDto)
                .toList();
    }

    public List<CPU_Product_Dto> service_getAll_CPU_Product_by_PriceRange(Double fromPrice , Double toPrice) {
        return cpuProductRepository.findAll()
                .stream()
                .filter(item -> item.getProduct_price() >= fromPrice && item.getProduct_price() <= toPrice)
                .map(this::mapToDto)
                .toList();
    }

    public CPU_Product_Dto service_detail_CPU_Product(int id) {
        return cpuProductRepository.findById(id)
                .map(this::mapToDto)
                .orElseThrow(() -> new ResourceNotFoundException("CPU by Id: " + id + " Not Found!!!"));
    }

    //Post Mapping Request
    public CPU_Product_Dto service_create_CPU_Product(CPU_Product_Dto newDto) {
        if(productRepository.existsByProductName(newDto.getProduct_name())) {
            throw new ResourceAlreadyExistException("This CPU Name Already Exist!!!");
        }

        System.out.println(newDto);

        CPU_Product newCPUProduct = mapToEntity(newDto);

        System.out.println(newCPUProduct);

        newCPUProduct = cpuProductRepository.save(newCPUProduct);
        return mapToDto(newCPUProduct);
    }

    //Put Mapping Request
    public CPU_Product_Dto service_update_CPU_Product(CPU_Product_Dto updateDto) {
        CPU_Product cpuProductInfo = cpuProductRepository.findById(updateDto.getProduct_id())
                .orElseThrow(() -> new ResourceNotFoundException("CPU by Id: " + updateDto.getProduct_id() + " Not Found!!!"));

        if(productRepository.existsByProductName(updateDto.getProduct_name()) && !cpuProductInfo.getProduct_name().equals(updateDto.getProduct_name())) {
            throw new ResourceAlreadyExistException("This CPU Name Already Exist!!!");
        }

        if(productRepository.existsByProductModel(updateDto.getProduct_model()) && !cpuProductInfo.getProduct_model().equals(updateDto.getProduct_model())) {
            throw new ResourceAlreadyExistException("This CPU Model Already Exist!!!");
        }

        CPU_Product updateCPUProduct = mapToEntity(updateDto);

        updateCPUProduct = cpuProductRepository.save(updateCPUProduct);
        return mapToDto(updateCPUProduct);
    }

    //Delete Mapping Request
}
