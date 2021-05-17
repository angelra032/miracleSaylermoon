package com.donzzul.spring.reservation.service.logic;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donzzul.spring.reservation.domain.Reservation;
import com.donzzul.spring.reservation.service.ReservationService;
import com.donzzul.spring.reservation.store.ReservationStore;
import com.donzzul.spring.shop.domain.Shop;
import com.donzzul.spring.user.domain.User;

@Service
public class ReservationServiceImpl implements ReservationService{

	
	@Autowired
	private ReservationStore store;

	@Override
	public int insertReservation(Reservation reservation) {
		System.out.println("서비스임플에는 들어왔니!?");
		System.out.println("서비스임플" + reservation);
		return store.insertReservation(reservation);
	}

	@Override
	public int updateUserPoint(User user) {
		System.out.println("포인트 서비스 임플이야!");
		return store.updateUserPoint(user);
	}
	
	@Override
	public String reservaionListByDream(int reservationNo, int userNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String reservationListByMZ(int reservationNo, int userNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String reservaionListByShop(int reservationNo, int shopNo) {
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
