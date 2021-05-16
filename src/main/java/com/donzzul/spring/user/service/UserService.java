package com.donzzul.spring.user.service;

import com.donzzul.spring.user.domain.User;

public interface UserService {
 
	public User loginUser(User user);
	public int checkIdDup(String userId);
	public int insertDreamUser(User user);
	public int insertMzUser(User user);
	public int insertPartnerUser(User user);
	public int updateUser(User user);
	public int deleteUser(String userId);
	public String sendEmail(String userEmail, String userId, String pwCode);
	public String resetPw(String userId, String userEmail);
	public String findId(String userName, String userEmail);
}
