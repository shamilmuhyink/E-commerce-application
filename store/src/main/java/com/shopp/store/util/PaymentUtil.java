package com.shopp.store.util;

import com.shopp.store.dto.PaymentDetails;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class PaymentUtil {
    private static final String paymentKey = "Or8ZQG";

    private static final String paymentSalt = "qtBR2QrhB4gpMutPx5Tn1HcLR5FZVTZ5";

    private static final String sUrl = "http://localhost:8080/payment/payment-response";

    private static final String fUrl = "http://localhost:8080/payment/payment-response";

    public static PaymentDetails populatePaymentDetail(PaymentDetails paymentRequest){
        String hashString = "";
        Random rand = new Random();
        String randomId = Integer.toString(rand.nextInt()) + (System.currentTimeMillis() / 1000L);
        String txnId = "txn-" + hashCal("SHA-256", randomId).substring(0, 12);
        paymentRequest.setTxnId(txnId);
//        String hash = "";
//        hashSequence = key|txnid|amount|productinfo|firstname|email|udf1|udf2|udf3|udf4|udf5||||||salt.
//                $hash = hash("sha512", $hashSequence);
//        Where salt is available on the PayUMoney dashboard.
        String hashSequence = "key|txnId|amount|productInfo|firstname|email|||||||||||";
        hashString = hashSequence.concat(paymentSalt);
        hashString = hashString.replace("key", paymentKey);
        hashString = hashString.replace("txnId", txnId);
        hashString = hashString.replace("amount", paymentRequest.getAmount());
        hashString = hashString.replace("productInfo", paymentRequest.getProductInfo());
        hashString = hashString.replace("firstname", paymentRequest.getName());
        hashString = hashString.replace("email", paymentRequest.getEmail());

        String hash = hashCal(hashString);
//        hash = sha512(Or8ZQG|txn-f236eefd60f8|100.00|test|test|test@gmail.com|||||||||||qtBR2QrhB4gpMutPx5Tn1HcLR5FZVTZ5)
//        String hash = encryptHash(hashSequence);
        paymentRequest.setHash(hash);
        paymentRequest.setFUrl(fUrl);
        paymentRequest.setSUrl(sUrl);
        paymentRequest.setKey(paymentKey);
        return paymentRequest;
    }

    private static String hashCal(String input) {
        try {
            MessageDigest sha512Digect = MessageDigest.getInstance("SHA-512");
            byte[] inputBytes = input.getBytes(StandardCharsets.UTF_8);
            byte[] hashBytes = sha512Digect.digest(inputBytes);
            StringBuilder sb = new StringBuilder();
            for(byte b : hashBytes){
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String hashCal(String type, String str) {
        byte[] hashseq = str.getBytes(StandardCharsets.UTF_8);
        StringBuffer hexString = new StringBuffer();
        try {
            MessageDigest  md = MessageDigest.getInstance(type);
            md.reset();
            md.update(hashseq);
            byte messageDigest[] = md.digest();
            for (int i = 0; i < messageDigest.length; i++) {
                String hex = Integer.toHexString(0xFF & messageDigest[i]);
                if (hex.length() == 1) {
                    hexString.append("0");
                }
                hexString.append(hex);
            }

        } catch (NoSuchAlgorithmException nsae) {
        }
        return hexString.toString();
    }

}
