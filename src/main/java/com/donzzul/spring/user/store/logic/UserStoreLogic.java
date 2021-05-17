package com.donzzul.spring.user.store.logic;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.donzzul.spring.user.domain.User;
import com.donzzul.spring.user.store.UserStore;

@Repository
public class UserStoreLogic implements UserStore {

	@Autowired
	private SqlSessionTemplate sqlSession;

	// 꿈나무 회원등록
	@Override
	public int insertDreamUser(User user) {
		return sqlSession.insert("userMapper.insertDreamUser", user);
	}

	// 일반회원등록
	@Override
	public int insertMzUser(User user) {
		return sqlSession.insert("userMapper.insertMzUser", user);
	}
	
	//사업자 회원등록
	@Override
	public int insertPartnerUser(User user) {
		return sqlSession.insert("userMapper.insertPartnerUser", user);
	}
	
	// 아이디 중복검사
	@Override
	public int checkIdDup(String userId) {
		return sqlSession.selectOne("userMapper.checkIdDup", userId);
	}
	
	//로그인
	@Override
	public User selectOneUser(User user) {
		return sqlSession.selectOne("userMapper.selectOneUser", user);
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
