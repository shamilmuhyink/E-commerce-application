package com.shopp.store.controller;

import com.shopp.store.dto.CartDTO;
import com.shopp.store.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping(path="/add-to-cart")
    public ResponseEntity<CartDTO> addToCart(@RequestBody CartDTO cartRequest){
        return ResponseEntity.ok(cartService.addToCart(cartRequest));
    }

    @GetMapping(path="/get-cart-items")
    public ResponseEntity<List<CartDTO>> getCartItems(){
        return null;
    }

    @PutMapping("/update-cart-item/{id}")
    public ResponseEntity<CartDTO> updateCart(@PathVariable long itemId, @RequestBody CartDTO cartRequest){
        return ResponseEntity.ok(cartService.updateCartItem(itemId,cartRequest));
    }

    @DeleteMapping("/delete-cart-item/{id}")
    public ResponseEntity<String> removeItem(@PathVariable long itemId){
        return null;
    }

}
