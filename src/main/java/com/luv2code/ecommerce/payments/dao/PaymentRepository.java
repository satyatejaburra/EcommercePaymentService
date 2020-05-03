package com.luv2code.ecommerce.payments.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.luv2code.ecommerce.payments.entity.Payment;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Long>  {
	Payment findByTxnId(String txnId);

}
