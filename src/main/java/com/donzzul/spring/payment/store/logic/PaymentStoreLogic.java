package com.donzzul.spring.payment.store.logic;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.donzzul.spring.payment.store.PaymentStore;
import com.donzzul.spring.user.domain.User;

@Repository
public class PaymentStoreLogic implements PaymentStore{

//	@Autowired
//	private SqlSessionTemplate session;
	
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
