package com.main.app.apicontroller.products;

import com.main.app.apiresponse.ApiResponse;
import com.main.app.dto.products.Product_Dto;
import com.main.app.service.products.Product_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@RestController
@RequestMapping("/collections")
public class Product_Api_Controller {
    private final Product_Service productService;

    @Autowired
    public Product_Api_Controller(Product_Service productService) {
        this.productService = productService;
    }

    private String getContentType(String filename) {
        if (filename.endsWith(".jpg") || filename.endsWith(".jpeg")) return "image/jpeg";
        if (filename.endsWith(".png")) return "image/png";
        if (filename.endsWith(".gif")) return "image/gif";
        if (filename.endsWith(".pdf")) return "application/pdf";
        return "application/octet-stream"; // Default
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



    @GetMapping("/image/{category}/{type}/{group}/{fileName}")
    public ResponseEntity<Resource> controller_image_product(
            @PathVariable(name = "category") String category,
            @PathVariable(name = "type") String type,
            @PathVariable(name = "group") String group,
            @PathVariable(name = "fileName") String fileName
    ) throws MalformedURLException {
        Resource imageResource = productService.service_image_resource(category , type , group , fileName);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(getContentType(fileName)))
                .body(imageResource);
    }

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<Product_Dto>> controller_create_product(@RequestPart("newDtoOriginString") String newDtoOriginString,
                                                                              @RequestPart("newImageFile") MultipartFile newImageFile
    ) throws IOException {
        var result = productService.service_create_product(newDtoOriginString , newImageFile);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CREATED.value(), "Created", result), HttpStatus.CREATED);
    }

}
