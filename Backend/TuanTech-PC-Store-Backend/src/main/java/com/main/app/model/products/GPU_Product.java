package com.main.app.model.products;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gpu_product_tbl")
@PrimaryKeyJoinColumn(name = "product_id")
public class GPU_Product extends Product {
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
