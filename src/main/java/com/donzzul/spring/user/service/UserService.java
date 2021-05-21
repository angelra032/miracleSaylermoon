package com.donzzul.spring.user.service;

import java.util.HashMap;

import com.donzzul.spring.user.domain.User;

public interface UserService {
 
	public User loginUser(User user);
	public int checkLoginDup(HashMap<String, String> map);
	public int checkIdDup(String userId);
	public int checkPhoneDup(String userPhone);
	public int checkCardAvail(HashMap<String, String> map);
	public int checkCardDup(HashMap<String, String> map);
	public int checkNickDup(String userNick);
	public int checkPVerifyDup(String partnerVerify);
	public int checkEmailDup(String userEmail);
	public int insertDreamUser(User user);
	public int insertMzUser(User user);
	public int insertPartnerUser(User user);
	public int updateUser(User user);
	public int deleteUser(String userId);
	public String sendEmail(String userEmail, String userId, String pwCode);
	public String resetPw(String userId, String userEmail);
	public int checkFindIdDup(HashMap<String, String> map);
	public int checkFindPwDup(HashMap<String, String> map);
	public String findId(HashMap<String, String> map);
}
