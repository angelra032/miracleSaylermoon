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
<title>지도 상세 페이지</title>
</head>
<body>
	<header>
        <div class="fixed-header-navi">
            <div class="header-logo-area" onclick="location.href='#'">
                <!-- visual에서 작업함 경로수정필요!! -->
                <img src="/resources/images/logo.png" alt="로고">
            </div>
            <div class="header-menu-area">
                <ul>
                    <li><a href="#">돈쭐소개</a></li>
                    <li><a href="/mapSearchList.dz">지도조회</a></li>
                    <li><a href="#">가게검색</a></li>
                    <li><a href="#">커뮤니티</a></li>
                </ul>
            </div>
            <div class="header-submenu-area">
                <a href="loginView.dz">로그인</a>
                <a href="#">회원가입</a>
            </div>
        </div>
    </header>
    
    	<!-- 메뉴바 제이쿼리문 -->
	<script>
	    $(document).ready(function(){
	        $(window).scroll(function(){
	            var scroll = $(window).scrollTop();
	            if (scroll > 1) {
	                scrollDownEvent();
	                
	            }
	            else{
	                scrollUpEvent();
	            }
	        });
	        
	        // 스크롤아래로
	        function scrollDownEvent() {
	            $('.fixed-header-navi').css("background" , "#ffffff");
	            $('.fixed-header-navi').css("box-shadow" , "0 0 20px rgba(0,0,0,.5)");
	            $('.fixed-header-navi').css("position" , "fixed");
	            $('.header-menu-area a').css('color', '#333333');
	            $('.header-submenu-area a').css('color', '#333333');
	            $(".header-logo-area img").attr("src", '/resources/images/logo-color.png');
	        }
	        // 스크롤 위로
	        function scrollUpEvent() {
	            $(".fixed-header-navi").css("background" , "");
	            $('.fixed-header-navi').css("box-shadow" , "");
	            $('.header-menu-area a').css('color', '#ffffff');
	            $('.header-submenu-area a').css('color', '#ffffff');
	            $(".header-logo-area img").attr("src", '/resources/images/logo.png');
	        }
	
	    });
	</script>

	<main>
		<div id="map">
			<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1683794343a4e97ff3195b44b6488d0c"></script>
			<script>
				var container = document.getElementById('map');
				var options = {
					center: new kakao.maps.LatLng(33.450701, 126.570667),
					level: 3
				};
		
				var map = new kakao.maps.Map(container, options);
			</script>
		</div>
	</main>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>

</body>
</html>