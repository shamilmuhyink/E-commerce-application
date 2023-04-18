package com.shopp.store.repository;

import com.shopp.store.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<Cart, Long> {

}
