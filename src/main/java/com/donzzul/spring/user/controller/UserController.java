package com.donzzul.spring.user.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.donzzul.spring.shop.domain.Shop;
import com.donzzul.spring.user.domain.User;
import com.donzzul.spring.user.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService service;
	
	@Autowired
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private final static String TAG = "KakaoController : ";
	
	// 회원가입폼
	@RequestMapping(value = "enrollView.dz", method = RequestMethod.GET) 
	public String enrollView() {
		return "user/userJoin";
	}
	
	// 꿈나무회원등록
	@RequestMapping(value = "dreamRegister.dz", method = RequestMethod.POST) 
	public String dreamUserRegister(@ModelAttribute User user, Model model) {
		int result = service.insertDreamUser(user);
		if (result>0) {
			return "redirect:index.jsp";
		}else {
			model.addAttribute("msg", "회원가입실패!");
			return "common/errorPage";
		}
	}
	
	// 일반회원등록
	@RequestMapping(value = "mzRegister.dz", method = RequestMethod.POST) 
	public String mzUserRegister(@ModelAttribute User user, Model model) {
		int result = service.insertMzUser(user);
		if (result>0) {
			return "redirect:index.jsp";
		}else {
			model.addAttribute("msg", "회원가입실패!");
			return "common/errorPage";
		}
	}
	
	// 사업자회원등록
	@RequestMapping(value = "partnerRegister.dz", method = RequestMethod.POST) 
	public String partnerUserRegister(@ModelAttribute User user, Model model) {
		int result = service.insertPartnerUser(user);
		if (result>0) {
			return "redirect:index.jsp";
		}else {
			model.addAttribute("msg", "회원가입실패!");
			return "common/errorPage";
		}
	}
	
	//아이디 중복 검사
	@ResponseBody //반드시 적어줘야함 jsp에서 매개변수로 받아서쓰게 하려면 필요
	@RequestMapping(value = "dupId.dz", method = RequestMethod.GET)
	public String idDuplicateCheck(@RequestParam("userId") String userId) {
		return service.checkIdDup(userId)+""; //@ResponseBody를 적으면 /WEB-INF/views어쩌구 안붙여주고 바로 보낼수 있다. printWriter를 안써도 됨
	}

	//연락처 중복 검사
	@ResponseBody 
	@RequestMapping(value = "dupPhone.dz", method = RequestMethod.GET)
	public String phoneDuplicateCheck(@RequestParam("userPhone") String userPhone) {
		return service.checkPhoneDup(userPhone)+"";
	}
	
	//카드번호 유효성 검사
	@ResponseBody 
	@RequestMapping(value = "dupCard.dz", method = RequestMethod.GET)
	public String cardDuplicateCheck(@RequestParam("userName") String userName, @RequestParam("dreamCardno") String dreamCardno) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("1", userName);
		map.put("2", dreamCardno);
		int availableCheck = service.checkCardAvail(map);
		int duplicateCheck = service.checkCardDup(map);
		if(duplicateCheck != 0){
			return 1+"";
		}else if (availableCheck == 0) {
			return 0+"";
		}else if(availableCheck != 0 && duplicateCheck == 0){
			return 2+"";
		}else {
			return 3+"";
		}
	}
	
	//닉네임 중복 검사
	@ResponseBody 
	@RequestMapping(value = "dupNick.dz", method = RequestMethod.GET)
	public String nickDuplicateCheck(@RequestParam("userNick") String userNick) {
		return service.checkNickDup(userNick)+"";
	}

	//사업자번호 중복 검사
	@ResponseBody 
	@RequestMapping(value = "dupPveri.dz", method = RequestMethod.GET)
	public String pVerifyDuplicateCheck(@RequestParam("partnerVerify") String partnerVerify) {
		return service.checkPVerifyDup(partnerVerify)+"";
	}
		
	//이메일 중복 검사
	@ResponseBody 
	@RequestMapping(value = "dupEmail.dz", method = RequestMethod.GET)
	public String emailDuplicateCheck(@RequestParam("userEmail") String userEmail) {
		return service.checkEmailDup(userEmail)+"";
	}
	
	//로그인 뷰
	@RequestMapping(value = "loginView.dz", method = RequestMethod.GET) 
	public String loginView() {
		return "user/userLogin";
	}
	
	//로그인 유효성 검사
	@ResponseBody 
	@RequestMapping(value = "dupLogin.dz", method = RequestMethod.GET)
	public String loginDuplicateCheck(@RequestParam("userId") String userId, @RequestParam("userPw") String userPw) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("userPw", userPw);
		int duplicateCheck = service.checkLoginDup(map);
		if(duplicateCheck == 0){
			return 1+"";
		}else {
			return 2+"";
		}
	}
	
	//로그인
	@RequestMapping(value = "login.dz", method = RequestMethod.POST)
	public String userLogin(HttpServletRequest request, @ModelAttribute User user, Model model) {
		User uOne = new User(user.getUserId(), user.getUserPw());
		User loginUser = service.loginUser(uOne);
		if (loginUser != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", loginUser);
			return "redirect:index.jsp";
		}else {
			model.addAttribute("msg", "로그인 실패");
			return "common/errorPage";
		}
	}
	
	// 카카오 로그인
	@RequestMapping(value = "kakaologin.dz", method = RequestMethod.GET)
	public String kakaoLogin(HttpServletRequest request, 
							@RequestParam("kakaoId") String kakaoId, 
							@RequestParam("kakaoNickname") String kakaoNickname, Model model) {
		if(kakaoId != null) {
			HttpSession session = request.getSession();
			session.setAttribute("kakaoId", kakaoId);
			session.setAttribute("kakaoNickname", kakaoNickname);
			return "redirect:index.jsp";
		}else {
			model.addAttribute("msg", "로그인 실패");
			return "common/errorPage";
		}
	}
	
	// 구글 로그인
    @RequestMapping(value = "googlelogin.dz", method = RequestMethod.GET)
    public String googleLogin(HttpServletRequest request, 
                      @RequestParam("googleId") String googleId, 
                      @RequestParam("googleName") String googleName, Model model) {
       if(googleId != null) {
          HttpSession session = request.getSession();
          session.setAttribute("googleId", googleId);
          session.setAttribute("googleName", googleName);
          return "redirect:index.jsp";
       }else {
          model.addAttribute("msg", "구글로그인 실패");
          return "common/errorPage";
       }
    }
	
	//로그아웃
	@RequestMapping(value = "logout.dz", method = RequestMethod.GET) 
	public String userLogout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:index.jsp";
	}
	
	//회원정보조회@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@RequestMapping(value = "myINfo.dz", method = RequestMethod.GET)
	public String myINfoView(@RequestParam("userNo") int userNo , Model model) {
		User user = service.selectOneUserByNo(userNo);
		if( user!=null ) {
			model.addAttribute("user", user);
			return "user/userMyInfo";
			
		}else {
			model.addAttribute("msg", "정보조회 실패");
			return "common/errorPage";
		}
	}
	
	//연락처 중복 검사(내꺼 빼고)
		@ResponseBody 
		@RequestMapping(value = "dupPhoneNotMe.dz", method = RequestMethod.GET)
		public String phoneDuplicateCheckNotMe(@RequestParam("userPhone") String userPhone, @RequestParam("userNo") int userNo) {
			User user = new User();
			user.setUserNo(userNo);
			user.setUserPhone(userPhone);
			return service.checkPhoneDupNotMe(user)+"";
		}
	
	//이메일 중복 검사(내꺼빼고)
	@ResponseBody 
	@RequestMapping(value = "dupEmailNotMe.dz", method = RequestMethod.GET)
	public String emailDuplicateCheckNotMe(@RequestParam("userEmail") String userEmail, @RequestParam("userNo") int userNo ) {
		User user = new User();
		user.setUserNo(userNo);
		user.setUserEmail(userEmail);
		return service.checkEmailDupNotMe(user)+"";
	}

	// 일반회원정보수정
	@RequestMapping(value = "mzModify.dz", method = RequestMethod.POST)
	public String mzUserUpdate(@ModelAttribute User user,
								Model model,
								HttpServletRequest request) {
		HttpSession session = request.getSession();
		int result = service.updateMzUser(user);
		if (result > 0) {
			session.setAttribute("loginUser", user);
			return "redirect:mzMyPage.dz";
		}else {
			return "common/errorPage";
		}
	}
	
	// 사업자회원정보수정
		@RequestMapping(value = "partnerModify.dz", method = RequestMethod.POST)
		public String partnerUserUpdate(@ModelAttribute User user,
									Model model,
									HttpServletRequest request) {
			HttpSession session = request.getSession();
			int result = service.updatePartnerUser(user);
			if (result > 0) {
				session.setAttribute("loginUser", user);
				return "partnerMyPage/partnerMyPage";
			}else {
				return "common/errorPage";
			}
		}
	
	//회원탈퇴전 비밀번호 입력뷰
		@RequestMapping(value = "userWritePwView.dz", method = RequestMethod.GET)
		public String userWritePwView() {
			return "user/userWritePw";
		}
		
	// 비밀번호 유효성 검사
		@ResponseBody 
		@RequestMapping(value = "dupPw.dz", method = RequestMethod.GET)
		public String pwDuplicateCheck(@RequestParam("userNo") String userNo, @RequestParam("userPw") String userPw) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("userNo", userNo);
			map.put("userPw", userPw);
			int duplicateCheck = service.checkPwDup(map);
			if(duplicateCheck == 0){
				return 0+"";
			}else {
				return 1+"";
			}
		}
		
	//회원탈퇴
	@RequestMapping(value = "userDelete.dz", method = RequestMethod.GET)
	public String userDelete(@RequestParam("userNo") int userNo, Model model) {
		// 회원탈퇴를 하고 나서 세션파괴를 하지 않으면 
		// 로그인한 상태가 유지되므로 세션파괴를 해줘야함.
		int result = service.deleteUser(userNo);
		if (result>0) {
			return "redirect:logout.dz";
		}else {
			model.addAttribute("msg", "회원탈퇴 실패");
			return "common/errorPage";
		}
	}
	
	//회원탈퇴요청(사업자)
		@RequestMapping(value = "userDeleteRequest.dz", method = RequestMethod.GET)
		public String userDeleteRequest(@RequestParam("userNo") int userNo, Model model) {
			// 회원탈퇴를 하고 나서 세션파괴를 하지 않으면 
			// 로그인한 상태가 유지되므로 세션파괴를 해줘야함.
			int result = service.deleteRequestUser(userNo);
			if (result>0) {
				return "partnerMyPage/partnerMyPage";
			}else {
				model.addAttribute("msg", "탈퇴요청 실패");
				return "common/errorPage";
			}
		}
	
	//아이디 찾기 폼
	@RequestMapping(value = "findIdView.dz", method = RequestMethod.GET)
	public String findIdView() {
		return "user/userFindId";
	}
	
	//아이디 찾기
	@RequestMapping(value = "findId.dz", method = RequestMethod.POST)
	public String findId(@RequestParam("userName") String userName, @RequestParam("userEmail") String userEmail, Model model) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userName", userName);
		map.put("userEmail", userEmail);
		String userId =  service.findId(map);
		if(userId != null) {
			model.addAttribute("userName", userName);
			model.addAttribute("userId", userId);
			return "user/userFindIdResult";
		}else {
			model.addAttribute("msg", "아이디찾기 실패");
			return "common/errorPage";
		}
	}
	
	//아이디찾기 유효성 검사
	@ResponseBody 
	@RequestMapping(value = "dupFindId.dz", method = RequestMethod.GET)
	public String findIdDuplicateCheck(@RequestParam("userName") String userName, @RequestParam("userEmail") String userEmail) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userName", userName);
		map.put("userEmail", userEmail);
		int duplicateCheck = service.checkFindIdDup(map);
		if(duplicateCheck == 0){
			return 0+"";
		}else {
			return 1+"";
		}
	}
	
	//비밀번호찾기 유효성 검사
	@ResponseBody 
	@RequestMapping(value = "dupFindPw.dz", method = RequestMethod.GET)
	public String findPwDuplicateCheck(@RequestParam("userId") String userId, @RequestParam("userEmail") String userEmail) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("userEmail", userEmail);
		int duplicateCheck = service.checkFindPwDup(map);
		if(duplicateCheck == 0){
			return 0+"";
		}else {
			return 1+"";
		}
	}
	
	//비밀번호 찾기 폼
	@RequestMapping(value = "findPwView.dz", method = RequestMethod.GET)
	public String findPwView() {
		return "user/userFindPw";
	}
	
	
	//메일 보내기
	@RequestMapping(value = "sendEmail.dz", method = RequestMethod.POST)
	public String sendEmail(@RequestParam("userEmail") String userEmail, @RequestParam("userId") String userId, Model model) {
		
		Properties props = System.getProperties();
		props.put("mail.smtp.user", "pminae11@gmail.com"); // 서버 아이디만 쓰기(발신인 이메일)
		props.put("mail.smtp.host", "smtp.gmail.com"); // 구글 SMTP
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.debug", "true"); //////////////
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		
		// 메일 인증
		Authenticator auth = new MyAuthentication();
		// session 생성 및 MimeMessage 생성
		Session session = Session.getDefaultInstance(props, auth); // getInst...
//		session.setDebug(true); // 메일 전송 시 상세상황 콘솔에 출력 ///////////
		MimeMessage msg = new MimeMessage(session);
		int randomCode = 0;
		try { 
			// 편지 보낸 시간
			msg.setSentDate(new Date());
			
			InternetAddress from = new InternetAddress("pminae11@gmail.com"); //보내는 사람(메일주소?)
			
			// 이메일 발신자
			msg.setFrom(from);
			
			// 이메일 수신자
			String email = userEmail; // 사용자가 입력한 이메일 받아오기
			InternetAddress to = new InternetAddress(email);
			msg.setRecipient(Message.RecipientType.TO, to);
			
			// 이메일 제목
			msg.setSubject("[돈쭐] 인증코드 메일입니다.", "UTF-8");
			
			// 이메일 내용
			randomCode = (int)Math.floor((Math.random()*(99999-10000+1)))+10000;
						// 5자리 숫자로 이루어진 인증번호 랜덤 생성
			//String code = request.getParameter("code-check");
			String content = "인증코드는 " + randomCode + " 입니다."; // 인증번호 값 받기
			msg.setText(content, "UTF-8");
			
			// 이메일 헤더
			msg.setHeader("content-Type", "text/html");
			
			// 메일 보내기
			Transport.send(msg);
			System.out.println("보냄!!");
		} catch (AddressException addr_e) {
			addr_e.printStackTrace();
		}catch (MessagingException msg_e) {
			msg_e.printStackTrace();
		}
		model.addAttribute("userId", userId);
		model.addAttribute("userEmail", userEmail);
		model.addAttribute("randomCode", randomCode);
		return "user/userFindPw";
	}

	// 아이디 패스워드 인증받기 함수
	class MyAuthentication extends Authenticator{
		PasswordAuthentication pa;
		public MyAuthentication() {
			String id = "pminae11"; // 구글 ID(발신인 이메일-도메인 제외)
			String pw = "donjjul1234"; // 구글 비밀번호(발신인 이메일 비번)
			// ID와 비밀번호 입력
			pa = new PasswordAuthentication(id, pw);
		}
		// 시스템에서 사용하는 인증정보
		public PasswordAuthentication getPasswordAuthentication() {
			return pa;
		}
	}
	
	
	// 비밀번호 재설정 폼
	@RequestMapping(value = "resetPwView.dz", method = RequestMethod.POST)
	public String resetPwView(@RequestParam("userEmail") String userEmail, @RequestParam("userId") String userId, Model model) {
		model.addAttribute("userId", userId);
		model.addAttribute("userEmail", userEmail);
		return "/user/userResetPw";
	}
	
	// 비밀번호 재설정
	@RequestMapping(value = "resetPw.dz", method = RequestMethod.POST)
	public String resetPw(@RequestParam("userId") String userId,
						@RequestParam("userEmail") String userEmail,
						@RequestParam("userPw") String userPw,
						Model model) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("userEmail", userEmail);
		map.put("userPw", userPw);
		int result =  service.resetPw(map);
		if(result>0) {
			return "user/userLogin";
		}else {
			model.addAttribute("msg", "비밀번호 등록 실패");
			return "common/errorPage";
		}
	}
	
}