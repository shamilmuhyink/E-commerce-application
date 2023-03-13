package com.shopp.store.controller;

import com.shopp.store.entity.response.ProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts(){
    return null;
    }
}
