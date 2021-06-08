package com.donzzul.spring.user.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements UserDetails {
	
	// 안만들어도 상관없지만 Warning이 발생함
	private static final long serialVersionUID = 1L;
	
	private int userNo;
	private String userId;
	private String userPw;
	private String userName;
	private String userNick;
	private String userPhone;
	private String userEmail;
	private String partnerVerify;
	private String partnerWithdraw;
	private String dreamCardno;
	private int userPoint;
	private String userType;
	private String partnerName;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int userNo, String userId, String userPw, String userName, String userNick, String userPhone,
			String userEmail, String partnerVerify, String partnerWithdraw, String dreamCardno, int userPoint,
			String userType, String partnerName) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userNick = userNick;
		this.userPhone = userPhone;
		this.userEmail = userEmail;
		this.partnerVerify = partnerVerify;
		this.partnerWithdraw = partnerWithdraw;
		this.dreamCardno = dreamCardno;
		this.userPoint = userPoint;
		this.userType = userType;
		this.partnerName = partnerName;
	}

	public User(String userId, String userPw) {
		super();
		this.userId = userId;
		this.userPw = userPw;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getPartnerVerify() {
		return partnerVerify;
	}

	public void setPartnerVerify(String partnerVerify) {
		this.partnerVerify = partnerVerify;
	}

	public String getPartnerWithdraw() {
		return partnerWithdraw;
	}

	public void setPartnerWithdraw(String partnerWithdraw) {
		this.partnerWithdraw = partnerWithdraw;
	}

	public String getDreamCardno() {
		return dreamCardno;
	}

	public void setDreamCardno(String dreamCardno) {
		this.dreamCardno = dreamCardno;
	}

	public int getUserPoint() {
		return userPoint;
	}

	public void setUserPoint(int userPoint) {
		this.userPoint = userPoint;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	@Override
	public String toString() {
		return "User [userNo=" + userNo + ", userId=" + userId + ", userPw=" + userPw + ", userName=" + userName
				+ ", userNick=" + userNick + ", userPhone=" + userPhone + ", userEmail=" + userEmail
				+ ", partnerVerify=" + partnerVerify + ", partnerWithdraw=" + partnerWithdraw + ", dreamCardno="
				+ dreamCardno + ", userPoint=" + userPoint + ", userType=" + userType + ", partnerName=" + partnerName
				+ "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	
	
}
