package com.main.app.apicontroller.products;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.main.app.apiresponse.ApiResponse;
import com.main.app.dto.products.Product_Dto;
import com.main.app.service.products.Product_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/collections")
public class Product_Api_Controller {
    private final Product_Service productService;

    @Autowired
    public Product_Api_Controller(Product_Service productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<Product_Dto>>> controller_all_product() {
        var result = productService.service_all_product();
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "Success", result), HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<ApiResponse<Product_Dto>> controller_detail_product(@PathVariable(name = "id") int id) {
        var result = productService.service_detail_product(id);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "Success", result), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Product_Dto>> controller_create_product(@RequestBody Product_Dto newDto) throws JsonProcessingException {
        var result = productService.service_create_product(newDto);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CREATED.value(), "Created", result), HttpStatus.CREATED);
    }
}
