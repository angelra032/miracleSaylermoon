package com.donzzul.spring.reservation.service.logic;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donzzul.spring.reservation.domain.Reservation;
import com.donzzul.spring.reservation.service.ReservationService;
import com.donzzul.spring.reservation.store.ReservationStore;

@Service
public class ReservationServiceImpl implements ReservationService{

	
	@Autowired
	private ReservationStore store;

	@Override
	public int insertReservation(Reservation reservation) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public HashMap<String, String> reservaionListByDream(int reservationNo, int userNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> reservationListByMZ(int reservationNo, int userNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> reservaionListByShop(int reservationNo, int shopNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteReservation(int reservationNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateReservation(Reservation reservation) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int comfirmReservation(int reservationNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int cancleReservation(int reservationNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int completeReservation(int reservationNo) {
		// TODO Auto-generated method stub
		return 0;
	}
}
