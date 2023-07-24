package com.shopp.store.entity;

import com.shopp.store.entity.enumeration.PaymentMode;
import com.shopp.store.entity.enumeration.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Payment {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String email;
    @Column
    private String name;
    @Column
    private String phone;
    @Column
    private String productInfo;
    @Column
    private Double amount;
    @Column
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    @Column
    @Temporal(TemporalType.DATE)
    private Date paymentDate;
    @Column
    private String txnId;
    @Column
    private String mihpayId;
    @Column
    @Enumerated(EnumType.STRING)
    private PaymentMode mode;
}
