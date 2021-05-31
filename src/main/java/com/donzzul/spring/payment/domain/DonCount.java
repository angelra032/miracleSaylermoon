package com.donzzul.spring.payment.domain;

public class DonCount {
	private int donPriceSum; // 날짜별 돈쭐합
	private int donPricePepole; // 돈쭐낸 사람합
	private int donUserPoint; // 유저획득 포인트
	private int donJjul; // 돈쭐에서 가져가는 수수료(10퍼센트)
	private int donPartnerPoint; // 사업자가 가져가는 금액
	
	public DonCount() {}

	public DonCount(int donPriceSum, int donPricePepole, int donUserPoint, int donJjul, int donPartnerPoint) {
		super();
		this.donPriceSum = donPriceSum;
		this.donPricePepole = donPricePepole;
		this.donUserPoint = donUserPoint;
		this.donJjul = donJjul;
		this.donPartnerPoint = donPartnerPoint;
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

	public int getDonUserPoint() {
		return donUserPoint;
	}

	public void setDonUserPoint(int donUserPoint) {
		this.donUserPoint = donUserPoint;
	}

	public int getDonJjul() {
		return donJjul;
	}

	public void setDonJjul(int donJjul) {
		this.donJjul = donJjul;
	}

	public int getDonPartnerPoint() {
		return donPartnerPoint;
	}

	public void setDonPartnerPoint(int donPartnerPoint) {
		this.donPartnerPoint = donPartnerPoint;
	}

	@Override
	public String toString() {
		return "DonCount [donPriceSum=" + donPriceSum + ", donPricePepole=" + donPricePepole + ", donUserPoint="
				+ donUserPoint + ", donJjul=" + donJjul + ", donPartnerPoint=" + donPartnerPoint + "]";
	}
	
	
	
}
