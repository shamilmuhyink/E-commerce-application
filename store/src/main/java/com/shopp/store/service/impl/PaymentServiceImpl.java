package com.shopp.store.service.impl;

import com.shopp.store.dto.PaymentDetails;
import com.shopp.store.entity.Payment;
import com.shopp.store.repository.PaymentRepository;
import com.shopp.store.service.PaymentService;
import com.shopp.store.util.PaymentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shopp.store.entity.enumeration.PaymentStatus;

import java.util.Date;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepo;

    @Autowired
    private PaymentUtil paymentUtil;

    @Override
    public PaymentDetails proceedPayment(PaymentDetails paymentRequest) {
        PaymentUtil paymentUtil = new PaymentUtil();
        paymentRequest = paymentUtil.populatePaymentDetail(paymentRequest);
        PaymentDetails paymentResponse = savePaymentDetail(paymentRequest);
        return paymentResponse;
    }

    private PaymentDetails savePaymentDetail(PaymentDetails paymentRequest) {
        Payment payment = new Payment();
        payment.setAmount(Double.parseDouble(paymentRequest.getAmount()));
        payment.setEmail(paymentRequest.getEmail());
        payment.setName(paymentRequest.getName());
        payment.setPaymentDate(new Date());
        payment.setPaymentStatus(PaymentStatus.PENDING);
        payment.setPhone(paymentRequest.getPhone());
        payment.setProductInfo(paymentRequest.getProductInfo());
        payment.setTxnId(paymentRequest.getTxnId());
        paymentRepo.save(payment);
        return paymentRequest;
    }
}
