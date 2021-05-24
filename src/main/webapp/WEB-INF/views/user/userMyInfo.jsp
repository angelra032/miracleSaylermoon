<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/user/myinfo.css"> 
<!-- <link href="/static/bootstrap.min.css" rel="stylesheet"> -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<title>내 정보 수정</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menubar.jsp"></jsp:include>
	<main>
		<div class="header-background-area">
        	<img src="/resources/images/mapListMain.png" alt="뒷배경이미지">
   		</div>	
		<div id="main-title">내 정보 수정</div>
		<div class="frame">
			<div class="tabcontent">
				<div class="content-body">
					<c:if test="${ !empty sessionScope.loginUser && sessionScope.loginUser.userType == '1'}">
						<div id="dreamEnrollView" class="tab-content current">
							<form action="mzModify.dz" method="post"> <!-- 일반회원이랑 수정양식 같아서 통일 -->
								<div class="form-head">
									아이디&nbsp;
									<span class="required">*</span>&nbsp;&nbsp;
								</div>
								<div class="form-body">
									<input name="userId" class="form-elem idelem" type="text" maxlength="20" value="${ loginUser.userId }" readonly>
								</div>
								
								<div class="form-head form-head2">
									비밀번호&nbsp;
									<span class="required">*</span>&nbsp;&nbsp;
									<div class="form-noti pwnoti password_noti_0">비밀번호를 입력해 주세요.</div>
									<div class="form-noti pwnoti password_noti_1">영문소문자, 숫자 포함 6~20자</div>
									<div class="form-noti pwnoti password_noti_2">비밀번호가 일치하지 않습니다.</div>
								</div>
								<div class="form-body">
									<input name="userPw" class="form-elem pwdelem" type="password" maxlength="20" placeholder="영문, 숫자 또는 혼합 6~20자">
									<input name="re_pwd" class="form-elem pwdelem re_pwd" type="password" maxlength="20" placeholder="비밀번호 재입력">
								</div>
								
								<div class="form-head form-head2">
									이름&nbsp;
									<span class="required">*</span>&nbsp;&nbsp;
								</div>
								<div class="form-body">
									<input name="userName" class="form-elem nameelem" type="text" maxlength="20" value="${ loginUser.userName }" readonly>
								</div>
								
								<div class="form-head form-head2">
									카드번호&nbsp;
									<span class="required">*</span>&nbsp;&nbsp;
								</div>
								<div class="form-body">
									<input name="dreamCardno" id="cardelem" class="form-elem cardelem" type="text" maxlength="20" value="${ loginUser.dreamCardno }" readonly>
								</div>
								
								<div class="form-head form-head2">
									닉네임&nbsp;
									<span class="required">*</span>&nbsp;&nbsp;
								</div>
								<div class="form-body">
									<input name="userNick" class="form-elem nickelem" type="text" maxlength="20" value="${ loginUser.userNick }" readonly>
								</div>
								
								<div class="form-head form-head2">
									휴대폰번호&nbsp;
									<span class="required">*</span>&nbsp;&nbsp;
									<div class="form-noti phonenoti phone_noti_0">휴대폰번호를 입력해 주세요.</div>
									<div class="form-noti phonenoti phone_noti_2">이미 등록된 번호입니다.</div>
								</div>
								<div class="form-body">
									<input name="userPhone" id="phoneelem" class="form-elem phoneelem" type="text" maxlength="13" placeholder="숫자만 입력" value="${ loginUser.userPhone }">
								</div>
								
								<div class="form-head form-head2">
									이메일&nbsp;
									<span class="required">*</span>&nbsp;&nbsp;
									<div class="form-noti emailnoti email_noti_0">이메일을 입력해 주세요.</div>
									<div class="form-noti emailnoti email_noti_1">이메일 형식이 올바르지 않습니다.</div>
									<div class="form-noti emailnoti email_noti_2">이미 사용중인 이메일입니다.</div>
								</div>
								<div class="form-body">
									<input name="userEmail" class="form-elem emailelem" type="text" maxlength="50" placeholder="아이디@도메인으로 입력" value="${ loginUser.userEmail }">
								</div>
								<button class="submit-btn" type="submit">수정하기</button>
							</form>
						</div>
					</c:if>
					<!-- 일반회원 -->
					<c:if test="${ !empty sessionScope.loginUser && sessionScope.loginUser.userType == '2'}">
						<div id="mzEnrollView" class="tab-content">
							<form action="mzModify.dz" method="post">
								<div class="form-head">
									아이디&nbsp;
									<span class="required">*</span>&nbsp;&nbsp;
								</div>
								<div class="form-body">
									<input name="userId" class="form-elem idelem" type="text" maxlength="20" value="${ loginUser.userId }" readonly>
								</div>
								
								<div class="form-head form-head2">
									비밀번호&nbsp;
									<span class="required">*</span>&nbsp;&nbsp;
									<div class="form-noti pwnoti password_noti_0">비밀번호를 입력해 주세요.</div>
									<div class="form-noti pwnoti password_noti_1">영문소문자, 숫자 포함 6~20자</div>
									<div class="form-noti pwnoti password_noti_2">비밀번호가 일치하지 않습니다.</div>
								</div>
								<div class="form-body">
									<input name="userPw" class="form-elem pwdelem" type="password" maxlength="20" placeholder="영문, 숫자 또는 혼합 6~20자">
									<input name="re_pwd" class="form-elem pwdelem re_pwd" type="password" maxlength="20" placeholder="비밀번호 재입력">
								</div>
								
								<div class="form-head form-head2">
									이름&nbsp;
									<span class="required">*</span>&nbsp;&nbsp;
								</div>
								<div class="form-body">
									<input name="userName" class="form-elem nameelem" type="text" maxlength="20" value="${ loginUser.userName }" readonly>
								</div>
								
								<div class="form-head form-head2">
									닉네임&nbsp;
									<span class="required">*</span>&nbsp;&nbsp;
								</div>
								<div class="form-body">
									<input name="userNick" class="form-elem nickelem" type="text" maxlength="20" value="${ loginUser.userNick }" readonly>
								</div>
								
								<div class="form-head form-head2">
									휴대폰번호&nbsp;
									<span class="required">*</span>&nbsp;&nbsp;
									<div class="form-noti phonenoti phone_noti_0">휴대폰번호를 입력해 주세요.</div>
									<div class="form-noti phonenoti phone_noti_2">이미 등록된 번호입니다.</div>
								</div>
								<div class="form-body">
									<input name="userPhone" id="mzphoneelem" class="form-elem phoneelem" type="text" maxlength="13" placeholder="숫자만 입력" value="${ loginUser.userPhone }">
								</div>
								
								<div class="form-head form-head2">
									이메일&nbsp;
									<span class="required">*</span>&nbsp;&nbsp;
									<div class="form-noti emailnoti email_noti_0">이메일을 입력해 주세요.</div>
									<div class="form-noti emailnoti email_noti_1">이메일 형식이 올바르지 않습니다.</div>
									<div class="form-noti emailnoti email_noti_2">이미 사용중인 이메일입니다.</div>
								</div>
								<div class="form-body">
									<input name="userEmail" class="form-elem emailelem" type="text" maxlength="50" placeholder="아이디@도메인으로 입력" value="${ loginUser.userEmail }">
								</div>
								<button class="submit-btn" type="submit">수정하기</button>
								<input type="hidden" name="userNo" value="${ loginUser.userNo }">
							</form>
						</div>
					</c:if>
					<!-- 사업자회원 -->
					<c:if test="${ !empty sessionScope.loginUser && sessionScope.loginUser.userType == '3'}">
						<div id="partnerEnrollView" class="tab-content">
							<form action="partnerModify.dz" method="post">
								<div class="form-head">
									아이디&nbsp;
									<span class="required">*</span>&nbsp;&nbsp;
								</div>
								<div class="form-body">
									<input name="userId" class="form-elem idelem" type="text" maxlength="20" value="${ loginUser.userId }" readonly>
								</div>
								
								<div class="form-head form-head2">
									비밀번호&nbsp;
									<span class="required">*</span>&nbsp;&nbsp;
									<div class="form-noti pwnoti password_noti_0">비밀번호를 입력해 주세요.</div>
									<div class="form-noti pwnoti password_noti_1">영문소문자, 숫자 포함 6~20자</div>
									<div class="form-noti pwnoti password_noti_2">비밀번호가 일치하지 않습니다.</div>
								</div>
								<div class="form-body">
									<input name="userPw" class="form-elem pwdelem" type="password" maxlength="20" placeholder="영문, 숫자 또는 혼합 6~20자">
									<input name="re_pwd" class="form-elem pwdelem re_pwd" type="password" maxlength="20" placeholder="비밀번호 재입력">
								</div>
								
								<div class="form-head form-head2">
									사업자명&nbsp;
									<span class="required">*</span>&nbsp;&nbsp;
								</div>
								<div class="form-body">
									<input name="userName" class="form-elem nameelem" type="text" maxlength="20" value="${ loginUser.userName }" readonly>
								</div>
								
								<div class="form-head form-head2">
									간이사업자명&nbsp;
									<span class="required">*</span>&nbsp;&nbsp;
									<div class="form-noti simplenamenoti simplename_noti_0">간이사업자명을 입력해 주세요.</div>
									<div class="form-noti simplenamenoti simplename_noti_1">한글 2자 이상 입력</div>
								</div>
								<div class="form-body">
									<input name="partnerName" class="form-elem simplenameelem" type="text" maxlength="20" placeholder="한글 2자 이상 입력" value="${ loginUser.partnerName }">
								</div>
								
								<div class="form-head form-head2">
									연락처&nbsp;
									<span class="required">*</span>&nbsp;&nbsp;
									<div class="form-noti phonenoti phone_noti_0">연락처를 입력해 주세요.</div>
									<div class="form-noti phonenoti phone_noti_2">이미 등록된 번호입니다.</div>
								</div>
								<div class="form-body">
									<input name="userPhone" id="pphoneelem" class="form-elem pphoneelem" type="tel" maxlength="12" placeholder="숫자만 입력" value="${ loginUser.userPhone }">
								</div>
								
								<div class="form-head form-head2">
									사업자등록번호&nbsp;
									<span class="required">*</span>&nbsp;&nbsp;
								</div>
								<div class="form-body">
									<input name="partnerVerify" id="pverielem" class="form-elem pverielem" type="text" maxlength="12" value="${ loginUser.partnerVerify }" readonly>
								</div>
								
								<div class="form-head form-head2">
									이메일&nbsp;
									<span class="required">*</span>&nbsp;&nbsp;
									<div class="form-noti emailnoti email_noti_0">이메일을 입력해 주세요.</div>
									<div class="form-noti emailnoti email_noti_1">이메일 형식이 올바르지 않습니다.</div>
									<div class="form-noti emailnoti email_noti_2">이미 사용중인 이메일입니다.</div>
								</div>
								<div class="form-body">
									<input name="userEmail" class="form-elem emailelem" type="text" maxlength="50" placeholder="아이디@도메인으로 입력" value="${ loginUser.userEmail }">
								</div>
								<button class="submit-btn" type="submit">수정하기</button>
							</form>
						</div>
				</c:if>
				</div>
			</div>
		</div>
	</main>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
<script>
	$(function() {
			
			///비밀번호 찾기 유효성검사@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			var userPwd = $(".pwdelem");
			var regExpPw = /^[a-z0-9][a-z0-9]{5,11}$/;
			userPwd.keyup(function() {
				$('.pwnoti').css('display', 'none');
				if (!regExpPw.test(userPwd.val())) {
					$('.password_noti_1').css('color', '#ff5442');
					$('.password_noti_1').css('display', 'block');
					$('.pwdelem').css('border', '1px solid #ff5442');
				}else{
					$('.pwnoti').css('display', 'none');
					$('.pwdelem').css('border', '0');
				}
			});
			$(".re_pwd").keyup(function() {
				$('.pwnoti').css('display', 'none');
				if($(".re_pwd").val()!=userPwd.val()){
					$('.pwnoti').css('display', 'none');
					$('.password_noti_2').css('color', '#ff5442');
					$('.password_noti_2').css('display', 'block');
					$('.re_pwd').css('border', '1px solid #ff5442');
				}else{
					$('.pwnoti').css('display', 'none');
					$('.pwdelem').css('border', '0');
				}
			});
			/////비밀번호 입력해주세요
			userPwd.on("blur", function() {
				$('.pwnoti').css('display', 'none');
				if (userPwd.val() =="") {
					$('.password_noti_0').css('color', '#ff5442');
					$('.password_noti_0').css('display', 'block');
					$('.pwdelem').css('border', '1px solid #ff5442');
				}else if(!regExpPw.test(userPwd.val())){
					$('.pwnoti').css('display', 'none');
					$('.password_noti_1').css('color', '#ff5442');
					$('.password_noti_1').css('display', 'block');
					$('.pwdelem').css('border', '1px solid #ff5442');
				}else if($(".re_pwd").val()==""){
					$('.re_pwd').css('border', '1px solid #ff5442');
				}else if($(".re_pwd").val()!=userPwd.val()){
					$('.pwnoti').css('display', 'none');
					$('.password_noti_2').css('color', '#ff5442');
					$('.password_noti_2').css('display', 'block');
					$('.re_pwd').css('border', '1px solid #ff5442');
				}else{
					$('.pwnoti').css('display', 'none');
					$('.pwdelem').css('border', '0');
				}
			});
			
			
			//간이사업자명 영어허용@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			var userSimpleName = $(".simplenameelem");
			var regExpSimpleName = /^[가-힣,a-z,A-Z]{2,11}$/; //undefind를 소문자로 받아들인다
			userSimpleName.keyup(function() {
				$('.simplenamenoti').css('display', 'none');
				if (!regExpSimpleName.test(userSimpleName.val())) {
					$('.simplename_noti_1').css('color', '#ff5442');
					$('.simplename_noti_1').css('display', 'block');
					$('.simplenameelem').css('border', '1px solid #ff5442');
				}else{
					$('.simplenamenoti').css('display', 'none');
					$('.simplenameelem').css('border', '0');
				}
			});
			userSimpleName.on("blur", function() {
				$('.simplenamenoti').css('display', 'none');
				if (userSimpleName.val() =="") {
					$('.simplename_noti_0').css('color', '#ff5442');
					$('.simplename_noti_0').css('display', 'block');
					$('.simplenameelem').css('border', '1px solid #ff5442');
				}else if(!regExpSimpleName.test(userSimpleName.val())){
					$('.simplenamenoti').css('display', 'none');
					$('.simplename_noti_1').css('color', '#ff5442');
					$('.simplename_noti_1').css('display', 'block');
					$('.simplenameelem').css('border', '1px solid #ff5442');
				}else{
					$('.siplenamenoti').css('display', 'none');
					$('.simplenameelem').css('border', '0');
				}
			});
			
			///휴대폰번호@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			//자동 - 생성
			var autoHypenPhone = function(str){
			      str = str.replace(/[^0-9]/g, '');
			      var tmp = '';
			      if( str.length < 4){
			          return str;
			      }else if(str.length < 7){
			          tmp += str.substr(0, 3);
			          tmp += '-';
			          tmp += str.substr(3);
			          return tmp;
			      }else if(str.length < 11){
			          tmp += str.substr(0, 3);
			          tmp += '-';
			          tmp += str.substr(3, 3);
			          tmp += '-';
			          tmp += str.substr(6);
			          return tmp;
			      }else{              
			          tmp += str.substr(0, 3);
			          tmp += '-';
			          tmp += str.substr(3, 4);
			          tmp += '-';
			          tmp += str.substr(7);
			          return tmp;
			      }
			      return str;
			}
	
			// 중복(2) - 이미 등록된 번호입니다
			var userPhone = $(".phoneelem");
			$(".phoneelem").on("keyup", function() {
				console.log(userPhone.val());
				$(this).val(autoHypenPhone($(this).val()));  
				$('.phonenoti').css('display', 'none');
				$.ajax({
					url : "dupPhone.dz",
					data : { "userPhone" : userPhone.val() },
					success : function(result) {
						if(result != 0){
							$('.phone_noti_2').css('color', '#ff5442'); // 에러메시지:이미 등록된 휴대폰번호
							$('.phone_noti_2').css('display', 'block');
							$('.phoneelem').css('border', '1px solid #ff5442');
						}else {
							$('.phonenoti').css('display', 'none');
							$('.phoneelem').css('border', '0');
						}
					},
					error : function() {
						console.log("전송실패");
					}
				});
			});
			// 안적었을때(0) - 휴대폰번호를입력해주세요
			$(".phoneelem").on("blur", function() {
				if (userPhone.val() =="") {
					$('.phonenoti').css('display', 'none');
					$('.phone_noti_0').css('color', '#ff5442');
					$('.phone_noti_0').css('display', 'block');
					$('.phoneelem').css('border', '1px solid #ff5442');
				}
			});
			
			///사업자 연락처 -위치 수정@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			//자동 - 생성
			var autoHypenPPhone = function(str){
			      str = str.replace(/[^0-9]/g, '');
			      var tmp = '';
			      if( str.length < 3){
			          return str;
			      }else if(str.length < 6){
			          tmp += str.substr(0, 2);
			          tmp += '-';
			          tmp += str.substr(2);
			          return tmp;
			      }else if(str.length < 10){
			          tmp += str.substr(0, 2);
			          tmp += '-';
			          tmp += str.substr(2, 3);
			          tmp += '-';
			          tmp += str.substr(5);
			          return tmp;
			      }else{              
			          tmp += str.substr(0, 2);
			          tmp += '-';
			          tmp += str.substr(2, 4);
			          tmp += '-';
			          tmp += str.substr(6);
			          return tmp;
			      }
			  
			      return str;
			}

			var userPhone = $(".pphoneelem");
			$(".pphoneelem").on("keyup", function() {
				console.log(userPhone.val());
				$(this).val(autoHypenPPhone($(this).val())) ;  
			});
			// 안적었을때(0) - 휴대폰번호를입력해주세요
			$(".pphoneelem").on("blur", function() {
				if (userPhone.val() =="") {
					$('.phonenoti').css('display', 'none');
					$('.phone_noti_0').css('color', '#ff5442');
					$('.phone_noti_0').css('display', 'block');
					$('.pphoneelem').css('border', '1px solid #ff5442');
				}
			});
			
			// 이메일 유효성검사@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			var userEmail = $(".emailelem");
			var emailReg = /[a-zA-Z0-9]{4,12}\@/;
			$(".emailelem").keyup(function() {
				$('.emailnoti').css('display', 'none');
				$.ajax({
					url : "dupEmail.dz",
					data : { "userEmail" : userEmail.val() },
					success : function(result) {
						if(result != 0){
							$('.email_noti_2').css('color', '#ff5442');
							$('.email_noti_2').css('display', 'block');
							$('.emailelem').css('border', '1px solid #ff5442');
						}else if(!emailReg.test(userEmail.val())) {
							$('.emailnoti').css('display', 'none');
							$('.email_noti_1').css('color', '#ff5442');
							$('.email_noti_1').css('display', 'block');
							$('.emailelem').css('border', '1px solid #ff5442');
						}else {
							$('.emailnoti').css('display', 'none');
							$('.emailelem').css('border', '0');
						}
					},
					error : function() {
						console.log("전송실패");
					}
				});
			});
			// 안적었을때
			$(".emailelem").on("blur", function() {
				if (userEmail.val() =="") {
					$('.emailnoti').css('display', 'none');
					$('.email_noti_0').css('color', '#ff5442');
					$('.email_noti_0').css('display', 'block');
					$('.emailelem').css('border', '1px solid #ff5442');
				}
			});
			
			///////////////제출버튼@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			$('.submit-btn').click(function() { //제출버튼을 클릭하면
				var rtn = true; // 원래 트루였던 rtn이
				
				//비밀번호
				if (userPwd.val() =="") {
					alert("비밀번호를 입력해주세요."); // 빈칸인 비밀번호를 만나
					userPwd.focus();
					rtn = false; // false가 되고
				}else if (!regExpPw.test(userPwd.val())) {
					alert("비밀번호가 올바르지 않습니다. 다시 입력해주세요.");
					userPwd.focus();
					rtn = false;
				}else if($(".re_pwd").val()!=userPwd.val()) {
					alert("비밀번호가 올바르지 않습니다. 다시 입력해주세요.");
					userPwd.focus();
					rtn = false;
				}
				
				//이름
				else if (userName.val() =="") {
					alert("이름을 입력해주세요.");
					userName.focus();
					rtn = false;
				}else if(userName.length != 0 && !regExpName.test(userName.val())){
					alert("이름이 올바르지 않습니다. 다시 입력해주세요.");
					userName.focus();
					rtn = false;
				} 
				
				//간이사업자명/////////////////////
				else if (userSimpleName.val() =="") {
					alert("간이사업자명을 입력해주세요.");
					userSimpleName.focus();
					rtn = false;
				}else if(userSimpleName.length != 0 && !regExpSimpleName.test(userSimpleName.val())){
					alert("간이사업자명이 올바르지 않습니다. 다시 입력해주세요.");
					userSimpleName.focus();
					rtn = false;
				}
				
				///휴대폰번호
				$.ajax({
					url : "dupPhone.dz",
					data : { "userPhone" : userPhone.val() },
					async: false,
					success : function(result) {
						if (userPhone.val() =="") {
							alert("휴대폰번호를 입력해주세요.");
							userPhone.focus();
							rtn = false;
						}else if(result != 0){
							alert("이미 등록된 휴대폰번호입니다. 다시 입력해주세요.");
							userPhone.focus();
							rtn = false;
						}
					},
					error : function() {
						console.log("전송실패");
					}
				});
				
				// 이메일
				$.ajax({
					url : "dupEmail.dz",
					data : { "userEmail" : userEmail.val() },
					async: false,
					success : function(result) {
						if (userEmail.val() =="") {
							alert("이메일을 입력해주세요.");
							userEmail.focus();
							rtn = false;
						}else if(result != 0){
							alert("이미 등록된 이메일입니다. 다시 입력해주세요.");
							userEmail.focus();
							rtn = false;
						}else if(!emailReg.test(userEmail.val())) {
							alert("이메일이 올바르지 않습니다. 다시 입력해주세요.");
							userEmail.focus();
							rtn = false;
						}
					},
					error : function() {
						console.log("전송실패");
					}
				});
				
				return rtn; //리턴이 됩니다
			});
		});
			
			
</script>
</html>