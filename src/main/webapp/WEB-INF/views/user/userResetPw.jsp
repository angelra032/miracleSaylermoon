<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/user/resetpw.css"> 
<title>새 비밀번호 입력</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menubar.jsp"></jsp:include>
	<main>
		<div class="header-background-area">
        	<img src="/resources/images/mapListMain.png" alt="뒷배경이미지">
   		</div>	
   		<div id="main-title">새 비밀번호 입력</div>
		<div class="frame">
		<h4>아이디, 이메일을 입력하세요</h4>
			<div class="tabcontent">
				<div class="underline">
					<div class="content-body">
						<form action="resetPw.dz" method="post">
							<div class="form-head">
									비밀번호&nbsp;
									<span class="required">*</span>&nbsp;&nbsp;
									<div class="form-noti pwnoti password_noti_0">비밀번호를 입력해 주세요.</div>
									<div class="form-noti pwnoti password_noti_1">영문소문자, 숫자 포함 6~20자</div>
									<div class="form-noti pwnoti password_noti_2">비밀번호가 일치하지 않습니다.</div>
								</div>
								<div class="form-body">
									<input name="userPw" class="form-elem pwdelem" type="password" maxlength="20" placeholder="영문, 숫자 또는 혼합 6~20자">
								</div>
								<div class="form-head form-head2">
									비밀번호 재입력&nbsp;
									<span class="required">*</span>&nbsp;&nbsp;
								</div>
								<div class="form-body">
									<input name="re_pwd" class="form-elem pwdelem re_pwd" type="password" maxlength="20" placeholder="비밀번호 재입력">
								</div>
								<button class="submit-btn" type="submit">비밀번호 변경</button>
								<input type="hidden" name="userId" value=${ userId }>
								<input type="hidden" name="userEmail" value=${ userEmail }>
							</form>
						</div>
					</div>
				</div>
		</div>
	</main>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
<script>
	$(function() {
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
		$('.submit-btn').click(function() {
			var rtn = true;
			//비밀번호
			if (userPwd.val() =="") {
				alert("비밀번호를 입력해주세요.");
				userPwd.focus();
				rtn = false;
			}else if (!regExpPw.test(userPwd.val())) {
				alert("비밀번호가 올바르지 않습니다. 다시 입력해주세요.");
				userPwd.focus();
				rtn = false;
			}else if($(".re_pwd").val()!=userPwd.val()) {
				alert("비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
				$(".re_pwd").focus();
				rtn = false;
			}else {
				alert("비밀번호가 변경되었습니다.");
				rtn = true;
			}
			return rtn;
		});
	});
</script>
</html>