package com.donzzul.spring.shop.domain;

public class MainMenu {
	
	private int shopNo;
	private int mainMenuNo;
	private String mainMenuName;
	private int mainMenuPrice;

	public MainMenu() {}

	public MainMenu(int shopNo, int mainMenuNo, String mainMenuName, int mainMenuPrice) {
		super();
		this.shopNo = shopNo;
		this.mainMenuNo = mainMenuNo;
		this.mainMenuName = mainMenuName;
		this.mainMenuPrice = mainMenuPrice;
	}

	public int getShopNo() {
		return shopNo;
	}

	public void setShopNo(int shopNo) {
		this.shopNo = shopNo;
	}

	public int getMainMenuNo() {
		return mainMenuNo;
	}

	public void setMainMenuNo(int mainMenuNo) {
		this.mainMenuNo = mainMenuNo;
	}

	public String getMainMenuName() {
		return mainMenuName;
	}

	public void setMainMenuName(String mainMenuName) {
		this.mainMenuName = mainMenuName;
	}

	public int getMainMenuPrice() {
		return mainMenuPrice;
	}

	public void setMainMenuPrice(int mainMenuPrice) {
		this.mainMenuPrice = mainMenuPrice;
	}

	@Override
	public String toString() {
		return "MainMenu [shopNo=" + shopNo + ", mainMenuNo=" + mainMenuNo + ", mainMenuName=" + mainMenuName
				+ ", mainMenuPrice=" + mainMenuPrice + "]";
	}
	
}
