package com.donzzul.spring.payment.domain;

public class DonCount {
	private int donPriceSum; // 날짜별 돈쭐합
	private int donPricePepole; // 돈쭐낸 사람합
	
	public DonCount() {}

	public DonCount(int donPriceSum, int donPricePepole) {
		super();
		this.donPriceSum = donPriceSum;
		this.donPricePepole = donPricePepole;
	}

	public int getDonPriceSum() {
		return donPriceSum;
	}

	public void setDonPriceSum(int donPriceSum) {
		this.donPriceSum = donPriceSum;
	}

	public int getDonPricePepole() {
		return donPricePepole;
	}

	public void setDonPricePepole(int donPricePepole) {
		this.donPricePepole = donPricePepole;
	}

	@Override
	public String toString() {
		return "DonCount [donPriceSum=" + donPriceSum + ", donPricePepole=" + donPricePepole + "]";
	}
	
	
}
