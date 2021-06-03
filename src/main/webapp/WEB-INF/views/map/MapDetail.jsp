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
<script src="resources/js/map/map-ajax.js"></script>
<!-- 지도 -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1683794343a4e97ff3195b44b6488d0c&libraries=services"></script>
<title>지도 상세 페이지</title>
</head>
<body>
    <jsp:include page="/WEB-INF/views/map/MapDetailmenubar.jsp"></jsp:include> 

	<main>
		<input type="hidden" id="center-value" value="${center }">
		<input type="hidden" id="mapMarkers" value="${mapMarkers }">
		<div class=frame>
			<div class=map-left>
				<div class="searchBar">
					<!-- <form action="#"> -->
						<input type="text" id="searchBox" name="searchKeyword" placeholder="지역명, 가게명 검색">
						<button id="btn-search"><img src="/resources/images/undo.png"></button>
					<!-- </form> -->
				</div>
				<hr>
				<div class="content-list">
					<c:if test="${empty mList}">
						<span id="shopReady">등록된 가게가 없습니다.</span>
					</c:if>
					<c:if test="${!empty mList}">
						<c:forEach items="${ mList }" var="shop">
							<div class="content-shop">
								<div class="content-shop left">
									<img src="/resources/images/logoG-mark.png" alt="대표이미지" class="img-thumbnail none"/>
									<!-- <img src="/resources/images/shopMainImg/" alt="대표이미지" class="img-thumbnail"/> -->
								</div>
								<div class="content-shop right">
									<div class="content-shop right top">
										<span id="shop-title"><b>${ shop.shopName }</b>&nbsp;&nbsp;</span>
										<span>${ shop.shopType }</span><br>
										<span>${ shop.shopAddr }</span><br>
										<c:if test="${!empty shop.shopContent }">
											<span id="shop-content">${ shop.shopContent }</span><br>
										</c:if>
										<c:if test="${!empty shop.shopContent }">
											<span id="shop-content"></span><br>
										</c:if>
										<br>
									</div>
									<div class="content-shop right bottom">
										<%-- <c:if test="${ !empty loginUser }">
											<button type="button" class="btn btn-primary btn-sm" onclick="shopDetail(${shop.shopNo }, ${loginUser.userNo })">예약하기</button>
										</c:if>
										<c:if test="${ empty loginUser }"> --%>
											<button type="button" class="btn btn-primary btn-sm" onclick="shopDetail(${shop.shopNo })">예약하기</button>
										<%-- </c:if> --%>
									</div>
								</div>
							</div>
						</c:forEach>
					</c:if>
				</div>
				<div class="content-list-navi">
					<c:if test="${empty mList}"></c:if>
					<c:if test="${!empty mList}">
						<hr>
						<c:url var="before" value="mapSearchShop.dz">
							<c:param name="page" value="${ pi.currentPage - 1 }"></c:param>
							<c:if test="${ !empty location }">
								<c:param name="location" value="${ location }"></c:param>
							</c:if>
						</c:url>
						<c:if test="${ pi.currentPage > 1 }">
							<a href="${ before }"><img src="/resources/images/navi-left.png" alt="이전"/>&nbsp;&nbsp;</a>
						</c:if>
						<c:forEach var="p" begin="${ pi.startPage }" end="${ pi.endPage }">
							<c:url var="pagination" value="mapSearchShop.dz">
								<c:param name="page" value="${ p }"></c:param>
								<c:if test="${ !empty location }">
									<c:param name="location" value="${ location }"></c:param>
								</c:if>
							</c:url>
							<c:if test="${ p eq pi.currentPage }">
								<span id="currentPage">${ p }</span>
							</c:if>
							<c:if test="${ p ne pi.currentPage }">
								<a href="${ pagination }"><span id="otherPage">${ p }</span>&nbsp;&nbsp;</a>
							</c:if>
						</c:forEach>
						<c:url var="after" value="mapSearchShop.dz">
							<c:param name="page" value="${ pi.currentPage + 1 }"></c:param>
							<c:if test="${ !empty location }">
								<c:param name="location" value="${ location }"></c:param>
							</c:if>
						</c:url>
						<c:if test="${ pi.currentPage >= pi.maxPage }">
						</c:if>
						<c:if test="${ pi.currentPage < pi.maxPage }">
							<a href="${ after }"><img src="/resources/images/navi-right.png" alt="다음"/></a>
						</c:if>
					</c:if>
				</div>
			</div>
			<div class=map-right>
				<div class="mapZoom">
					<div class="mapZoom in">
						<span onclick="zoomIn()"><img src="/resources/images/plus-white.png" alt="zoom-in"></span>
					</div>
					<div class="mapZoom out">
						<span onclick="zoomOut()"><img src="/resources/images/minus-white.png" alt="zoom-out"></span>
					</div>
				</div> 
				<div id="map"> 
				</div>
			</div>
		</div>
	</main>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	
	<!-- 맵 js -->
	<script>
	
		var positions = [];
		
			<c:forEach var="shop" items="${mapMarkers}" >
				var shopMap = new Object();
				
				shopMap.shopNo = "${shop.shopNo}";
				shopMap.shopName = "${shop.shopName}";
				shopMap.shopShortAddr = "${shop.shopShortAddr}";
				shopMap.shopAddr = "${shop.shopAddr}";
				shopMap.startTime = "${shop.startTime}";
				shopMap.endTime = "${shop.endTime}";
				shopMap.businessDay = "${shop.businessDay}";
				shopMap.shopTarget = "${shop.shopTarget}";
				shopMap.shopProduct = "${shop.shopProduct}";
				
				positions.push(shopMap);
			</c:forEach>
		
 	/* var positions = [];
	var mapObj;
	<c:forEach var="shop" items="${mapMarkers}" >
	var shopMap = new Object();
	
	shopMap.shopNo = "${shop.shopNo}";
	shopMap.shopName = "${shop.shopName}";
	shopMap.shopShortAddr = "${shop.shopShortAddr}";
	shopMap.shopAddr = "${shop.shopAddr}";
	shopMap.startTime = "${shop.startTime}";
	shopMap.endTime = "${shop.endTime}";
	shopMap.businessDay = "${shop.businessDay}";
	shopMap.shopTarget = "${shop.shopTarget}";
	shopMap.shopProduct = "${shop.shopProduct}";
	
	positions.push(shopMap);
	</c:forEach> */
	
    mapObj = showMap();
	
    
	mapJs(mapObj, positions);
	
	// 지도 확대, 축소 컨트롤에서 확대 버튼을 누르면 호출되어 지도를 확대하는 함수입니다
	function zoomIn() {
		mapObj.setLevel(mapObj.getLevel() - 1);
	}
	// 지도 확대, 축소 컨트롤에서 축소 버튼을 누르면 호출되어 지도를 확대하는 함수입니다
	function zoomOut() {
		mapObj.setLevel(mapObj.getLevel() + 1);
	}
	
    // 가게 상세 페이지 이동
    
    function shopDetailsession(shopNo, userNo) {
        location.href='shopDetail.dz?shopNo='+shopNo+'&userNo='+userNo;
        
    }
    
    function shopDetail(shopNo) {
        location.href='shopDetail.dz?shopNo='+shopNo;
        
    } 		
			

	   
	
			$("#btn-search").on("click", function() {
				/* var result = true; */
				
				var searchKeyword = $("#searchBox").val();
				if(searchKeyword == "") {
					alert("검색하실 지역을 입력해주세요.");
					/* result = false; */
					return false;
				}else {
					$(".content-list").empty();
					$(".content-list-navi").empty();
	
						$.ajax({
							url: "mapSearchKey.dz",
							type: "get",
							data: { "searchKeyword": searchKeyword }, // ""따옴표 안의 값이 키 값, vo 클래스 변수명과 일치해야 한다.
							/* async: false, */
							dataType: "json", // 중요!! 안 적으면 데이터 안 가져옴
							success: function(data) {
								var contentList = $(".content-list");
								var contentListNavi = $(".content-list-navi");
								$("#center-value").val(data.center);
								if(data.mList.length > 0) { 
									$(".content-list").empty();
									$(".content-list-navi").empty();
									
									/* 검색 리스트 */
									for( var i in data.mList) {
										
										var contentShop = $("<div class='content-shop'>");
										var contentShopLeft = $("<div class='content-shop left'>");
										var contentShopRight = $("<div class='content-shop right'>");
										var contentShopRightTop = $("<div class='content-shop right top'>");
										var contentShopRightBottom = $("<div class='content-shop right bottom'>");
										
										contentShopLeft.append("<img src='/resources/images/logoG-mark.png' alt='대표이미지' class='img-thumbnail none'/>");
										contentShopRightTop.append("<span id='shop-title'><b>"+data.mList[i].shopName+"</b>&nbsp;&nbsp;</span>")
														   .append("<span>"+data.mList[i].shopType+"</span><br>")
														   .append("<span>"+data.mList[i].shopAddr+"</span><br>")
														   .append("<span>"+data.mList[i].shopContent+"</span><br>");
										contentShopRightBottom.append("<button type='button' class='btn btn-primary btn-sm' onclick='shopDetail("+data.mList[i].shopNo+")'>예약하기</button>");
										contentShop.append(contentShopLeft);
										contentShopRight.append(contentShopRightTop);
										contentShopRight.append(contentShopRightBottom);
										contentShop.append(contentShopRight);
										contentList.append(contentShop); 
							 	 	}
									
									
									/* 네비 */
									contentListNavi.append("<hr>");
									if(data.pi.currentPage > 1) {
										var prev = Number(data.pi.currentPage)-1;
										contentListNavi.append("<a href='#' onclick='searchLogic1(\""+data.searchKeyword+"\", "+prev+");'><img src='/resources/images/navi-left.png' alt='이전'/>&nbsp;&nbsp;</a>");
									}
									for(var i = data.pi.startPage; i <= data.pi.endPage; i++) {
										if(i == data.pi.currentPage) {
											contentListNavi.append("<span id='currentPage'>"+i+"</span>");	
										}else if(i != data.pi.currentPage) {
											contentListNavi.append("<a href='#' onclick='searchLogic1(\""+data.searchKeyword+"\", "+i+");'><span id='otherPage'>"+i+"</span>&nbsp;&nbsp;</a>");
										}
									}
									if(data.pi.currentPage < data.pi.maxPage) {
										var next = Number(data.pi.currentPage)+1;
										contentListNavi.append("<a href='#' onclick='searchLogic1(\""+data.searchKeyword+"\", "+next+");'><img src='/resources/images/navi-right.png' alt='다음'/></a>");
									}
									
									/* 맵 js */
									mapJs(mapMarkers);
									
							}else {
								$(".content-list").empty();
								$(".content-list-navi").empty();
								contentList.append("<span>등록된 가게가 없습니다.</span>");
								/* result = false; */
							}  
						},
						error: function() {
							console.log("서버에 연결할 수 없습니다.");
						}
					});
	 				/* result = true; */
				}
			});
		
			
			
			function searchLogic1(searchKeyword, page) {
			
				$(".content-list").empty();
				$(".content-list-navi").empty();
	
					$.ajax({
						url: "mapSearchKey.dz",
						type: "get",
						data: { "searchKeyword": searchKeyword, "page" :  page}, // ""따옴표 안의 값이 키 값, vo 클래스 변수명과 일치해야 한다.
						/* async: false, */
						dataType: "json", // 중요!! 안 적으면 데이터 안 가져옴
						success: function(data) {
							var contentList = $(".content-list");
							var contentListNavi = $(".content-list-navi");
							$("#center-value").val(data.center);
							if(data.mList.length > 0) { 
								$(".content-list").empty();
								$(".content-list-navi").empty();
								
								/* 검색 리스트 */
								for( var i in data.mList) {
									
									var contentShop = $("<div class='content-shop'>");
									var contentShopLeft = $("<div class='content-shop left'>");
									var contentShopRight = $("<div class='content-shop right'>");
									var contentShopRightTop = $("<div class='content-shop right top'>");
									var contentShopRightBottom = $("<div class='content-shop right bottom'>");
									
									contentShopLeft.append("<img src='/resources/images/logoG-mark.png' alt='대표이미지' class='img-thumbnail none'/>");
									contentShopRightTop.append("<span id='shop-title'><b>"+data.mList[i].shopName+"</b>&nbsp;&nbsp;</span>")
													   .append("<span>"+data.mList[i].shopType+"</span><br>")
													   .append("<span>"+data.mList[i].shopAddr+"</span><br>")
													   .append("<span>"+data.mList[i].shopContent+"</span><br>");
									contentShopRightBottom.append("<input type='hidden' name='shopNo' value="+data.mList[i].shopNo+">")
														  .append("<button type='button' class='btn btn-primary btn-sm' onclick='shopDetail()'>예약하기</button>");
									contentShop.append(contentShopLeft);
									contentShopRight.append(contentShopRightTop);
									contentShopRight.append(contentShopRightBottom);
									contentShop.append(contentShopRight);
									contentList.append(contentShop); 
						 	 	}
									
								
								/* 네비 */
								contentListNavi.append("<hr>");
								if(data.pi.currentPage > 1) {
									var prev = Number(data.pi.currentPage)-1;
									contentListNavi.append("<a href='#' onclick='searchLogic1(\""+data.searchKeyword+"\", "+prev+");'><img src='/resources/images/navi-left.png' alt='이전'/>&nbsp;&nbsp;</a>");
								}
								for(var i = data.pi.startPage; i <= data.pi.endPage; i++) {
									if(i == data.pi.currentPage) {
										contentListNavi.append("<span id='currentPage'>"+i+"</span>");	
									}else if(i != data.pi.currentPage) {
										contentListNavi.append("<a href='#' onclick='searchLogic1(\""+data.searchKeyword+"\", "+i+");'><span id='otherPage'>"+i+"</span>&nbsp;&nbsp;</a>");
									}
								}
								if(data.pi.currentPage < data.pi.maxPage) {
									var next = Number(data.pi.currentPage)+1;
									contentListNavi.append("<a href='#' onclick='searchLogic1(\""+data.searchKeyword+"\", "+next+");'><img src='/resources/images/navi-right.png' alt='다음'/></a>");
								}

								var mapMarkers = data.mapMarkers;
									
								/* 맵 js */
								mapJs(mapMarkers);
								
						}else {
							$(".content-list").empty();
							$(".content-list-navi").empty();
							contentList.append("<span>등록된 가게가 없습니다.</span>");
						}  
					},
					error: function() {
						console.log("서버에 연결할 수 없습니다.");
					}
				});
			}
	</script>
	
	
</body>
</html>