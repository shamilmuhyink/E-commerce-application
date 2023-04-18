package com.shopp.store.controller;

import com.shopp.store.dto.cartDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/orderitem")
public class CartController {

    @PostMapping(path="/add-to-cart")
    public ResponseEntity<cartDTO> addToCart(@RequestBody cartDTO cartRequest){
        return null;
    }

    @GetMapping(path="/get-orderitems")
    public ResponseEntity<List<cartDTO>> getCartItems(){
        return null;
    }

    @PutMapping("/update-item/{id}")
    public ResponseEntity<cartDTO> updateCart(@PathVariable long itemId, @RequestBody cartDTO cartRequest){
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> removeItem(@PathVariable long itemId){
        return null;
    }

}
