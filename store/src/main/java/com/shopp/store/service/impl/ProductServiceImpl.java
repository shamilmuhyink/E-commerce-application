package com.shopp.store.service.impl;

import com.shopp.store.entity.Category;
import com.shopp.store.entity.Product;
import com.shopp.store.dto.ProductDTO;
import com.shopp.store.repository.CategoryRepository;
import com.shopp.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class ProductServiceImpl {
    @Autowired
    private ProductRepository productDAO;

    @Autowired
    private CategoryRepository categoryDAO;

    public ProductDTO addProduct(ProductDTO productRequest){

        Product product = Product.builder()
                .stock(productRequest.getStock())
                .productDesc(productRequest.getProductDesc())
                .productName(productRequest.getProductName())
                .price(productRequest.getPrice())
                .imageURL(productRequest.getImageURL())
                .weight(productRequest.getWeight())
                .updateDate(LocalDate.now())
                .category(productRequest.getCategory())
                .build();

        if(categoryDAO.findByCategoryName(productRequest.getCategory().getCategoryName().toUpperCase()) == null){
            Category category = productRequest.getCategory();
            categoryDAO.save(category);
        }else{
            Category category = categoryDAO.findByCategoryName(productRequest.getCategory().getCategoryName().toUpperCase());
            List<Product> products = category.getProducts();
            products.add(product);
            category.setProducts(products);
            categoryDAO.save(category);
        }

        product = productDAO.save(product);

        ProductDTO productResponse = ProductDTO.builder()
                .productDesc(product.getProductDesc())
                .productName(product.getProductName())
                .productId(product.getProductId())
                .price(product.getPrice())
                .weight(product.getWeight())
                .stock(product.getStock())
                .imageURL(product.getImageURL())
                .updateDate(product.getUpdateDate())
                .category(product.getCategory())
                .build();

        return productResponse;

    }
}
