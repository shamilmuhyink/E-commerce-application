package com.shopp.store.service;

import com.shopp.store.dto.CartDTO;

import java.util.List;

public interface CartService {
    public CartDTO addToCart(CartDTO cartRequest);
    public List<CartDTO> getCartItems();
    public CartDTO updateCartItem(long cartId, CartDTO cartRequest);
    public String deleteCartItem(long cartId);
}
