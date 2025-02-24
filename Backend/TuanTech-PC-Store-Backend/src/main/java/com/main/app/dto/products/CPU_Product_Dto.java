package com.main.app.dto.products;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CPU_Product_Dto extends ProductDto {
    private String cpu_socket;
    private String cpu_cache;
    private Integer number_of_core;
    private Integer number_of_thread;
    private Double base_clock;
    private Double boost_clock;
    private Double default_tdp;
    private String compatible_ram_type;
    private String max_supported_ram_speed;
    private String pcle_support;
    private String igpu;
}
