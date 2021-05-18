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
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1683794343a4e97ff3195b44b6488d0c&libraries=services"></script>
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
		<div class=frame>
			<div class=map-left>
				<div class="searchBar">
					<form>
						<input type="text" id="searchBox" name="mapKeyword" placeholder="지역별 가게 검색">
						<button type="submit" id="btn-search"><img src="/resources/images/undo.png" onclick=""></button>
					</form>
				</div>
				<hr>
				<div class="content-list">
					
				</div>
				<div class="pagination">
				
				</div>
			</div>
			<div class=map-right>
				<div id="map"></div> 
			</div>
		</div>
	
	</main>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>

</body>
</html>