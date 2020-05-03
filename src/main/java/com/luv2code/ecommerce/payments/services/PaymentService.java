package com.luv2code.ecommerce.payments.services;

import com.luv2code.ecommerce.payments.pojo.PaymentCallback;
import com.luv2code.ecommerce.payments.pojo.PaymentDetails;

public interface PaymentService {

	PaymentDetails proceedPayment(PaymentDetails paymentDetail);

	String payuCallback(PaymentCallback paymentResponse);

}
