package com.donzzul.spring.user.service.logic;

import java.util.ArrayList;
import java.util.HashMap;

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
	
	// 휴대폰번호 중복검사
	@Override
	public int checkPhoneDup(String userPhone) {
		return store.checkPhoneDup(userPhone);
	}

	// 카드번호 유효성 검사
	@Override
	public int checkCardAvail(HashMap<String, String> map) {
		return store.checkCardAvail(map);
	}
	
	// 카드번호 중복검사
	@Override
	public int checkCardDup(HashMap<String, String> map) {
		return store.checkCardDup(map);
	}
	
	// 닉네임 중복검사
	@Override
	public int checkNickDup(String userNick) {
		return store.checkNickDup(userNick);
	}

	// 사업자번호 중복검사
	@Override
	public int checkPVerifyDup(String partnerVerify) {
		return store.checkPVerifyDup(partnerVerify);
	}
	
	// 이메일 중복검사
	@Override
	public int checkEmailDup(String userEmail) {
		return store.checkEmailDup(userEmail);
	}

	//로그인
	@Override
	public User loginUser(User user) {
		User uOne = store.selectOneUser(user);
		return uOne;
	}
	
	// 로그인 중복검사
	@Override
	public int checkLoginDup(HashMap<String, String> map) {
		return store.checkLoginDup(map);
	}
	
	// 유저의 정보를 db에서 가져오면서 안의 값을 널로 바꾸고 싶다
	@Override
	public User selectOneUserByNo(int userNo) {
		User user = store.selectOneUserByNo(userNo);
		return user;
	}
	
	@Override
	public int updateToNull(int userNo) {
		return store.updateToNull(userNo);
	}

	// 일반회원정보수정
	@Override
	public int updateMzUser(User user) {
		return store.updateMzUser(user);
	}

	// 사업자회원정보수정
	@Override
	public int updatePartnerUser(User user) {
		return store.updatePartnerUser(user);
	}
	
	//회원탈퇴
	@Override
	public int deleteUser(int userNo) {
		return store.deleteUser(userNo);
	}

	//회원탈퇴요청(사업자)
	@Override
	public int deleteRequestUser(int userNo) {
		return store.deleteRequestUser(userNo);
	}
	
	// 비밀번호 확인
	@Override
	public int checkPwDup(HashMap<String, String> map) {
		return store.checkPwDup(map);
	}


	//아이디 찾기 중복검사
	@Override
	public int checkFindIdDup(HashMap<String, String> map) {
		return store.checkFindIdDup(map);
	}

	// 아이디찾기
	@Override
	public String findId(HashMap<String, String> map) {
		return store.findId(map);
	}

	// 비밀번호 찾기 중복검사
	@Override
	public int checkFindPwDup(HashMap<String, String> map) {
		return store.checkFindPwDup(map);
	}
	
	// 새 비밀번호 입력
	@Override
	public int resetPw(HashMap<String, String> map) {
		return store.resetPw(map);
	}

	// 이메일 중복검사 (나 빼고)
	@Override
	public int checkEmailDupNotMe(User user) {
		return store.checkEmailDupNotMe(user);
	}

	// 연락처 중복검사 (나 빼고)
	@Override
	public int checkPhoneDupNotMe(User user) {
		return store.checkPhoneDupNotMe(user);
	}
	
	// 모든 유저 출력하기(관리자페이지)
	@Override
	public ArrayList<User> selectAllUserList() {
		return store.selectAllUserList();
	}

	


	

	


}
