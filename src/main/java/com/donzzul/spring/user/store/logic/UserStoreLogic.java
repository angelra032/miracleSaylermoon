package com.donzzul.spring.user.store.logic;

import java.util.HashMap;

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
	
	// 휴대폰번호 중복검사
	@Override
	public int checkPhoneDup(String userPhone) {
		return sqlSession.selectOne("userMapper.checkPhoneDup", userPhone);
	}

	// 카드번호 유효성 검사
	@Override
	public int checkCardAvail(HashMap<String, String> map) {
		return sqlSession.selectOne("userMapper.checkCardAvail", map);
	}
	
	// 카드번호 중복검사
	@Override
	public int checkCardDup(HashMap<String, String> map) {
		return sqlSession.selectOne("userMapper.checkCardDup", map);
	}
	
	// 닉네임 중복검사
	@Override
	public int checkNickDup(String userNick) {
		return sqlSession.selectOne("userMapper.checkNickDup", userNick);
	}
	
	// 사업자번호 중복검사
	@Override
	public int checkPVerifyDup(String partnerVerify) {
		return sqlSession.selectOne("userMapper.checkPVerifyDup", partnerVerify);
	}
	
	// 이메일
	@Override
	public int checkEmailDup(String userEmail) {
		return sqlSession.selectOne("userMapper.checkEmailDup", userEmail);
	}
	
	//로그인
	@Override
	public User selectOneUser(User user) {
		return sqlSession.selectOne("userMapper.selectOneUser", user);
	}
	
	// 로그인 중복검사
	@Override
	public int checkLoginDup(HashMap<String, String> map) {
		return sqlSession.selectOne("userMapper.checkLoginDup", map);
	}
	
	//회원정보 불러오기
	@Override
	public User selectOneUserByNo(int userNo) {
		return sqlSession.selectOne("userMapper.selectOneUserByNo", userNo);
	}
	
	// 일반회원정보 수정
	@Override
	public int updateMzUser(User user) {
		
		return sqlSession.update("userMapper.updateMzUser", user);
	}

	// 사업자회원정보수정
	@Override
	public int updatePartnerUser(User user) {
		return sqlSession.update("userMapper.updatePartnerUser", user);
	}
	
	//회원탈퇴
	@Override
	public int deleteUser(int userNo) {
		return sqlSession.delete("userMapper.deleteUser", userNo);
	}
	
	//회원탈퇴요청
	@Override
	public int deleteRequestUser(int userNo) {
		return sqlSession.update("userMapper.deleteRequestUser", userNo);
	}
	
	//비밀번호 검사
	@Override
	public int checkPwDup(HashMap<String, String> map) {
		return sqlSession.selectOne("userMapper.checkPwDup", map);
	}

	// 아이디 찾기 중복검사
	@Override
	public int checkFindIdDup(HashMap<String, String> map) {
		return sqlSession.selectOne("userMapper.checkFindIdDup", map);
	}

	// 아이디 찾기
	@Override
	public String findId(HashMap<String, String> map) {
		return sqlSession.selectOne("userMapper.findId", map);
	}

	// 비밀번호 찾기 중복검사
	@Override
	public int checkFindPwDup(HashMap<String, String> map) {
		return sqlSession.selectOne("userMapper.checkFindPwDup", map);
	}

	// 비밀번호 업데이트
	@Override
	public int resetPw(HashMap<String, String> map) {
		return sqlSession.update("userMapper.resetPw", map);
	}

	

	

	

	

	
	

	
	


}
