<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/resources/css/map/MapList.css">
<title>지도조회</title>
</head>
<body>

	<jsp:include page="/WEB-INF/views/common/menubar.jsp"></jsp:include>
	
	<main>
	    <div class="header-background-area">
        	<img src="/resources/images/mapListMain.png" alt="뒷배경이미지">
    	</div>
		<div id="main-title">지도조회</div>
		<div class="frame">
			<div class="sub-title">
				<p>찾을 지역을 선택하세요</p>
			</div>
			<div class="map-menuAll">
				<ul>
					<li><a href="mapSearchShop.dz?mapNo=1">전체</a></li>
					<li><a href="mapSearchShop.dz?mapNo=2">서울</a></li>
					<li><a href="mapSearchShop.dz?mapNo=3">부산</a></li>
					<li><a href="mapSearchShop.dz?mapNo=4">광주</a></li>
					<li><a href="mapSearchShop.dz?mapNo=5">대구</a></li>
					<li><a href="mapSearchShop.dz?mapNo=6">대전</a></li>
					<li><a href="mapSearchShop.dz?mapNo=7">세종</a></li>
					<li><a href="mapSearchShop.dz?mapNo=8">울산</a></li>
					<li><a href="mapSearchShop.dz?mapNo=9">인천</a></li>
					<li><a href="mapSearchShop.dz?mapNo=10">제주</a></li>
					<li><a href="mapSearchShop.dz?mapNo=11">강원</a></li>
					<li><a href="mapSearchShop.dz?mapNo=12">경기</a></li>
					<li><a href="mapSearchShop.dz?mapNo=13">경남</a></li>
					<li><a href="mapSearchShop.dz?mapNo=14">경북</a></li>
					<li><a href="mapSearchShop.dz?mapNo=15">전남</a></li>
					<li><a href="mapSearchShop.dz?mapNo=16">전북</a></li>
					<li><a href="mapSearchShop.dz?mapNo=17">충남</a></li>
					<li><a href="mapSearchShop.dz?mapNo=18">충북</a></li>
				</ul>
			</div>
		</div>
	</main>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	
</body>
</html>