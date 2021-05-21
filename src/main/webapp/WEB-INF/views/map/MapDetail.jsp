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
    <jsp:include page="/WEB-INF/views/map/MapDetailmenubar.jsp"></jsp:include> 

	<main>
		<div class=frame>
			<div class=map-left>
				<div class="searchBar">
					<form action="mapSearchKey.dz" method="get">
						<input type="text" id="searchBox" name="searchKeyword" placeholder="지역별 검색">
						<button id="btn-search"><img src="/resources/images/undo.png"></button>
					</form>
				</div>
				<hr>
				<div class="content-list">
					<c:forEach items="${ mList }" var="shop">
						<div class="content-shop">
							<div class="content-shop left">
								<img src="/resources/images/logoG-mark.png" alt="대표이미지" class="img-thumbnail none"/>
								<!-- <img src="/resources/images/shopMainImg/realPasta.jpeg" alt="대표이미지" class="img-thumbnail"/> -->
							</div>
							<div class="content-shop right">
								<div class="content-shop right top">
									<input type="hidden" name="shopNo" value="${ shop.shopNo }">
									<span id=shop-title><b>${ shop.shopName }</b>&nbsp;&nbsp;</span>
									<span>${ shop.shopType }</span><br>
									<span>${ shop.shopAddr }</span><br>
									<span>영업시간</span>
									<span>${ shop.startTime }:00 - ${ shop.endTime }:00</span><br>
									<span>휴무</span><br>
									<span>${ shop.shopMaxReserv }인</span><br>
									<span>${ shop.shopPhone }&nbsp;&nbsp;</span>
									<c:if test="${ shop.shopParking eq 'Y' }">
										<span>주차가능</span>
									</c:if>
									<c:if test="${ shop.shopParking eq 'N' }">
										<span>주차불가</span>
									</c:if>
									<br>
								</div>
								<div class="content-shop right bottom">
									<button type="button" class="btn btn-primary btn-sm">예약하기</button>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
				<div class="content-list navi">
					<hr>
					<c:url var="before" value="mapSearchShop.dz">
						<c:param name="page" value="${ pi.currentPage - 1 }"></c:param>
						<c:if test="${ mapNo != null }">
							<c:param name="mapNo" value="${ mapNo }"></c:param>
						</c:if>
					</c:url>
					<c:if test="${ pi.currentPage > 1 }">
						<a href="${ before }"><img src="/resources/images/navi-left.png" alt="이전"/>&nbsp;&nbsp;</a>
					</c:if>
					<c:forEach var="p" begin="${ pi.startPage }" end="${ pi.endPage }">
						<c:url var="pagination" value="mapSearchShop.dz">
							<c:param name="page" value="${ p }"></c:param>
							<c:if test="${ mapNo != null }">
								<c:param name="mapNo" value="${ mapNo }"></c:param>
							</c:if>
						</c:url>
						<c:if test="${ p eq pi.currentPage }">
							<span id="currentPage">${ p }</span>
						</c:if>
						<c:if test="${ p ne pi.currentPage }">
							<a href="${ pagination }"><span>${ p }</span>&nbsp;&nbsp;</a>
						</c:if>
					</c:forEach>
					<c:url var="after" value="mapSearchShop.dz">
						<c:param name="page" value="${ pi.currentPage + 1 }"></c:param>
						<c:if test="${ mapNo != null }">
							<c:param name="mapNo" value="${ mapNo }"></c:param>
						</c:if>
					</c:url>
					<c:if test="${ pi.currentPage >= pi.maxPage }">
					</c:if>
					<c:if test="${ pi.currentPage < pi.maxPage }">
						<a href="${ after }"><img src="/resources/images/navi-right.png" alt="다음"/></a>
					</c:if>
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
				var searchKeyword = $("#searchBox").val();
				if(searchKeyword == "") {
					alert("검색하실 지역을 입력해주세요.");
					return false;
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
							return false;
						}
					});
				}
			});
		});
/* 		function pageMove() {
			$(".content-list").empty();
			$(".content-list navi").empty();
			$.ajax({
				url : ,
				type : ,
				data : ,
				success : function(data) {
					for( int i in data.mapList) {
						$(".content-list").append(mapList[i].showNo)	
												
					}
				},
				error : function() {
					
				}
			});
		} */
	</script>
</body>
</html>