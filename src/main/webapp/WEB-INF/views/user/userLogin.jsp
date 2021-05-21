<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/user/login.css"> 
<link href="/static/bootstrap.min.css" rel="stylesheet">
<title>돈쭐 로그인</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menubar.jsp"></jsp:include>
	
	<main>
		<div class="header-background-area">
        	<img src="/resources/images/mapListMain.png" alt="뒷배경이미지">
   		</div>	
		<div id="main-title">로그인</div>
		<div class="frame">
			<div class="left-login">
				<h2>회원로그인</h2>
				<h4>꿈나무, 일반, 사업자 회원</h4>
				<div class="left-inner-box">
					<form action="login.dz" method="POST"> 
						<div class="input-box"> 
							<input id="userid" type="text" name="userId" placeholder="아이디"> 
							<label for="userid">아이디</label> 
						</div> 
						<div class="input-box"> 
							<input id="userpw" type="password" name="userPw" placeholder="비밀번호"> 
							<label for="userpw">비밀번호</label> 
						</div> 
						<input type="submit" id="left-login-bt" value="로그인"> 
						<a href="findIdView.dz">아이디 찾기</a>
						<a href="findPwView.dz">비밀번호 찾기</a>
					</form>
				</div>
			</div>
			<div class="left-login">
				<h2>간편로그인</h2>
				<h4>일반회원전용</h4>
				<div class="left-inner-box">
					<form action="" method="POST"> 
						
					</form>
				</div>
			</div>
		
		</div>
	</main>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
<script>
	$(function() {
		$('#left-login-bt').click(function() {
			var rtn = true;
			var userId = $("#userid");
			var userPw = $("#userpw");
			/* if(userId.val() == "") {
				alert("아이디를 입력해주세요")
				rtn = false;
			}else if(userPw.val() == ""){
				alert("비밀번호를 입력해주세요")
				rtn = false;
			} */
			$.ajax({
				url : "dupLogin.dz",
				data : { "userId" : userId.val(), "userPw" : userPw.val() },
				async: false,
				success : function(result) {
					if(userId.val() == "") {
						alert("아이디를 입력해주세요")
						rtn = false;
					}else if(userPw.val() == ""){
						alert("비밀번호를 입력해주세요")
						rtn = false;
					}else if(result == 1){
						alert("아이디 또는 비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
						userId.focus();
						rtn = false;
					}else {
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