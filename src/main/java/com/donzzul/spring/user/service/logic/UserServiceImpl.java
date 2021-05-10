package com.donzzul.spring.user.service.logic;

import com.donzzul.spring.user.domain.User;
import com.donzzul.spring.user.service.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public User loginUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int checkIdDup(String userId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertUser(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteUser(String userId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String findIdView(String userName, String userEmail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String sendEmail(String userEmail, String userId, String pwCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String resetPw(String userId, String userEmail) {
		// TODO Auto-generated method stub
		return null;
	}

}
