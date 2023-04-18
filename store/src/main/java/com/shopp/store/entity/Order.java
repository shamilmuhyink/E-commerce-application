package com.shopp.store.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "order_sequence")
    @SequenceGenerator(name = "order_sequence", sequenceName = "product_seq", initialValue = 601, allocationSize = 1)
    private long orderId;

    private LocalDate orderDate;
    private String orderInfo;
    @OneToMany()
    @JoinColumn(name = "productId")
    private List<Product> products = new ArrayList<>();

}
