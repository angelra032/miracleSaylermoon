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



	//꿈나무 회원등록
	@Override
	public int insertDreamUser(User user) {
		int result = store.insertDreamUser(user);
		return result;
	}
	
	//일반회원등록
	@Override
	public int insertMzUser(User user) {
		int result = store.insertMzUser(user);
		return result;
	}
	
	//사업자회원등록
	@Override
	public int insertPartnerUser(User user) {
		int result = store.insertPartnerUser(user);
		return result;
	}

	// 아이디 중복검사
	@Override
	public int checkIdDup(String userId) {
		return store.checkIdDup(userId);
	}

	//로그인
	@Override
	public User loginUser(User user) {
		User uOne = store.selectOneUser(user);
		return uOne;
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
