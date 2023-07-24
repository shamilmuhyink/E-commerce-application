package com.shopp.store.controller;

import com.shopp.store.dto.PaymentDetails;
import com.shopp.store.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins="localhost:4200/")
@RequestMapping(path="/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @GetMapping
    public ResponseEntity<String> Hello(){
        return ResponseEntity.ok("Hello");
    }

    @PostMapping(path="/payment-details")
    public ResponseEntity<PaymentDetails> proceedPayment(@RequestBody PaymentDetails paymentRequest){
        return ResponseEntity.ok(paymentService.proceedPayment(paymentRequest));
    }
}
