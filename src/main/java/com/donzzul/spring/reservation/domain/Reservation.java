package com.donzzul.spring.reservation.domain;

public class Reservation {

	private int reserveNo; // 예약번호
	private int shopNo; //가게고유번호
	private String usertype; //회원타입
	private int userNo; // 회원고유번호
	private String reserveDate; //예약날짜
	private int reserveTime; //예약시간
	private int reserveCount; //예약인원수
	private String pointYn; // 포인트사용여부
	private int point; //포인트

	public Reservation() {}
	
	public Reservation(int reserveNo, int shopNo, String usertype, int userNo, String reserveDate, int reserveTime,
			int reserveCount, String pointYn, int point) {
		super();
		this.reserveNo = reserveNo;
		this.shopNo = shopNo;
		this.usertype = usertype;
		this.userNo = userNo;
		this.reserveDate = reserveDate;
		this.reserveTime = reserveTime;
		this.reserveCount = reserveCount;
		this.pointYn = pointYn;
		this.point = point;
	}

	public int getReserveNo() {
		return reserveNo;
	}

	public void setReserveNo(int reserveNo) {
		this.reserveNo = reserveNo;
	}

	public int getShopNo() {
		return shopNo;
	}

	public void setShopNo(int shopNo) {
		this.shopNo = shopNo;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
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

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	@Override
	public String toString() {
		return "Reservation [reserveNo=" + reserveNo + ", shopNo=" + shopNo + ", usertype=" + usertype + ", userNo="
				+ userNo + ", reserveDate=" + reserveDate + ", reserveTime=" + reserveTime + ", reserveCount="
				+ reserveCount + ", pointYn=" + pointYn + ", point=" + point + "]";
	}
	
	
	
}
