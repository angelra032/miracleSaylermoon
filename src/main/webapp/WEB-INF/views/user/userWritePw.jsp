<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/user/writepw.css"> 
<title>비밀번호 확인</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menubar.jsp"></jsp:include>
	<main>
		<div class="header-background-area">
        	<img src="/resources/images/mapListMain.png" alt="뒷배경이미지">
   		</div>	
   		<div id="main-title">비밀번호 확인</div>
		<div class="frame">
		<h4>비밀번호를 입력하세요</h4>
			<div class="tabcontent">
				<div class="underline">
					<div class="content-body">
						<form action="userDelete.dz" method="get">
							<div class="form-head">
									비밀번호&nbsp;
									<span class="required">*</span>&nbsp;&nbsp;
									<div class="form-noti pwnoti password_noti_0">비밀번호를 입력해 주세요.</div>
								</div>
								<div class="form-body">
									<input name="userPw" class="form-elem pwdelem" type="password" maxlength="20" placeholder="영문, 숫자 또는 혼합 6~20자">
								</div>
								
								<button class="submit-btn" type="submit">탈퇴하기</button>
								<input type="hidden" class="userNo" name="userNo" value=${ loginUser.userNo }>
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
		var userNo = $(".userNo");
		/////비밀번호 입력해주세요
		userPwd.on("blur", function() {
			$('.pwnoti').css('display', 'none');
			if (userPwd.val() =="") {
				$('.password_noti_0').css('color', '#ff5442');
				$('.password_noti_0').css('display', 'block');
				$('.pwdelem').css('border', '1px solid #ff5442');
			}else{
				$('.pwnoti').css('display', 'none');
				$('.pwdelem').css('border', '0');
			}
		});
		$('.submit-btn').click(function() {
			var rtn = true;
			//비밀번호
			$.ajax({
				url : "dupPw.dz",
				data : { "userPw" : userPwd.val(), "userNo" : userNo.val() },
				async: false,
				success : function(result) {
					if (userPwd.val() =="") {
						alert("비밀번호를 입력해주세요.");
						userPwd.focus();
						rtn = false;
					}else if (result == 0) {
						alert("비밀번호가 틀렸습니다. 다시 입력해주세요.");
						userPwd.focus();
						rtn = false;
					}else {
						alert("탈퇴가 완료되었습니다.");
						rtn = true;
					}
				},
				error : function() {
					console.log("전송실패");
				}
			});
			return rtn;
		});
	});
</script>
</html>