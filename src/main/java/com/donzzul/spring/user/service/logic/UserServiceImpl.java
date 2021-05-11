package com.donzzul.spring.user.service.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donzzul.spring.user.domain.User;
import com.donzzul.spring.user.service.UserService;
import com.donzzul.spring.user.store.UserStore;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserStore store;

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
	public String sendEmail(String userEmail, String userId, String pwCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String resetPw(String userId, String userEmail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findId(String userName, String userEmail) {
		// TODO Auto-generated method stub
		return null;
	}

}
