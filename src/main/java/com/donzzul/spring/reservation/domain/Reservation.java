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
	public String rState;
//	예약기본상태 O(default) 사용자가 예약을 넣으면 예약에 이 값이 들어갑니다..
//	예약승인 Y(comfirm) // 사업자가 예약을 승인하면 이 값이 사용자에게 넘어갑니다..
//	예약취소 X(cancle) // 사용자가 취소하면 이 값이 사업자에게 넘어갑니다.. or 사업자가 취소하면 이 값이 사업자에게 넘어갑니다...
//	예약완료 C(complete) //  사업자가 예약 완료를 누르면  이 값이 사용자에게 넘어가 후기 남기는 버튼이 활성화됩니다...
	private String shopName;
	private String userNick;
	
	public Reservation() {}

	
	public Reservation(int reservationNo, int shopNo, String reserveDate, int reserveTime, int reserveCount,
			String pointYn, int paymentPoint, int userNo, String rState, String shopName, String userNick) {
		super();
		this.reservationNo = reservationNo;
		this.shopNo = shopNo;
		this.reserveDate = reserveDate;
		this.reserveTime = reserveTime;
		this.reserveCount = reserveCount;
		this.pointYn = pointYn;
		this.paymentPoint = paymentPoint;
		this.userNo = userNo;
		this.rState = rState;
		this.shopName = shopName;
		this.userNick = userNick;
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

	public String getrState() {
		return rState;
	}

	public void setrState(String rState) {
		this.rState = rState;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	@Override
	public String toString() {
		return "Reservation [reservationNo=" + reservationNo + ", shopNo=" + shopNo + ", reserveDate=" + reserveDate
				+ ", reserveTime=" + reserveTime + ", reserveCount=" + reserveCount + ", pointYn=" + pointYn
				+ ", paymentPoint=" + paymentPoint + ", userNo=" + userNo + ", rState=" + rState + ", shopName="
				+ shopName + ", userNick=" + userNick + "]";
	}

	
}
