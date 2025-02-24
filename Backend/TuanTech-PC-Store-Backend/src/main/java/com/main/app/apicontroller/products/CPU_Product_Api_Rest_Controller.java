package com.main.app.apicontroller.products;

import com.main.app.apiresponse.ApiResponse;
import com.main.app.dto.products.CPU_Product_Dto;
import com.main.app.service.products.CPU_Product_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/PCComponents/CPU")
public class CPU_Product_Api_Rest_Controller {
    private final CPU_Product_Service cpuProductService;

    @Autowired
    public CPU_Product_Api_Rest_Controller(CPU_Product_Service cpuProductService) {
        this.cpuProductService = cpuProductService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<CPU_Product_Dto>>> api_controller_getAll_CPU_Product() {
        List<CPU_Product_Dto> result = cpuProductService.service_getAll_CPU_Product();
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Success", result));
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<ApiResponse<CPU_Product_Dto>> api_controller_detail_CPU_Product(@PathVariable(name = "id") int id) {
        CPU_Product_Dto result = cpuProductService.service_detail_CPU_Product(id);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Success", result));
    }

    @GetMapping("/getAll/byGroup/{group}")
    public ResponseEntity<ApiResponse<List<CPU_Product_Dto>>> api_controller_getAll_CPU_Product_by_Group(@PathVariable(name = "group") String group) {
        List<CPU_Product_Dto> result = cpuProductService.service_getAll_CPU_Product_by_Group(group);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Success", result));
    }

    @GetMapping("/getAll/byModel/{model}")
    public ResponseEntity<ApiResponse<List<CPU_Product_Dto>>> api_controller_getAll_CPU_Product_by_Model(@PathVariable(name = "model") String model) {
        List<CPU_Product_Dto> result = cpuProductService.service_getAll_CPU_Product_by_Model(model);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Success", result));
    }

    @GetMapping("/getAll/byPriceRange")
    public ResponseEntity<ApiResponse<List<CPU_Product_Dto>>> api_controller_getAll_CPU_Product_by_PriceRange(@RequestParam(name = "fromPrice") Double fromPrice ,
                                                                                                              @RequestParam(name = "toPrice") Double toPrice) {
        List<CPU_Product_Dto> result = cpuProductService.service_getAll_CPU_Product_by_PriceRange(fromPrice , toPrice);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Success", result));
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<CPU_Product_Dto>> api_controller_create_CPU_Product(@RequestBody CPU_Product_Dto newDto) {
        CPU_Product_Dto result = cpuProductService.service_create_CPU_Product(newDto);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CREATED.value(), "Created", result), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<CPU_Product_Dto>> api_controller_update_CPU_Product(@RequestBody CPU_Product_Dto updateDto) {
        CPU_Product_Dto result = cpuProductService.service_update_CPU_Product(updateDto);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "Updated", result), HttpStatus.OK);
    }
}
