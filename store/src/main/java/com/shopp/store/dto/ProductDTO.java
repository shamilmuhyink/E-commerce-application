package com.shopp.store.dto;

import com.shopp.store.entity.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
public class ProductDTO {
    private long productId;
    private String productName;
    private String imageURL;
    private float price;
    private float weight;
    private String productDesc;
    private LocalDate updateDate;
    private float stock;
    private Category category;
}
