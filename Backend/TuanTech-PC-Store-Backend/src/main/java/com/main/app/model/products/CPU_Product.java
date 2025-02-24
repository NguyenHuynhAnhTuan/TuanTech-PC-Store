package com.main.app.model.products;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cpu_product_tbl")
@PrimaryKeyJoinColumn(name = "product_id")
public class CPU_Product extends Product {

    private String cpu_socket;
    private String cpu_cache;

    private Integer number_of_core;
    private Integer number_of_thread;

    private Double base_clock;
    private Double boost_clock;

    private Double default_tdp;

    private String compatible_ram_type;
    private String max_supported_ram_speed;

    private String PCLe_support;

    private String iGPU;
}
