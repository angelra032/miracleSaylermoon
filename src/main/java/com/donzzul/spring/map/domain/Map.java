package com.donzzul.spring.map.domain;

import java.sql.Timestamp;

public class Map {

	private int shopNo;
	private String shopName;
	private String shopShortAddr;
	private String shopTarget;
	private String shopProduct;
	private String shopFileName;
	private String shopFilePath;
	private long shopFileSize;
	private Timestamp shopUploadTime;
	private int shopPhone;
	private String shopLat;
	private String shopLng;
	private int menuFileNo;
	private String menuFileName;
	private String menuFilePath;
	private long menuFileSize;
	private Timestamp menuUploadTime;
	
	public Map() {}

	public Map(int shopNo, String shopName, String shopShortAddr, String shopTarget, String shopProduct,
			String shopFileName, String shopFilePath, long shopFileSize, Timestamp shopUploadTime, int shopPhone,
			String shopLat, String shopLng, int menuFileNo, String menuFileName, String menuFilePath, long menuFileSize,
			Timestamp menuUploadTime) {
		super();
		this.shopNo = shopNo;
		this.shopName = shopName;
		this.shopShortAddr = shopShortAddr;
		this.shopTarget = shopTarget;
		this.shopProduct = shopProduct;
		this.shopFileName = shopFileName;
		this.shopFilePath = shopFilePath;
		this.shopFileSize = shopFileSize;
		this.shopUploadTime = shopUploadTime;
		this.shopPhone = shopPhone;
		this.shopLat = shopLat;
		this.shopLng = shopLng;
		this.menuFileNo = menuFileNo;
		this.menuFileName = menuFileName;
		this.menuFilePath = menuFilePath;
		this.menuFileSize = menuFileSize;
		this.menuUploadTime = menuUploadTime;
	}

	public int getShopNo() {
		return shopNo;
	}

	public void setShopNo(int shopNo) {
		this.shopNo = shopNo;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopShortAddr() {
		return shopShortAddr;
	}

	public void setShopShortAddr(String shopShortAddr) {
		this.shopShortAddr = shopShortAddr;
	}

	public String getShopTarget() {
		return shopTarget;
	}

	public void setShopTarget(String shopTarget) {
		this.shopTarget = shopTarget;
	}

	public String getShopProduct() {
		return shopProduct;
	}

	public void setShopProduct(String shopProduct) {
		this.shopProduct = shopProduct;
	}

	public String getShopFileName() {
		return shopFileName;
	}

	public void setShopFileName(String shopFileName) {
		this.shopFileName = shopFileName;
	}

	public String getShopFilePath() {
		return shopFilePath;
	}

	public void setShopFilePath(String shopFilePath) {
		this.shopFilePath = shopFilePath;
	}

	public long getShopFileSize() {
		return shopFileSize;
	}

	public void setShopFileSize(long shopFileSize) {
		this.shopFileSize = shopFileSize;
	}

	public Timestamp getShopUploadTime() {
		return shopUploadTime;
	}

	public void setShopUploadTime(Timestamp shopUploadTime) {
		this.shopUploadTime = shopUploadTime;
	}

	public int getShopPhone() {
		return shopPhone;
	}

	public void setShopPhone(int shopPhone) {
		this.shopPhone = shopPhone;
	}

	public String getShopLat() {
		return shopLat;
	}

	public void setShopLat(String shopLat) {
		this.shopLat = shopLat;
	}

	public String getShopLng() {
		return shopLng;
	}

	public void setShopLng(String shopLng) {
		this.shopLng = shopLng;
	}

	public int getMenuFileNo() {
		return menuFileNo;
	}

	public void setMenuFileNo(int menuFileNo) {
		this.menuFileNo = menuFileNo;
	}

	public String getMenuFileName() {
		return menuFileName;
	}

	public void setMenuFileName(String menuFileName) {
		this.menuFileName = menuFileName;
	}

	public String getMenuFilePath() {
		return menuFilePath;
	}

	public void setMenuFilePath(String menuFilePath) {
		this.menuFilePath = menuFilePath;
	}

	public long getMenuFileSize() {
		return menuFileSize;
	}

	public void setMenuFileSize(long menuFileSize) {
		this.menuFileSize = menuFileSize;
	}

	public Timestamp getMenuUploadTime() {
		return menuUploadTime;
	}

	public void setMenuUploadTime(Timestamp menuUploadTime) {
		this.menuUploadTime = menuUploadTime;
	}

	@Override
	public String toString() {
		return "Map [shopNo=" + shopNo + ", shopName=" + shopName + ", shopShortAddr=" + shopShortAddr + ", shopTarget="
				+ shopTarget + ", shopProduct=" + shopProduct + ", shopFileName=" + shopFileName + ", shopFilePath="
				+ shopFilePath + ", shopFileSize=" + shopFileSize + ", shopUploadTime=" + shopUploadTime
				+ ", shopPhone=" + shopPhone + ", shopLat=" + shopLat + ", shopLng=" + shopLng + ", menuFileNo="
				+ menuFileNo + ", menuFileName=" + menuFileName + ", menuFilePath=" + menuFilePath + ", menuFileSize="
				+ menuFileSize + ", menuUploadTime=" + menuUploadTime + "]";
	}

}
