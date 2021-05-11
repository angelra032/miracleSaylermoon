package com.donzzul.spring.user.store.logic;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.donzzul.spring.user.domain.User;
import com.donzzul.spring.user.store.UserStore;

@Repository
public class UserStoreLogic implements UserStore {

//	@Autowired
//	private SqlSessionTemplate sqlSession;
	
	@Override
	public User selectOneUser(User user) {
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
 
}
