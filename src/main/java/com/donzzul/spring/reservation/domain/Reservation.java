package com.donzzul.spring.reservation.domain;

public class Reservation {

	private int reserveNo;
	private int shopNo;
	private char usertype;
	private int userNo;
	private String reserveDate;
	private int reserveTime;
	private int reserveCount;
	private String pointYn;
	private int point;
	private int userNo2;
	
	public Reservation(int reserveNo, int shopNo, char usertype, int userNo, String reserveDate, int reserveTime,
			int reserveCount, String pointYn, int point, int userNo2) {
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
		this.userNo2 = userNo2;
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

	public char getUsertype() {
		return usertype;
	}

	public void setUsertype(char usertype) {
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

	public int getUserNo2() {
		return userNo2;
	}

	public void setUserNo2(int userNo2) {
		this.userNo2 = userNo2;
	}

	@Override
	public String toString() {
		return "Reservation [reserveNo=" + reserveNo + ", shopNo=" + shopNo + ", usertype=" + usertype + ", userNo="
				+ userNo + ", reserveDate=" + reserveDate + ", reserveTime=" + reserveTime + ", reserveCount="
				+ reserveCount + ", pointYn=" + pointYn + ", point=" + point + ", userNo2=" + userNo2 + "]";
	}
	
	
}
