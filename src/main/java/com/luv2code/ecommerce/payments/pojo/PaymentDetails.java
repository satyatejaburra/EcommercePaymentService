package com.luv2code.ecommerce.payments.pojo;

import lombok.Data;

@Data
public class PaymentDetails {

	private String email;
	private String name;
	private String phone;
	private String productInfo;
	private String amount;
	private String txnId;
	private String hash;
	private String sUrl;
	private String fUrl;
	private String key;
	private String serviceProvider;
	private String orderId;
}
