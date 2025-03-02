package com.main.app.service.products;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

@Service
public class Product_Service {
    @Value("${file.upload-dir}")
    private String uploadDirectory;

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

    public void handleProductRelations(Product entity, Product_Dto dto) {
        //If entity not null, that mean we update a product
        if (entity.getProduct_category() != null && entity.getProduct_type() != null && entity.getProduct_group() != null) {
            if (!Objects.equals(dto.getProduct_category_id(), entity.getProduct_category().getProduct_category_id())) {
                Product_Category pCateInfo = productCategoryRepository.findById(dto.getProduct_category_id())
                        .orElseThrow(() -> new ResourceNotFoundException("Product Category By Id Not Found!!!"));
                entity.setProduct_category(pCateInfo);
            }

            if (!Objects.equals(dto.getProduct_type_id(), entity.getProduct_type().getProduct_type_id())) {
                Product_Type pTypeInfo = productTypeRepository.findById(dto.getProduct_type_id())
                        .orElseThrow(() -> new ResourceNotFoundException("Product Type By Id Not Found!!!"));
                entity.setProduct_type(pTypeInfo);
            }

            if (!Objects.equals(dto.getProduct_group_id(), entity.getProduct_group().getProduct_group_id())) {
                Product_Group pGroupInfo = productGroupRepository.findById(dto.getProduct_group_id())
                        .orElseThrow(() -> new ResourceNotFoundException("Product Group By Id Not Found!!!"));
                entity.setProduct_group(pGroupInfo);
            }
        }
        //Else that mean we create new product
        else {
            Product_Category pCateInfo = productCategoryRepository.findById(dto.getProduct_category_id())
                    .orElseThrow(() -> new ResourceNotFoundException("Product Category By Id Not Found!!!"));
            Product_Type pTypeInfo = productTypeRepository.findById(dto.getProduct_type_id())
                    .orElseThrow(() -> new ResourceNotFoundException("Product Type By Id Not Found!!!"));
            Product_Group pGroupInfo = productGroupRepository.findById(dto.getProduct_group_id())
                    .orElseThrow(() -> new ResourceNotFoundException("Product Group By Id Not Found!!!"));

            entity.setProduct_category(pCateInfo);
            entity.setProduct_type(pTypeInfo);
            entity.setProduct_group(pGroupInfo);
        }
    }

    private Product mappingToEntity(Product_Dto dto) {
        return productMapper.toEntity(dto);
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
        Object jsonObject = objectMapper.readValue(jsonString, Object.class);
        String compactJsonString = objectMapper.writeValueAsString(jsonObject);
        System.out.println(compactJsonString);
        return compactJsonString;
    }

    private Product_Dto mappingJsonToDto(String originProductString) throws JsonProcessingException {
        String compactJsonString = myJsonStringify(originProductString);
        ObjectMapper objMapper = new ObjectMapper();
        JsonNode jsonNode = objMapper.readTree(compactJsonString);

        Product_Dto dto = new Product_Dto();
        if (jsonNode.get("product_id") != null) {
            dto.setProduct_id(jsonNode.get("product_id").asInt());
        }
        dto.setProduct_name(jsonNode.get("product_name").asText());
        dto.setProduct_brand(jsonNode.get("product_brand").asText());
        dto.setProduct_model(jsonNode.get("product_model").asText());
        dto.setProduct_price(jsonNode.get("product_price").asDouble());
        dto.setProduct_guaranty(jsonNode.get("product_guaranty").asInt());
        dto.setProduct_release_year(jsonNode.get("product_release_year").asInt());
        dto.setStock_inventory(jsonNode.get("stock_inventory").asInt());
        dto.setSpecifications(jsonNode.get("specifications").asText());
        dto.setProduct_category_id(jsonNode.get("product_category_id").asInt());
        dto.setProduct_type_id(jsonNode.get("product_type_id").asInt());
        dto.setProduct_group_id(jsonNode.get("product_group_id").asInt());

        return dto;
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

    public List<Product_Dto> service_all_product_by_category(String product_category) {
        return productRepository.findByProductCategory(product_category)
                .stream()
                .map(this::mappingToDto)
                .toList();
    }

    public List<Product_Dto> service_all_product_by_type(String product_type) {
        return productRepository.findByProductType(product_type)
                .stream()
                .map(this::mappingToDto)
                .toList();
    }

    public List<Product_Dto> service_all_product_by_group(String product_group) {
        return productRepository.findByProductGroup(product_group)
                .stream()
                .map(this::mappingToDto)
                .toList();
    }

    public Resource service_image_resource(String category, String type, String group, String fileName) throws MalformedURLException {
        Path filePath = Paths.get(uploadDirectory)
                .resolve(category)
                .resolve(type)
                .resolve(group)
                .resolve(fileName);
        System.out.println(filePath);
        return new UrlResource(filePath.toUri());
    }

    public Product_Dto service_create_product(String newProductDtoByJsonString, MultipartFile newImageFile) throws IOException {
        Product_Dto newDto = mappingJsonToDto(newProductDtoByJsonString);

        if (productRepository.existsByProductName(newDto.getProduct_name())) {
            throw new ResourceAlreadyExistException("This Product Name Already Exist!!!");
        }

        if (productRepository.existsByProductModel(newDto.getProduct_model())) {
            throw new ResourceAlreadyExistException("This Product Model Already Exist!!!");
        }

        Product_Detail newProductDetail = new Product_Detail();
        newProductDetail.setSpecifications(myJsonStringify(newDto.getSpecifications()));
        Product newEntity = mappingToEntity(newDto);
        newEntity.setProduct_detail(newProductDetail);
        newProductDetail.setProduct(newEntity);
        handleProductRelations(newEntity, newDto);

        //Image Handler
        Path uploadPath = Paths.get(uploadDirectory).resolve(newEntity.getProduct_category().getProduct_category_name())
                .resolve(newEntity.getProduct_type().getProduct_type_name())
                .resolve(newEntity.getProduct_group().getProduct_group_name());
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileName = System.currentTimeMillis() + "_" + newImageFile.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(newImageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        //

        newEntity.setImage_name(fileName);
        newEntity.setImage_path(filePath.toString());
        newEntity.setImage_content_type(newImageFile.getContentType());

        return mappingToDto(productRepository.save(newEntity));
    }

    public Product_Dto service_update_product(String updateProductDtoByJsonString, MultipartFile updateImageFile) throws IOException {
        Product_Dto updateProductDto = mappingJsonToDto(updateProductDtoByJsonString);

        Product productInformation = productRepository.findById(updateProductDto.getProduct_id())
                .orElseThrow(() -> new ResourceNotFoundException("Product By Id: " + updateProductDto.getProduct_id() + " Not Found!!!"));

        if (productRepository.existsByProductName(updateProductDto.getProduct_name())
                && !updateProductDto.getProduct_name().equals(productInformation.getProduct_name())) {
            throw new ResourceAlreadyExistException("This Product Name Already Exist!!!");
        }

        if (productRepository.existsByProductModel(updateProductDto.getProduct_model())
                && !updateProductDto.getProduct_model().equals(productInformation.getProduct_model())) {
            throw new ResourceAlreadyExistException("This Product Model Already Exist!!!");
        }

        productInformation.setProduct_name(updateProductDto.getProduct_name());
        productInformation.setProduct_brand(updateProductDto.getProduct_brand());
        productInformation.setProduct_model(updateProductDto.getProduct_model());
        productInformation.setProduct_price(updateProductDto.getProduct_price());
        productInformation.setProduct_guaranty(updateProductDto.getProduct_guaranty());
        productInformation.setProduct_release_year(updateProductDto.getProduct_release_year());
        productInformation.setStock_inventory(updateProductDto.getStock_inventory());

        Product_Detail productDetailInformation = productInformation.getProduct_detail();
        productDetailInformation.setSpecifications(updateProductDto.getSpecifications());
        productInformation.setProduct_detail(productDetailInformation);

        handleProductRelations(productInformation, updateProductDto);

        //Handle Image
        if (updateImageFile != null && !updateImageFile.isEmpty()) {
            if (productInformation.getImage_path() != null) {
                File oldImageFile = new File(productInformation.getImage_path());
                if (oldImageFile.exists()) {
                    oldImageFile.delete();
                }
            }

            Path uploadPath = Paths.get(uploadDirectory)
                    .resolve(productInformation.getProduct_category().getProduct_category_name())
                    .resolve(productInformation.getProduct_type().getProduct_type_name())
                    .resolve(productInformation.getProduct_group().getProduct_group_name());
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String updateFileName = System.currentTimeMillis() + "_" + updateImageFile.getOriginalFilename();
            Path updateFilePath = uploadPath.resolve(updateFileName);
            Files.copy(updateImageFile.getInputStream(), updateFilePath, StandardCopyOption.REPLACE_EXISTING);

            productInformation.setImage_name(updateFileName);
            productInformation.setImage_path(updateFilePath.toString());
            productInformation.setImage_content_type(updateImageFile.getContentType());
        }

        return mappingToDto(productRepository.save(productInformation));
    }
}
