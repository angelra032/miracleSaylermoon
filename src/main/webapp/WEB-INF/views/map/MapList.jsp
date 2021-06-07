<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
        	<img src="/resources/images/map/mapListMain.png" alt="뒷배경이미지">
    	</div>
		<div id="main-title">지도조회</div>
		<div class="frame">
			<div class="sub-title">
				<p>찾을 지역을 선택하세요</p>
			</div>
			<div class="map-menuAll">
				<ul>
					<li><a href="mapSearchShop.dz?location=All">전체</a></li>
					<li><a href="mapSearchShop.dz?location=Seoul">서울</a></li>
					<li><a href="mapSearchShop.dz?location=Busan">부산</a></li>
					<li><a href="mapSearchShop.dz?location=Gwangju">광주</a></li>
					<li><a href="mapSearchShop.dz?location=Daegu">대구</a></li>
					<li><a href="mapSearchShop.dz?location=Daejeon">대전</a></li>
					<li><a href="mapSearchShop.dz?location=Sejong">세종</a></li>
					<li><a href="mapSearchShop.dz?location=Ulsan">울산</a></li>
					<li><a href="mapSearchShop.dz?location=Incheon">인천</a></li>
					<li><a href="mapSearchShop.dz?location=Jeju">제주</a></li>
					<li><a href="mapSearchShop.dz?location=Gangwon">강원</a></li>
					<li><a href="mapSearchShop.dz?location=Gyeonggi">경기</a></li>
					<li><a href="mapSearchShop.dz?location=SouthGyeongsang">경남</a></li>
					<li><a href="mapSearchShop.dz?location=NorthGyeongsang">경북</a></li>
					<li><a href="mapSearchShop.dz?location=SouthJeolla">전남</a></li>
					<li><a href="mapSearchShop.dz?location=NorthJeolla">전북</a></li>
					<li><a href="mapSearchShop.dz?location=SouthChungCheong">충남</a></li>
					<li><a href="mapSearchShop.dz?location=NorthChungCheong">충북</a></li>
				</ul>
			</div>
		</div>
	</main>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>

</body>
</html>