package com.donzzul.spring.user.store.logic;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.mzreview.domain.MzReview;
import com.donzzul.spring.user.domain.CustomUserDetails;
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
	
	// 회원수정필드 비우기
	@Override
	public int updateToNull(int userNo) {
		return sqlSession.update("userMapper.updateToNull", userNo);
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

	// 이메일 중복검사(나 빼고)
	@Override
	public int checkEmailDupNotMe(User user) {
		return sqlSession.selectOne("userMapper.checkEmailDupNotMe", user);
	}

	// 폰번호 중복검사(나빼고)
	@Override
	public int checkPhoneDupNotMe(User user) {
		return sqlSession.selectOne("userMapper.checkPhoneDupNotMe", user);
	}
	

	// 3명 유저 출력하기(관리자페이지)
	@Override
	public ArrayList<User> selectUserListThree() {
		return (ArrayList)sqlSession.selectList("userMapper.selectThreeUser");
	}
	
	// 페이징 작업(어드민)
	@Override
	public int getListCount(HashMap<String, String> pageType) {
		return sqlSession.selectOne("userMapper.selectListCount", pageType);
	}
	
	// 페이징 작업(어드민)
	@Override
	public ArrayList<User> selectAllUserList(PageInfo pi, HashMap<String, String> userType) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return (ArrayList)sqlSession.selectList("userMapper.selectAllListPage", userType, rowBounds);
	}

	@Override
	public User getUsersByID(String userId) {
		return sqlSession.selectOne("userMapper.getUsersByID", userId);
	}

	@Override
	public User getUsersByNo(String userNo) {
		return sqlSession.selectOne("userMapper.getUsersByNo", userNo);
	}

	@Override
	public int countSocialUser(String socialId) {
		return sqlSession.selectOne("userMapper.countSocialUser", socialId);
	}

	@Override
	public int insertSocialUser(HashMap<String, String> map) {
		return sqlSession.insert("userMapper.insertSocialUser", map);
	}

	// 맛집 후기 작성 후 포인트 추가 
	@Override
	public int updatePoint(MzReview mzReview) {
		return sqlSession.update("userMapper.addReviewPoint", mzReview);
		
	}

}
