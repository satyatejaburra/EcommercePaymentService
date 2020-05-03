package com.luv2code.ecommerce.payments.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.ecommerce.payments.enums.PaymentMode;
import com.luv2code.ecommerce.payments.pojo.PaymentCallback;
import com.luv2code.ecommerce.payments.pojo.PaymentDetails;
import com.luv2code.ecommerce.payments.services.PaymentService;

@RestController
@RequestMapping(value = "/payment")
@CrossOrigin("http://localhost:4200")
public class PaymentController {

	@Autowired
	private Environment env;

	@Autowired
	PaymentService paymentService;

	@GetMapping("/status/check")
	public String status() {
		return "Working on port " + env.getProperty("local.server.port") + ", with token = "
				+ env.getProperty("token.secret");
	}

	@PostMapping(path = "/payment-details", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	@CrossOrigin(origins = "*")
	public @ResponseBody PaymentDetails proceedPayment(@RequestBody PaymentDetails paymentDetail) {
		return paymentService.proceedPayment(paymentDetail);
	}

	@RequestMapping(path = "/payment-response", method = RequestMethod.POST)
	public @ResponseBody String payuCallback(@RequestParam String mihpayid, @RequestParam String status,
			@RequestParam PaymentMode mode, @RequestParam String txnid, @RequestParam String hash) {
		PaymentCallback paymentCallback = new PaymentCallback();
		paymentCallback.setMihpayid(mihpayid);
		paymentCallback.setTxnid(txnid);
		paymentCallback.setMode(mode);
		paymentCallback.setHash(hash);
		paymentCallback.setStatus(status);
		return paymentService.payuCallback(paymentCallback);
	}
}
