<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/payment/snsPhoto.css"> 
<link href="/static/bootstrap.min.css" rel="stylesheet">
<title>돈쭐내기</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menubar.jsp"></jsp:include>
	
	<main>
		<div class="header-background-area">
	        <img src="/resources/images/mapListMain.png" alt="뒷배경이미지">
	    </div>
		
		<div id="main-title">인증샷&nbsp;&nbsp;<span id="main-title-shop">${shop.shopName }</span></div>
		<div class="frame">
		
			<div class="sns">
				
				<div>
					<img src="/resources/images/snsPhoto.png" id="sns-Photo"> 
					
				</div>
				
			</div>
			
			<div class="result">
				<div class="lay-title">
					<span class="title-span">당첨 결과</span>
				</div>
				<div class="lay-content">
					<div>
						<div id="h2">${loginUser.userName }님,<br> 호되게 돈쭐내셨군요!</div>
						<h4>인증샷을 SNS에 자랑해주세요.<br>많은 공유가 많은 돈쭐을 부릅니다.</h4>
					</div>
					<button id="sns-link-btn">SNS에 인증하기</button>
				</div>
			</div>
			
		</div>
	</main>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>