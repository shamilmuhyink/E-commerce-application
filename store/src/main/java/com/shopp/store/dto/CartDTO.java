package com.shopp.store.dto;

import com.shopp.store.entity.Product;
import com.shopp.store.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class CartDTO {
    private long itemId;
    private Product product;
    private int quantity;
    private float itemTotal;
    private LocalDateTime updateDate;
}
