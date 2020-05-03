package com.luv2code.ecommerce.payments.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.luv2code.ecommerce.payments.enums.PaymentMode;
import com.luv2code.ecommerce.payments.enums.PaymentStatus;

import lombok.Data;

@Entity
@Table(name = "Payment")
@Data
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
	
	@Column(name="order_id")
	private String orderId;
	
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
