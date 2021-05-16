<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- css -->
<link rel="stylesheet" type="text/css" href="/resources/css/map/MapDetail.css">
<!-- JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- 지도 -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1683794343a4e97ff3195b44b6488d0c"></script>
<script type="text/javascript" src="/resources/js/map/Map.js"></script>
<title>지도 상세 페이지</title>
</head>
<body>
    <header>
        <div class="fixed-header-navi">
            <div class="header-logo-area" onclick="location.href='#'">
                <!-- visual에서 작업함 경로수정필요!! -->
                <img src="/resources/images/logo-color.png" alt="로고">
            </div>
            <div class="header-menu-area">
                <ul>
                    <li><a href="#">돈쭐소개</a></li>
                    <li><a href="mapSearchList.dz">지도조회</a></li>
                    <li><a href="searchShopView.dz">가게검색</a></li>
                    <li><a href="#">커뮤니티</a></li>
                </ul>
            </div>
            <div class="header-submenu-area">
                <a href="loginView.dz">로그인</a>
                <a href="enrollView.dz">회원가입</a>
            </div>
        </div>
    </header>

	<main>
		<div id="frame">
			<div id="map"></div> 
		</div>
	<!-- 리스트 출력 -->
		<!-- 	<div class="map_wrap">
    <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>

    <div id="menu_wrap" class="bg_white">
        <div class="option">
            <div>
                <form onsubmit="searchPlaces(); return false;">
                    키워드 : <input type="text" value="이태원 맛집" id="keyword" size="15"> 
                    <button type="submit">검색하기</button> 
                </form>
            </div>
        </div>
	        <hr>
	        <ul id="placesList"></ul>
	        <div id="pagination"></div>
	    </div>
	</div> -->
	
	</main>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>

</body>
</html>