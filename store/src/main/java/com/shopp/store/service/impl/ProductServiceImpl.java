package com.shopp.store.service.impl;

import com.shopp.store.customException.ResourceNotFoundException;
import com.shopp.store.entity.Category;
import com.shopp.store.entity.Product;
import com.shopp.store.dto.ProductDTO;
import com.shopp.store.repository.CategoryRepository;
import com.shopp.store.repository.ProductRepository;
import com.shopp.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productDAO;

    @Autowired
    private CategoryRepository categoryDAO;

    @Override
    public ProductDTO addProduct(ProductDTO productRequest){

        Product product = Product.builder()
                .stock(productRequest.getStock())
                .productDesc(productRequest.getProductDesc())
                .productName(productRequest.getProductName())
                .price(productRequest.getPrice())
                .imageURL(productRequest.getImageURL())
                .weight(productRequest.getWeight())
                .updateDate(LocalDateTime.now())
                .build();

        Category category = categoryDAO.findByCategoryName(productRequest.getCategory().getCategoryName());
        if(category == null){
            category = productRequest.getCategory();
            categoryDAO.save(category);
        }

        product.setCategory(category);
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

    @Override
    public List<ProductDTO> getProducts() {
        List<Product> products = productDAO.findAll();
        List<ProductDTO> productResponseList= new ArrayList<ProductDTO>();
        for (Product product: products) {
            ProductDTO productResponse = ProductDTO.builder()
                    .productId(product.getProductId())
                    .productName(product.getProductName())
                    .imageURL(product.getImageURL())
                    .price(product.getPrice())
                    .weight(product.getWeight())
                    .productDesc(product.getProductDesc())
                    .updateDate(product.getUpdateDate())
                    .stock(product.getStock())
                    .category(product.getCategory())
                    .build();
            productResponseList.add(productResponse);
        }
        return productResponseList;
    }

    @Override
    public ProductDTO getProductById(long id) {
        Product product = productDAO.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Resource not found"));
        ProductDTO productResponse = ProductDTO.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .imageURL(product.getImageURL())
                .price(product.getPrice())
                .weight(product.getWeight())
                .productDesc(product.getProductDesc())
                .updateDate(product.getUpdateDate())
                .stock(product.getStock())
                .category(product.getCategory())
                .build();
        return productResponse;
    }

    @Override
    public ProductDTO updateProduct(long productId, ProductDTO productRequest) {
        Product product = productDAO.findById(productId)
                .orElseThrow(()-> new ResourceNotFoundException("Resource not found"));
        product = Product.builder()
                .productId(productId)
                .productName(productRequest.getProductName())
                .imageURL(productRequest.getImageURL())
                .price(productRequest.getPrice())
                .weight(productRequest.getWeight())
                .productDesc(productRequest.getProductDesc())
                .updateDate(LocalDateTime.now())
                .stock(productRequest.getStock())
                .category(product.getCategory())
                .build();

        product = productDAO.save(product);

        ProductDTO productResponse = ProductDTO.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .imageURL(product.getImageURL())
                .price(product.getPrice())
                .weight(product.getWeight())
                .productDesc(product.getProductDesc())
                .updateDate(product.getUpdateDate())
                .stock(product.getStock())
                .category(product.getCategory())
                .build();

        return productResponse;
    }

    @Override
    public String deleteProduct(long productId) {
        Product product = productDAO.findById(productId)
                .orElseThrow(()-> new ResourceNotFoundException("Resource not found"));
        productDAO.delete(product);
        return "Item removed";
    }
}
