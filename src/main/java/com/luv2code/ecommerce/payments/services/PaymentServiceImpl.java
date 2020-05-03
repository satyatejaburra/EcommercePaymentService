package com.luv2code.ecommerce.payments.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.ecommerce.payments.dao.PaymentRepository;
import com.luv2code.ecommerce.payments.entity.Payment;
import com.luv2code.ecommerce.payments.enums.PaymentStatus;
import com.luv2code.ecommerce.payments.pojo.PaymentCallback;
import com.luv2code.ecommerce.payments.pojo.PaymentDetails;
import com.luv2code.ecommerce.payments.util.PaymentUtil;

@Service
public class PaymentServiceImpl implements PaymentService {

	private final KafkaProducer producer;

	@Autowired
	public PaymentServiceImpl(KafkaProducer producer) {
		this.producer = producer;
	}

	@Autowired
	private PaymentRepository paymentRepository;

	@SuppressWarnings("static-access")
	@Override
	public PaymentDetails proceedPayment(PaymentDetails paymentDetail) {
		PaymentUtil paymentUtil = new PaymentUtil();
		paymentDetail.setEmail("satyateja100@gmail.com");
		paymentDetail = paymentUtil.populatePaymentDetail(paymentDetail);
	    savePaymentDetail(paymentDetail);
		return paymentDetail;
	}

	@Override
	public String payuCallback(PaymentCallback paymentResponse) {
		String msg = "Transaction failed.";
		Payment payment = paymentRepository.findByTxnId(paymentResponse.getTxnid());
		if (payment != null) {
			// TODO validate the hash
			PaymentStatus paymentStatus = null;
			if (paymentResponse.getStatus().equals("failure")) {
				paymentStatus = PaymentStatus.Failed;
			} else if (paymentResponse.getStatus().equals("success")) {
				paymentStatus = PaymentStatus.Success;
				msg = "Transaction success";
			}
			producer.sendMessage(paymentStatus.name());

			payment.setPaymentStatus(paymentStatus);
			payment.setMihpayId(paymentResponse.getMihpayid());
			payment.setMode(paymentResponse.getMode());
			paymentRepository.save(payment);
		}
		return msg;
	}

	private void savePaymentDetail(PaymentDetails paymentDetail) {
		Payment payment = new Payment();
		payment.setAmount(Double.parseDouble(paymentDetail.getAmount()));
		payment.setEmail(paymentDetail.getEmail());
		payment.setName(paymentDetail.getName());
		payment.setPaymentDate(new Date());
		payment.setPaymentStatus(PaymentStatus.Pending);
		payment.setPhone(paymentDetail.getPhone());
		payment.setOrderId(paymentDetail.getOrderId());
		payment.setTxnId(paymentDetail.getTxnId());
		//paymentRepository.save(payment);
	}

}
