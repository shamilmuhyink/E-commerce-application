package com.shopp.store.controller;

import com.shopp.store.dto.ProductDTO;
import com.shopp.store.repository.CategoryRepository;
import com.shopp.store.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PostAuthorize;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductServiceImpl productServiceImpl;
    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping(path = "/addProduct")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productRequest){
        return ResponseEntity.ok(productServiceImpl.addProduct(productRequest));
    }

//    @GetMapping(path= "/list-products")
//    public ResponseEntity<List<ProductDTO>> getProducts(){
//        return ResponseEntity.ok(productServiceImpl.getProducts());
//    }

//    @PutMapping("/update/{productId}")
//    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long productId, @RequestBody ProductDTO productRequest){
//        return ResponseEntity.ok(productServiceImpl.updateProduct(productId, productRequest));
//    }

//    @DeleteMapping("/delete/{productId}")
//    public ResponseEntity<String> deleteProduct(@PathVariable long productId){
//        return ResponseEntity.ok(productServiceImpl.deleteProduct(productId));
//    }

}
