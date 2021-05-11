package com.donzzul.spring.payment.service.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donzzul.spring.payment.service.PaymentService;
import com.donzzul.spring.payment.store.PaymentStore;
import com.donzzul.spring.user.domain.User;

@Service
public class PaymentServiceImpl implements PaymentService{

	@Autowired
	private PaymentStore store;
	
	@Override
	public int updatePoint(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateDonList(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String selectDonList(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
