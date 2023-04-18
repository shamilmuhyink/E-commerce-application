package com.shopp.store.dto;

import com.shopp.store.entity.Cart;
import com.shopp.store.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class OrderDTO {
    private long orderId;
    private User user;
    private LocalDateTime orderDate;
    private float orderTotal;
    private List<Cart> cart = new ArrayList<>();
}
