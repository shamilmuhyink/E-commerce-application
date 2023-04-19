package com.shopp.store.repository;

import com.shopp.store.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<Cart, Long> {

//    @Query(SELECT * FROM Cart WHERE PRODUCT_ID=productId)
    @Query("FROM Cart c WHERE c.product.id = :productId")
    public Cart findCartByProductID(@Param("productId") long productId);
}
