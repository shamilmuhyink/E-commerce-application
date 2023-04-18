package com.shopp.store.service;

import com.shopp.store.dto.cartDTO;

import java.util.List;

public interface CartService {
    public cartDTO addToCart(cartDTO itemRequest);
    public List<cartDTO> getCartItems();
}
