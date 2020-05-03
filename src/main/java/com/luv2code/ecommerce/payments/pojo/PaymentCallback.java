package com.luv2code.ecommerce.payments.pojo;

import com.luv2code.ecommerce.payments.enums.PaymentMode;

import lombok.Data;

@Data
public class PaymentCallback {

    private String txnid;
    private String mihpayid;
    private PaymentMode mode;
    private String status;
    private String hash;
}