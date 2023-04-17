package com.shopp.store.controller;

import com.shopp.store.customException.ResourceNotFoundException;
import com.shopp.store.customException.UserAlreadyExistException;
import com.shopp.store.dto.ProductDTO;
import com.shopp.store.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;



    @PostMapping(path = "/add-product")
    @PreAuthorize("SELLER")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productRequest){
        return ResponseEntity.ok(productService.addProduct(productRequest));
    }
    @GetMapping(path= "/list-products")
    public ResponseEntity<List<ProductDTO>> getProducts(){
        return ResponseEntity.ok(productService.getProducts());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }
    @PutMapping("/update/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long productId, @RequestBody ProductDTO productRequest){
        return ResponseEntity.ok(productService.updateProduct(productId, productRequest));
    }
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable long productId){
        return ResponseEntity.ok(productService.deleteProduct(productId));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex){
        String error = ex.getMessage();
        return ResponseEntity.badRequest().body(error);
    }

}
