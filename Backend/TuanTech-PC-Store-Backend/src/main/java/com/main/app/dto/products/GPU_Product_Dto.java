package com.main.app.dto.products;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GPU_Product_Dto extends ProductDto {
    private String number_of_gpu_core;
    private Integer core_clock;
    private Integer boost_clock;
    private Integer vram;
    private String memory_type;
    private Integer memory_bandwidth;
    private String pcle_version;
    private Integer requirement_psu;
    private String power_connectors;
    private String direct_x_version;
    private String technologies;
    private String output_port;
    private String form_factor;
}
