package com.shopp.store.service;


import com.shopp.store.dto.PaymentDetails;

public interface PaymentService {
    public PaymentDetails proceedPayment(PaymentDetails paymentRequest);
}
