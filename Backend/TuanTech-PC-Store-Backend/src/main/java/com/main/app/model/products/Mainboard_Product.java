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
@Table(name = "mainboard_product_tbl")
@PrimaryKeyJoinColumn(name = "product_id")
public class Mainboard_Product extends Product {
    private String socket;
    private String chipset;
    private String memory;
    private String onboard_graphic;
    private String audio;
    private String LAN;
    private String expansion_slot;
    private String storage_interface;
    private String usb;
    private String wireless_bluetooth;
    private String internal_io_connector;
    private String back_panel_connector;
    private String operating_system;
    private String form_factor;
}
