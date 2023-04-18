package com.shopp.store.entity;

import jakarta.persistence.*;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "cart_sequence")
    @SequenceGenerator(name = "cart_sequence", sequenceName = "car_seq", initialValue = 501, allocationSize = 1)
    private long itemId;

    private int quantity;

    private float itemTotal;

    @ManyToOne
    @JoinColumn(name="productId")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;
}
