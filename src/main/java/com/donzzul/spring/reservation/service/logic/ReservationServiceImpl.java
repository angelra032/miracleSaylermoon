package com.donzzul.spring.reservation.service.logic;

import java.util.ArrayList;
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
	private ReservationStore rStore;

	@Override
	public int insertReservation(Reservation reservation) {
		return rStore.insertReservation(reservation);
	}

	@Override
	public int updateUserPoint(User user) {
		return rStore.updateUserPoint(user);
	}
	
	@Override
	public ArrayList<Reservation> reservaionListByDream(int userNo) {
		ArrayList<Reservation> rList = rStore.reservationListByDream(userNo);
		System.out.println(rList);
		return rList;
	}

	@Override
	public ArrayList<Reservation> reservationListByMZ(int userNo) {
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
