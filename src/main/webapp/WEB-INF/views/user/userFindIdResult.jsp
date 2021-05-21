<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/user/findidresult.css">
<title>ID 찾기</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menubar.jsp"></jsp:include>
	<main>
		<div class="header-background-area">
        	<img src="/resources/images/mapListMain.png" alt="뒷배경이미지">
   		</div>	
		<div id="main-title">아이디 찾기</div>
		<div class="frame">
			<h4>${ userName}님의 아이디 조회 결과</h4>
			<div class="tabcontent">
				<div class="content-body">
					<p>회원님의 아이디는</p>
					<p><span><b>${userId }</b></span>&nbsp입니다.</p>
					<div class ="btn-frame">
						<a href="loginView.dz">로그인</a>
						<a href="findPwView.dz">비밀번호 찾기</a>
					</div>
				</div>
			</div>
		</div>
	</main>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>