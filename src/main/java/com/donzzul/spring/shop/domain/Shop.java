package com.donzzul.spring.shop.domain;

import java.sql.Timestamp;

public class Shop {
	
	private int shopNo;
	private int reserveNo;
	private String shopName;
	private String shopShortAddr;
	private String shopAddr;
	private String shopTarget;
	private String shopProduct;
	private String shopType;
	private String shopFileName;
	private String shopFilePath;
	private long shopFileSize;
	private Timestamp shopUploadTime;
	private int shopPhone;
	private String shopParking;
	private int shopMaxReserv;
	private String shopContent;
	private String shopLat;
	private String shopLng;
	private int shopPoint;
	private String shopPointYn;
	private int userNo;
	private int donNo;
	private String startTime;
	private String endTime;
	private int businessDay;
	
	public Shop() {}

	public Shop(int shopNo, int reserveNo, String shopName, String shopShortAddr, String shopAddr, String shopTarget,
			String shopProduct, String shopType, String shopFileName, String shopFilePath, long shopFileSize,
			Timestamp shopUploadTime, int shopPhone, String shopParking, int shopMaxReserv, String shopContent,
			String shopLat, String shopLng, int shopPoint, String shopPointYn, int userNo, int donNo, String startTime,
			String endTime, int businessDay) {
		super();
		this.shopNo = shopNo;
		this.reserveNo = reserveNo;
		this.shopName = shopName;
		this.shopShortAddr = shopShortAddr;
		this.shopAddr = shopAddr;
		this.shopTarget = shopTarget;
		this.shopProduct = shopProduct;
		this.shopType = shopType;
		this.shopFileName = shopFileName;
		this.shopFilePath = shopFilePath;
		this.shopFileSize = shopFileSize;
		this.shopUploadTime = shopUploadTime;
		this.shopPhone = shopPhone;
		this.shopParking = shopParking;
		this.shopMaxReserv = shopMaxReserv;
		this.shopContent = shopContent;
		this.shopLat = shopLat;
		this.shopLng = shopLng;
		this.shopPoint = shopPoint;
		this.shopPointYn = shopPointYn;
		this.userNo = userNo;
		this.donNo = donNo;
		this.startTime = startTime;
		this.endTime = endTime;
		this.businessDay = businessDay;
	}

	public int getShopNo() {
		return shopNo;
	}

	public void setShopNo(int shopNo) {
		this.shopNo = shopNo;
	}

	public int getReserveNo() {
		return reserveNo;
	}

	public void setReserveNo(int reserveNo) {
		this.reserveNo = reserveNo;
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

	public String getShopAddr() {
		return shopAddr;
	}

	public void setShopAddr(String shopAddr) {
		this.shopAddr = shopAddr;
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

	public String getShopType() {
		return shopType;
	}

	public void setShopType(String shopType) {
		this.shopType = shopType;
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

	public String getShopParking() {
		return shopParking;
	}

	public void setShopParking(String shopParking) {
		this.shopParking = shopParking;
	}

	public int getShopMaxReserv() {
		return shopMaxReserv;
	}

	public void setShopMaxReserv(int shopMaxReserv) {
		this.shopMaxReserv = shopMaxReserv;
	}

	public String getShopContent() {
		return shopContent;
	}

	public void setShopContent(String shopContent) {
		this.shopContent = shopContent;
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

	public int getShopPoint() {
		return shopPoint;
	}

	public void setShopPoint(int shopPoint) {
		this.shopPoint = shopPoint;
	}

	public String getShopPointYn() {
		return shopPointYn;
	}

	public void setShopPointYn(String shopPointYn) {
		this.shopPointYn = shopPointYn;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public int getDonNo() {
		return donNo;
	}

	public void setDonNo(int donNo) {
		this.donNo = donNo;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getBusinessDay() {
		return businessDay;
	}

	public void setBusinessDay(int businessDay) {
		this.businessDay = businessDay;
	}

	@Override
	public String toString() {
		return "Shop [shopNo=" + shopNo + ", reserveNo=" + reserveNo + ", shopName=" + shopName + ", shopShortAddr="
				+ shopShortAddr + ", shopAddr=" + shopAddr + ", shopTarget=" + shopTarget + ", shopProduct="
				+ shopProduct + ", shopType=" + shopType + ", shopFileName=" + shopFileName + ", shopFilePath="
				+ shopFilePath + ", shopFileSize=" + shopFileSize + ", shopUploadTime=" + shopUploadTime
				+ ", shopPhone=" + shopPhone + ", shopParking=" + shopParking + ", shopMaxReserv=" + shopMaxReserv
				+ ", shopContent=" + shopContent + ", shopLat=" + shopLat + ", shopLng=" + shopLng + ", shopPoint="
				+ shopPoint + ", shopPointYn=" + shopPointYn + ", userNo=" + userNo + ", donNo=" + donNo
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", businessDay=" + businessDay + "]";
	}

}
