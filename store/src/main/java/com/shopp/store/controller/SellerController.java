package com.shopp.store.controller;

import com.shopp.store.entity.Product;
import com.shopp.store.entity.dto.ProductDTO;
import com.shopp.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private ProductService productService;

    @PostMapping(path = "/addProduct")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productRequest){
        return ResponseEntity.ok(productService.addProduct(productRequest));
    }

//    @GetMapping(path = "/products")
//    public List<Product> getProducts(){
//
//        return;
//    }
}
