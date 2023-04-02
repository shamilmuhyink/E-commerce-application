package com.shopp.store.entity;

import jakarta.persistence.*;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "cart_sequence")
    @SequenceGenerator(name = "cart_sequence", sequenceName = "car_seq", initialValue = 601, allocationSize = 1)
    private long cartId;

    private int quantity;

    private float cartTotal;

    @ManyToOne
    @JoinColumn(name="productId")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;
}
