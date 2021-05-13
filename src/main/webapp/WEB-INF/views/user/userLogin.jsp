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
		<div id="main-title">로그인</div>
		<div class="frame">
			<div class="left-login">
				<h2>회원로그인</h2>
				<h4>꿈나무, 일반, 사업자 회원</h4>
				<div class="left-inner-box">
					<form action="" method="POST"> 
						<div class="input-box"> 
							<input id="username" type="text" name="username" placeholder="아이디"> 
							<label for="username">아이디</label> 
						</div> 
						<div class="input-box"> 
							<input id="password" type="password" name="password" placeholder="비밀번호"> 
							<label for="password">비밀번호</label> 
						</div> 
						<input type="submit" id="left-login-bt" value="로그인"> 
						<div id="findpw">비밀번호 찾기</div> 
					</form>
				</div>
			</div>
			<div class="left-login">
				<h2>간편로그인</h2>
				<h4>일반회원전용</h4>
				<div class="left-inner-box">
					<form action="" method="POST"> 
						<div class="input-box"> 
							<input id="username" type="text" name="username" placeholder="아이디"> 
							<label for="username">아이디</label> 
						</div> 
						<div class="input-box"> 
							<input id="password" type="password" name="password" placeholder="비밀번호"> 
							<label for="password">비밀번호</label> 
						</div> 
						<input type="submit" id="left-login-bt" value="로그인"> 
						<div id="findpw">비밀번호 찾기</div> 
					</form>
				</div>
			</div>
		
		</div>
	</main>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>