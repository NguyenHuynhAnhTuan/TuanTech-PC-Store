package com.main.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_group_tbl")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductGroup {
    private Integer id;
    private String productGroupName;
}
