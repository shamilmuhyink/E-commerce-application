package com.shopp.store.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "product_sequence")
    @SequenceGenerator(name = "product_sequence", sequenceName = "product_seq", initialValue = 301, allocationSize = 1)
    private long productId;
    private String productName;
    private String imageURL;
    private float price;
    private float weight;
    private String productDesc;
    private LocalDateTime updateDate;
    private float stock;
    @ManyToOne
    @JoinColumn(name="categoryId", referencedColumnName = "categoryId")
    private Category category;
}
