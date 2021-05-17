package com.donzzul.spring.reservation.domain;

public class Reservation {

	public int reservationNo;
	public int shopNo;
	public String reserveDate;
	public int reserveTime;
	public int reserveCount;
	public String pointYn;
	public int paymentPoint;
	public int userNo;
	
	public Reservation() {}

	public Reservation(int reservationNo, int shopNo, String reserveDate, int reserveTime, int reserveCount,
			String pointYn, int paymentPoint, int userNo) {
		super();
		this.reservationNo = reservationNo;
		this.shopNo = shopNo;
		this.reserveDate = reserveDate;
		this.reserveTime = reserveTime;
		this.reserveCount = reserveCount;
		this.pointYn = pointYn;
		this.paymentPoint = paymentPoint;
		this.userNo = userNo;
	}

	public int getReservationNo() {
		return reservationNo;
	}

	public void setReservationNo(int reservationNo) {
		this.reservationNo = reservationNo;
	}

	public int getShopNo() {
		return shopNo;
	}

	public void setShopNo(int shopNo) {
		this.shopNo = shopNo;
	}

	public String getReserveDate() {
		return reserveDate;
	}

	public void setReserveDate(String reserveDate) {
		this.reserveDate = reserveDate;
	}

	public int getReserveTime() {
		return reserveTime;
	}

	public void setReserveTime(int reserveTime) {
		this.reserveTime = reserveTime;
	}

	public int getReserveCount() {
		return reserveCount;
	}

	public void setReserveCount(int reserveCount) {
		this.reserveCount = reserveCount;
	}

	public String getPointYn() {
		return pointYn;
	}

	public void setPointYn(String pointYn) {
		this.pointYn = pointYn;
	}

	public int getPaymentPoint() {
		return paymentPoint;
	}

	public void setPaymentPoint(int paymentPoint) {
		this.paymentPoint = paymentPoint;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	@Override
	public String toString() {
		return "Reservation [reservationNo=" + reservationNo + ", shopNo=" + shopNo + ", reserveDate=" + reserveDate
				+ ", reserveTime=" + reserveTime + ", reserveCount=" + reserveCount + ", pointYn=" + pointYn
				+ ", paymentPoint=" + paymentPoint + ", userNo=" + userNo + "]";
	}
	
}
