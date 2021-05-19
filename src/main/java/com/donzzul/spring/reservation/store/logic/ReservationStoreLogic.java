package com.donzzul.spring.reservation.store.logic;

import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.donzzul.spring.reservation.domain.Reservation;
import com.donzzul.spring.reservation.store.ReservationStore;
import com.donzzul.spring.shop.domain.Shop;
import com.donzzul.spring.user.domain.User;

@Repository
public class ReservationStoreLogic implements ReservationStore {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insertReservation(Reservation reservation) {
		return sqlSession.insert("reservationMapper.insertRservation", reservation);
	}

	// 다시 생각하기
	@Override
	public int updateUserPoint(User user) {
		return sqlSession.update("userMapper.updateUserPoint",user);
	}

	@Override
	public ArrayList<Reservation> reservationListByDream(int userNo) {
		ArrayList<Reservation> reserve = (ArrayList)sqlSession.selectList("reservationMapper.selectByDream", userNo);
		return reserve;
	}

	@Override
	public ArrayList<Reservation> reservationListByMZ(int userNo) {
		return null;
	}

	@Override
	public String reservationListByShop(int reservationNo, int ShopNo) {
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

}
