<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/payment/pointRoulette.css"> 
<!-- <link href="/static/bootstrap.min.css" rel="stylesheet"> -->
<title>돈쭐내기</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menubar.jsp"></jsp:include>
	
	<main>
		<div class="header-background-area">
	        <img src="/resources/images/mapListMain.png" alt="뒷배경이미지">
	    </div>
		<div id="main-title">포인트 룰렛</div>
		<div class="frame">
			<h1>진짜파스타</h1>
			<div class="roulette">
				<h2>포인트 룰렛</h2>
				<h4>최대 5000원 상당의 포인트를 지급받으세요!</h4>
				
				<div id="point-roulette">
					
				</div>
				<button id="point-save-btn">포인트 받기</button>
			</div>
			
		</div>
		
	</main>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>