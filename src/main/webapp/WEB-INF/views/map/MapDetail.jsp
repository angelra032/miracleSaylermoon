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
<title>지도 상세 페이지</title>
</head>
<body>
    <jsp:include page="/WEB-INF/views/map/MapDetailmenubar.jsp"></jsp:include> 

	<main>
		<div class=frame>
			<div class=map-left>
				<div class="searchBar">
					<form action="mapSearchKey.dz" method="get" onsubmit="return false;">
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
									<span>${ shop.shopContent }</span><br>
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
							<a href="${ pagination }"><span id="otherPage">${ p }</span>&nbsp;&nbsp;</a>
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
	
		/* console.log(positions); */
	
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	 		mapOption = { 
	        center: new kakao.maps.LatLng(37.56421, 127.00169), // 지도의 중심좌표
	        level: 7 // 지도의 확대 레벨
	    	};

		
		var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
		
		 // 마우스 휠로 지도 확대,축소 가능여부를 설정합니다
	    map.setZoomable(false); 
		
		// 지도 확대, 축소 컨트롤에서 확대 버튼을 누르면 호출되어 지도를 확대하는 함수입니다
		function zoomIn() {
		    map.setLevel(map.getLevel() - 1);
		}

		// 지도 확대, 축소 컨트롤에서 축소 버튼을 누르면 호출되어 지도를 확대하는 함수입니다
		function zoomOut() {
		    map.setLevel(map.getLevel() + 1);
		}
		
		// 주소-좌표 변환 객체를 생성합니다
		var geocoder = new kakao.maps.services.Geocoder();
		
			// 주소로 좌표를 검색합니다
		positions.forEach(function(shop, index){ 
			
			geocoder.addressSearch(shop.shopAddr, function(result, status) {
				
			/* console.log(shop.shopAddr); */
			
			    // 정상적으로 검색이 완료됐으면 
			     if (status === kakao.maps.services.Status.OK) {
			
					var imageSrc = '/resources/images/map_marker_blue.png', // 마커이미지의 주소입니다    
					    imageSize = new kakao.maps.Size(27, 35); // 마커이미지의 크기입니다
					      
					// 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
					var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize),
					    markerPosition = new kakao.maps.LatLng(result[0].y, result[0].x); // 마커가 표시될 위치입니다
					
					// 마커를 생성합니다
					var marker = new kakao.maps.Marker({
					    position: markerPosition, 
					    image: markerImage // 마커이미지 설정 
					});
					
					// 마커가 지도 위에 표시되도록 설정합니다
					marker.setMap(map);
					
					/// 커스텀 오버레이에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
					var content = '<div class="customoverlay">' +
					    '  <a href="javascript:showShortInfo()">' +
					    '    <span class="title">' + shop.shopName + '</span>' +
					    '  </a>' +
					    '</div>';
					
					// 커스텀 오버레이가 표시될 위치입니다 
					var position = new kakao.maps.LatLng(result[0].y, result[0].x); 
					
					// 커스텀 오버레이를 생성합니다
					var titleOverlay = new kakao.maps.CustomOverlay({
					    position: position,
					    map: map,
					    content: content,
					    yAnchor: 1 
					});
					
/* 					// 마커에 마우스오버 이벤트를 등록합니다
					kakao.maps.event.addListener(marker, 'mouseover', function() {
						// 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
					    titleOverlay.setMap(map);
					});
					
					// 마커에 마우스아웃 이벤트를 등록합니다
					kakao.maps.event.addListener(marker, 'mouseout', function() {
					    // 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다
					    titleOverlay.setMap(null);
					}); */
					

 					function showShortInfo() {
						
						titleOverlay.setMap(null);
						
						var shortContent = '<div class="wrap">' + 
			            '    <div class="info">' + 
			            '        <div class="title">' + 
			            '            카카오 스페이스닷원' + 
			            '            <div class="close" onclick="closeOverlay()" title="닫기"></div>' + 
			            '        </div>' + 
			            '        <div class="body">' + 
			            '            <div class="img">' +
			            '                <img src="https://cfile181.uf.daum.net/image/250649365602043421936D" width="73" height="70">' +
			            '           </div>' + 
			            '            <div class="desc">' + 
			            '                <div class="ellipsis">제주특별자치도 제주시 첨단로 242</div>' + 
			            '                <div class="jibun ellipsis">(우) 63309 (지번) 영평동 2181</div>' + 
			            '                <div><a href="https://www.kakaocorp.com/main" target="_blank" class="link">홈페이지</a></div>' + 
			            '            </div>' + 
			            '        </div>' + 
			            '    </div>' +    
			            '</div>';
				
						// 마커 위에 커스텀오버레이를 표시합니다
						// 마커를 중심으로 커스텀 오버레이를 표시하기위해 CSS를 이용해 위치를 설정했습니다
						var shortInfo = new kakao.maps.CustomOverlay({
						    content: shortContent,
						    map: map,
						    position: markerPosition
						});
					
					}
					
					
					// 커스텀 오버레이를 닫기 위해 호출되는 함수입니다 
					function closeOverlay() {
						shortInfo.setMap(null);     
					}
					
					
					
					
					
					
					
					
					
					

					// 커스텀 오버레이를 닫기 위해 호출되는 함수입니다 
					function closeOverlay() {
						titleOverlay.setMap(null);     
					}
					
					// 지도의 중심좌표를 얻어옵니다 
				    var latlng = map.getCenter(); 

				    var message = '<p>중심 좌표는 위도 ' + latlng.getLat() + ', 경도 ' + latlng.getLng() + '입니다</p>';
				    console.log(message);
				}
			});
			
		});
	</script>

	<script>
		function() {
			$("#btn-search").on("submit", function() {
				var searchKeyword = $("#searchBox").val();
				if(searchKeyword == "") {
					alert("검색하실 지역을 입력해주세요.");
					return false;
				}else {
					$(".content-list").empty();
					$(".content-list navi").empty();
					$.ajax({
						url: "mapSearchKey.dz",
						type: "get",
						data: { "searchKeyword": searchKeyword }, // ""따옴표 안의 값이 키 값, vo 클래스 변수명과 일치해야 한다.
						dataType: "json", // 중요!! 안 적으면 데이터 안 가져옴
						success: function(data) {
							/* if(data.mList.length > 0) { */
								for( var i in data.mList) {
								/* 	console.log(data.pi);
									console.log(data.mList);
									console.log(data.mList[0].shopNo); */
									/* $(".content-list").append("<span>mapList[i].shopNo</span>"); */	
									/* data.mList[0].shopNo */
							 	}
							/*}else {
								
							} */
								
						},
						error: function() {
							alert("서버에 연결할 수 없습니다.");
							return false;
						}
					});
				}
			});
		}
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