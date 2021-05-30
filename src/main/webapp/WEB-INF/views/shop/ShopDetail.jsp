<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- css -->
<link rel="stylesheet" type="text/css" href="/resources/css/shop/ShopDetail.css">

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />

<!-- 지도 -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1683794343a4e97ff3195b44b6488d0c&libraries=services"></script>
<title>가게 상세 페이지</title>
</head>
<body>

	<jsp:include page="/WEB-INF/views/common/menubar.jsp"/>
		
		 <div class="header-background-area">
		 	<!-- 가게 메인 이미지 가져오기 -->
		 	<img src="/resources/images/shopMainImg/realPasta.jpeg" alt="shopMain">
	   	</div>
		<div id="shop-header">
			<div id="shop-main-title">
				<span>${ shop.shopName }</span>
				<span id="shop-main-title-type">${ shop.shopType }</span><br>
				<span id="shop-main-title-provide">${ shop.shopProduct }</span>
			
				<!-- 사업자 회원 제외 찜버튼 활성화 -->
				<!-- <div id="pick-zone"> -->
	 				<c:if test="${ loginUser.userType != 3 }">
		 				<c:if test="${ empty pick }">		
		 					<a href="enrollPick.dz" id="pick-button"><!-- <img src="/resources/images/zzimButton-before.png" alt="pick-button"> --></a>
		 				</c:if>
	 					<c:if test="${ !empty pick }">		
							<!-- 세션 체크 하여 동작 -->
							<!-- 세션 없을시 로그인 연결 -->
							<!-- 컨트롤러에서 세션 체크해서 userNo 같이 가져가기 -->
							<c:url var="reservation" value="reservationView.dz">
								<c:param name="shopNo" value="${ shop.shopNo }"/>
							</c:url>
							<a href="removePick.dz" id="pick-button"><img src="/resources/images/zzimButton-after.png" alt="pick-button"></a>
						</c:if> 
					</c:if>
				<!-- </div> -->
			</div>
		</div>
		
	<main>
		<div class=frame>
			<div class="shop-detail">
				<div class="detailAll line1">
					<div class="detail-left">휴무일</div>
					<div class="detail-right">${ shop.businessDay }</div>
				</div>
				
				<div class="detailAll line2">
					<div class="detail-left">영업시간</div>
					<div class="detail-right">${ shop.startTime }:00 ~ ${ shop.endTime }:00</div>
				</div>
				
				<div class="detailAll line3">
					<div class="detail-left">대표메뉴</div>
					<div class="detail-right">
						<div class="detail-right menu-list">
							<c:if test="${ !empty mainMenu }">
								<c:forEach items="${mainMenu }" var="mainMenu">
									${ mainMenu.mainMenuName }&nbsp;&nbsp;${ mainMenu.mainMenuPrice }원 <br>
								</c:forEach>
							</c:if>
							
							<!-- modal test 중.. -->
							<div id="menu-img" title="클릭하면 창이 닫힙니다.">
								<div>
									<img src="/resources/images/snsPhoto.png" alt="menuImg" width="300px" >
								</div>
							</div>

							<img src="/resources/images/snsPhoto.png" class="menu-img-thumb" alt="menuImg" width="90px">
							<img src="/resources/images/snsPhoto.png" class="menu-img-thumb" alt="menuImg" width="90px">
							<img src="/resources/images/snsPhoto.png" class="menu-img-thumb" alt="menuImg" width="90px">
							
							<script>
								$(function(){
									$(".menu-img-thumb").click(function() {
										$("#menu-img").fadeIn();
									});
									$("#menu-img").click(function() {
										$("#menu-img").fadeOut();
									});
								});
							</script>
							
						</div>
						<c:if test="${ !empty mPhoto }">
							<div class="detail-right menu-img"> <!-- 이미지파일 여러개 생성 ( 미리보기 가능 ) ( 최대 몇개 ? ) -->
								<img src="" alt="menuImg">
							</div> 
						</c:if>
					</div>
				</div>
				
				<div class="detailAll line4">
					<div class="detail-left">주차여부</div>
					<div class="detail-right">
						<c:if test="${ shop.shopParking eq 'Y' }">
							주차가능
						</c:if>
						<c:if test="${ shop.shopParking eq 'N' }">
							주차불가
						</c:if>
					</div>
				</div>
				
				<div class="detailAll line5">
					<div class="detail-left">상세내용</div>
					<c:if test="${empty shop.shopContent }">
						<div class="detail-right conInfo">&nbsp;</div> <!-- 최대 몇글자? -->
					</c:if>
					<c:if test="${!empty shop.shopContent }">
						<div class="detail-right conInfo">${ shop.shopContent }</div> <!-- 최대 몇글자? -->
					</c:if>
				</div>
				
				<div class="detailAll line6">
					<div class="detail-left">연락처</div>
					<div class="detail-right">${ shop.shopPhone }</div>
				</div>
				
				<div class="detailAll line7" style="height: 550px;">
					<div class="detail-left">
						<div class="detail-left top">주소</div>
						<div class="detail-left middle"></div>
						<div class="detail-left bottom"></div>
					</div>
					<div class="detail-right">
						<div class="detail-right top">${ shop.shopAddr }</div>
						<div id="map" class="detail-right bottom"></div>
						<div class="detail-right buttons">
							<c:url var="reservation" value="reservationView.dz">
								<c:param name="shopNo" value="${ shop.shopNo }"/>
								<c:param name="shopName" value="${ shop.shopName }"/>
								<c:param name="startTime" value="${ shop.startTime }"/>
								<c:param name="endTime" value="${ shop.endTime }"/>
								<c:param name="businessDay" value="${ shop.businessDay }"/>
								<c:param name="shopMaxReserv" value="${ shop.shopMaxReserv }"/>
							</c:url>
							<c:url var="donation" value="paymentFormView.dz">
								<c:param name="shopNo" value="${ shop.shopNo }"/>
								<c:param name="shopName" value="${ shop.shopName }"/>
							</c:url>
						
						<!-- 회원별 버튼 생성 -->
						<!-- 비회원 : 로그인 연결 -->
							<c:if test="${ empty loginUser }">
								<ul>
									<li><a href="javascript:void(0);" onclick="goLogin()">예약하기</a></li>
									<li><a href="javascript:void(0);" onclick="goLogin()">돈쭐내기</a></li>
									<li><a href="javascript:history.back();">목록으로</a></li>
								</ul>
							</c:if>
						
							<!-- 회원 -->
							<c:if test="${ !empty loginUser }">
								<!-- 꿈나무회원 -->
								<c:if test="${ loginUser.userType == 1 }">
									<ul>
										<li><a href="${ reservation }" class="two-buttons">예약하기</a></li>
										<li><a href="javascript:history.back();" class="two-buttons">목록으로</a></li>
									</ul>
								</c:if>
								
								<!-- mz -->
								<c:if test="${ loginUser.userType == 2 }">
									<ul>
										<li><a href="${ reservation }">예약하기</a></li>
										<li><a href="${ donation }">돈쭐내기</a></li>
										<li><a href="javascript:history.back();">목록으로</a></li>
									</ul>
								</c:if>
								
								<!-- 사업자 -->
								<c:if test="${ loginUser.userType == 3 }">
									<ul>
										<li><a href="javascript:history.back();" class="one-button">목록으로</a></li>
									</ul>
								</c:if>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
			
				
	<!-- 예약후기 -->
		<div class="review">
				<div class="review-background-area">
				 	<img src="/resources/images/review-title.png" alt="shopMain">
				</div>
				<div class="review-title-area">
					<span id="review-title">예약후기</span>&nbsp;&nbsp;&nbsp;&nbsp;
					<span id="review-title-sub">솔직하고 따뜻한 후기</span>
					
					<div id="review-tab">
						<ul>
							<li><a href="javascript:void(0);" onclick="shopReviewAll()">전체후기</a></li>
							<li><a href="javascript:void(0);" onclick="drReviewAll()">감사후기</a></li>
							<li><a href="javascript:void(0);" onclick="mzReviewAll()">맛집후기</a></li>
						</ul>
					</div>
				</div>
	
		    <div class="frame">
					
			
		    	<div id="list-body" class="review-list"> <!-- 처음에 보여질 후기 갯수 / 작성날짜, 닉네임 안들어가도 되는지 확인 -->
	 		 		
	 		 		
	 		 		<c:if test="${ empty rList }">
			 			<span class="review-yet">등록된 후기가 없습니다.</span>
			 		</c:if> 
			 		<c:if test="${ !empty rList }">
					 	<c:forEach items="${ rList }" var="reviewAll">
							 <div id="list" class="review-list rContent">
								<div class="rContent left">
									<img src="/resources/images/shopMainImg/realPasta.jpeg" alt="shopMain">
								</div>
								<div class="rContent right">
									<span class="review-title">${ reviewAll.mReviewTitle }</span>&nbsp;&nbsp;
									<span class="review-type">감사후기</span><br> <!-- 감사후기 공개 여부 확인해서 가져오기 -->
									<span>${ reviewAll.mReviewContent }</span><br>
								</div>
							</div>
						</c:forEach> 
						<div class="review-list showMoreReply">
							<!-- 클릭시 나올 후기 갯수?? -->
							<input type="button" id="moreReply" onclick="moreList()" value="더보기">
						</div>
			 		</c:if> 
			 		
			 		<script>
			 		
			 			function moreList(){
			 				
			 				// 변수 - 시작 번호(tr)
			 				var startNum = $(".review-title").length; // 마지막 리스트 번호 알아내기 위해 (div 넣어야하는데 title로 대체,, 안 나옴ㅠ)
			 				var addListHtml = ""; // 이건 뭔지 모르겠는데
			 				console.log("startNum", startNum);
			 				
			 				// ajax
			 				$.ajax({
			 					url: "shopDetail.dz",
			 					type: "post",
			 					dataType: "json", // 객체?로 변환
			 					data: { // 컨트롤로 보낼 데이터
			 						"startNum" : startNum
			 					},
			 					success: function(data){
			 						if(data.length < 5){ // 5보다 작으면
			 							$("#moreReply").remove(); // 왜 없애?..
			 							  // 더보기 버튼을 div 클래스로 줘야 할 수도 있음 - 뭔솔
			 						}else{
			 							var addListHtml = ""; // 이거 뭔데요.
			 							if(data.length > 5){ // 5보다 크면
			 								for(var i=0; i<data.length; i++){
			 									var index = Number(startNum) + Number(i) + 1;
			 									// 글번호 : startNum 이  10단위로 증가되기 때문에 startNum +i (+1은 i는 0부터 시작하므로 )
			 									addListHtml += "<div>";
			 									addListHtml += "<div>";
			 									addListHtml += "<img src='/resources/images/shopMainImg/realPasta.jpeg' alt='shopMain'>"; // 이미지 내용
			 									addListHtml += "</div>";
			 									addListHtml += "<div>";
			 									addListHtml += "<span>" + data[i].mReviewTitle + "</span>";
			 									addListHtml += "<span>" + data[i].mReviewContent + "</span>";
			 									addListHtml += "</div>";
			 									addListHtml += "</div>";
			 								}
			 								$("#list-body").append(addListHtml);
			 							}
			 						}
			 					}
			 				});
			 				
			 				
			 			}
			 		
			 		</script>
			 		
	 		 		
	 		 		
	 		 		<%-- <c:if test="${ empty drList }">
			 			<span class="review-yet">등록된 후기가 없습니다.</span>
			 		</c:if> 
			 		<c:if test="${ !empty drList }">
					 	<c:forEach items="${ drList }" var="reviewAll">
							 <div class="review-list rContent">
								<div class="rContent left">
									<img src="/resources/images/shopMainImg/realPasta.jpeg" alt="shopMain">
								</div>
								<div class="rContent right">
									<span class="review-title">${ reviewAll.drmReviewTitle }</span>&nbsp;&nbsp;
									<span class="review-type">감사후기</span><br> <!-- 감사후기 공개 여부 확인해서 가져오기 -->
									<span>${ reviewAll.drmReviewContent }</span><br>
								</div>
							</div>
						</c:forEach> 
						<div class="review-list showMoreReply">
							<!-- 클릭시 나올 후기 갯수?? -->
							<input type="button" id="moreReply" onclick="" value="더보기">
						</div>
			 		</c:if> --%> 
			 		
			 		
				</div>
		    </div>
		</div>
	
	</main>	

	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	
	<!-- 맵 js -->
	<script>
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = { 
		        center: new kakao.maps.LatLng(37.55021, 126.92327), // 지도의 중심좌표
		        level: 3 // 지도의 확대 레벨
		    };
		
		var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
		
		 // 마우스 휠로 지도 확대,축소 가능여부를 설정합니다
	    map.setZoomable(false); 
		
		// 주소-좌표 변환 객체를 생성합니다
		var geocoder = new kakao.maps.services.Geocoder();
		
			// 주소로 좌표를 검색합니다
			geocoder.addressSearch('${shop.shopAddr}', function(result, status) {
				
			/* console.log(shop.shopAddr); */
			
			    // 정상적으로 검색이 완료됐으면 
			     if (status === kakao.maps.services.Status.OK) {
			    	 
			     
			    	 var imageSrc = '/resources/images/map_marker_blue.png', // 마커이미지의 주소입니다    
					    imageSize = new kakao.maps.Size(27, 35); // 마커이미지의 크기입니다
					      
					// 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
					var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize),
					    markerPosition = new kakao.maps.LatLng(result[0].y, result[0].x); // 마커가 표시될 위치입니다
					
					    console.log(markerPosition);
					// 마커를 생성합니다
					var marker = new kakao.maps.Marker({
					    position: markerPosition, 
					    image: markerImage // 마커이미지 설정 
					});
					
					// 마커가 지도 위에 표시되도록 설정합니다
					marker.setMap(map);  
					
					/* console.log(shop.shopName); */
					
					/// 커스텀 오버레이에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
					var content = '<div class="customoverlay">' +
					    '  <a href="javascript:void(0);">' +
					    '    <span class="title">${shop.shopName}</span>' +
					    '  </a>' +
					    '</div>';
					
					// 커스텀 오버레이가 표시될 위치입니다 
					var position = new kakao.maps.LatLng(result[0].y, result[0].x); 
					
					// 커스텀 오버레이를 생성합니다
					var customOverlay = new kakao.maps.CustomOverlay({
					    map: map,
					    position: position,
					    content: content,
					    yAnchor: 1 
					});
			    	
					// 지도의 중심좌표 세팅 
				    map.setCenter(markerPosition); 
			     }
			});
			
			
			function goLogin() {
				location.href='loginView.dz';
			}
	</script>
</body>
</html>