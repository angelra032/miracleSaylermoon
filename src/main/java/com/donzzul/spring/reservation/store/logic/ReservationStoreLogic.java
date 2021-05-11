package com.donzzul.spring.reservation.store.logic;

import java.sql.SQLSyntaxErrorException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.donzzul.spring.reservation.domain.Reservation;
import com.donzzul.spring.reservation.store.ReservationStore;

@Repository
public class ReservationStoreLogic implements ReservationStore{

	@Override
	public int insertReservation(Reservation reserveReservation) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public HashMap<String, String> reservationListByDream(int reservationNo, int userNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> reservationListByMZ(int reservationNo, int userNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> reservationListByShop(int reservationNo, int ShopNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteReservation(int reservationNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateReservation(int reservationNo) {
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
	public int complteReservation(int reservationNo) {
		// TODO Auto-generated method stub
		return 0;
	}

//	@Autowired
//	private SqlSessionTemplate sqlSession;
}
