package com.shopp.store.dto;

import com.shopp.store.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class cartDTO {
    private long itemId;
    private int quantity;
    private float itemTotal;
    private Product product;
}
