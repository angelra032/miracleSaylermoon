package com.donzzul.spring.reservation.service.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donzzul.spring.reservation.store.ReservationStrore;

@Service
public class ReservationServiceImpl {

	
	@Autowired
	private ReservationStrore store;
}
