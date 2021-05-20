<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- css -->
<link rel="stylesheet" type="text/css" href="/resources/css/map/MapDetail.css">
<!-- 부트스트랩 -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
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
					<form action="" method="get">
						<input type="text" id="searchBox" name="searchKeyword" placeholder="지역별 검색">
						<button id="btn-search"><img src="/resources/images/undo.png"></button>
					</form>
				</div>
				<hr>
				<div class="content-list">
					<c:forEach items="${ mList }" var="shop">
						<div class="content-shop">
							<div class="content-shop left">
								<img src="/resources/images/shopMainImg/realPasta.jpeg" alt="대표이미지" class="img-thumbnail"/>
							</div>
							<div class="content-shop right">
								<div class="content-shop right top">
									<input type="hidden" name="shopNo" value="${ shop.shopNo }">
									<span id=shop-title><b>${ mList.shopName }</b>&nbsp;&nbsp;</span>
									<span>${ shop.shopType }</span><br>
									<span>${ shop.shopAddr }</span><br>
									<span class="label label-info">영업시간</span>
									<span>${ shop.startTime } - ${ shop.endTime }</span><br>
									<span>예약 가능 인원(최대)&nbsp;&nbsp;</span>
									<span>${ shop.shopMaxReserv }</span><br>
									<span>${ shop.shopPhone }&nbsp;&nbsp;${ shop.shopParking }</span>
									<%-- <c:if test="${ mList.shopParking }"> --%>
									<br>
	<!-- 								<span id=shop-title><b>진짜 파스타</b>&nbsp;&nbsp;</span>
									<span>양식</span><br>
									<span>서울특별시 마포구 와우산로 64 전원빌딩 2층 진짜파스타</span><br>
									<span class="label label-info">영업시간</span>
									<span>12:00 - 22:00</span><br>
									<span>예약 가능 인원(최대)</span><br>
									<span>02-322-1518&nbsp;&nbsp;주차가능</span><br> -->
								</div>
								<div class="content-shop right bottom">
									<button type="button" class="btn btn-primary btn-sm">예약하기</button>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
				<div class="pagination">
				
				</div>
			</div>
			<div class=map-right>
				<div id="map"></div> 
				<div class="mapZoom"></div>
			</div>
		</div>
	
	</main>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>

	<script>
		$(function() {
			$("#btn-search").on("click", function() {
				var searchKeyword = $("searchKeyword").val();
				if(searchKeyword == "") {
					alert("검색하실 지역을 입력해주세요.");
				}else {
					$.ajax({
						url: "mapSearchKey.dz",
						type: "get",
						data: { "searchKeyword": searchKeyword },
						dataType: "json",
						success: function(data) {
								console.log(data);
						},
						error: function() {
							alert("서버에 연결할 수 없습니다.");
						}
					});
				}
			});
		});
	</script>
</body>
</html>