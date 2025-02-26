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
@Table(name = "product_detail_tbl")
public class Product_Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer product_detail_id;

    @Column(columnDefinition = "VARCHAR(MAX)")
    private String specifications;

    @OneToOne
    @JoinColumn(name = "product_id" , nullable = false)
    private Product product;
}
