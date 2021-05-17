<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/main.css"> 
<title>꿈나무 예약목록 페이지</title>
<style>
	.frame {
	width: 1440px;
	margin: 0 auto;
	position:relative;
	height:500px;
	margin-top:140px;
	margin-bottom: 140px;
}
</style>
</head>
<body>
	<!-- 헤더 시작 -->
	<jsp:include page="/WEB-INF/views/common/menubar.jsp"></jsp:include>
	<!-- 헤더 끝 -->
	
	<!-- 본문 시작 -->
	<main align="center">
		<div class="header-background-area">
        	<img src="/resources/images/mapListMain.png" alt="뒷배경이미지">
   		</div>	
   		
		<div id="main-title">타이틀!</div>
		<div class="frame">
		
		
		</div>
	</main>
	<!-- 본문 끝 -->
	
	<!-- 푸터 시작 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	<!-- 끝 -->
</body>
</html>