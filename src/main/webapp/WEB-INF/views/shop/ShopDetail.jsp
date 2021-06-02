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
							<li><a href="javascript:void(0);" onclick="shopReviewAll(1)">전체후기</a></li>
							<li><a href="javascript:void(0);" onclick="drReviewAll(1)">감사후기</a></li>
							<li><a href="javascript:void(0);" onclick="mzReviewAll(1)">맛집후기</a></li>
						</ul>
					</div>
				</div>
	
		    <div class="frame-review">
					
			
		    	<div id="list-body" class="review-list"> <!-- 처음에 보여질 후기 갯수 / 작성날짜, 닉네임 안들어가도 되는지 확인 -->
	 		 		
	 		 		
			 		<span class="review-yet"></span>
	 		 		<%-- <c:if test="${ empty rList }">
			 		</c:if> --%> 
			 		<c:if test="${ !empty rList }">
					 	<c:forEach items="${ rList }" var="reviewAll">
							 <div id="list" class="review-list rContent">
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
							<input type="button" id="moreReply" value="더보기"> <!-- onclick="moreList()" -->
						</div>
			 		</c:if> 
			 		
			 		
			 		
	 		 		
	 		 		
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
	
	// 아예 쿼리문 다시 짜기 - 데이터 전부 조회해서 5개만 보여주기,, 어떻게?
			 		
	
		// 조회 인덱스
		var startNum = 1; // 인덱스 초기값
		var countNum = 5; // 5개씩 로딩
		
		// 페이지 로딩 시 첫 실행
		shopReviewAll(startNum);
		//drReviewAll(startNum);
		
		/* // 더보기 클릭시
		$("#moreReply").on("click", function(){
			console.log("좋은말로 할때 돼라");
			startNum += countNum; // 누적
			shopReviewAll(startNum); // 초기값 - 위에서 바뀜(누적)
		});
		 */
		
		// 더보기 전체 실행함수
		function shopReviewAll(index){
			
			// 조회 인덱스 (다시 초기화)
// 			var startNum = 1; // 인덱스 초기값
// 			var countNum = 5; // 5개씩 로딩
			
			
			// 인덱스 초기화(다시 실행할 때 누적)
			// 중복(전체 - 리스트 초기화)
			// 리스트 초기화하면 페이징처리처럼 됨.
			// 안 하면 계속 누적...
			
			// 각 함수 호출할 때마다 리스트 비우고 그 이후론 누적(처음 호출할 때 한 번만 비워야 함)
			// 더보기 버튼은 데이터가 페이징(시작 + 끝)보다 적으면 사라짐.
			// 함수 호출할 때 인덱스(시작, 끝)값도 초기화해줘야 함
			
			// 시작값 초기화 완료
			// 이제 남은 건
			// 호출하면 list 싹다 비우기 - 한번만 비우고 싶음.. 근데 더보기 할 때마다 비워짐
				// 버튼 클릭을 밖으로 빼보기, 버튼 전역변수로 선언
			// + 후기 없을 때 처리 + 전체에서 감사, 맛집 나누기
			
			
			
			startNum = index;
			var endNum = index + countNum - 1; // 끝값 설정 (초기~끝 검색)
			console.log(startNum);console.log(endNum);
			
			$.ajax({
				url: "moreAllReview.dz",
				type: "GET",
				dataType: "json",
				data: {		// hashMap 으로 - x
					shopNo: '${shop.shopNo}',
					startNum: index, // 쿼리문에서 초기값
					endNum: endNum
				},
				success: function(data){ // textStatus -?
					$("#list-body").empty(); // 비우기?
				if(data == null){
					console.log("data는 null");
					$(".review-yet").html("등록된 후기가 없습니다.");
				} else{
					console.log(data);
					console.log(data.dataCnt);
					
					if(data.dataCnt == 0){
						console.log("data는 0");
						
						var contentList = $("#list-body");
						
						
						$(".review-yet").html("등록된 후기가 없습니다.");
					}
					
					//var addListHtml = ""; 	// list-body
						var contentList = $("#list-body");
						//$("#list-body").empty();
						
						for(i=0; i<data.rList.length; i++){
							
							// += html
							var contentReview = $("<div id='list' class='review-list rContent'>");
							var contentReviewLeft = $("<div class='rContent left'>");
							var contentReviewRight = $("<div class='rContent right'>");
							
							contentReviewLeft.append("<img src='/resources/images/shopMainImg/realPasta.jpeg' alt='shopMain'>");
							contentReviewRight.append("<span class='review-title'>" + data.rList[i].drmReviewTitle + "</span>&nbsp;&nbsp;")
												.append("<span class='review-type'>감사후기</span><br>")
												.append("<span>" + data.rList[i].drmReviewContent + "</span><br>");
							contentReview.append(contentReviewLeft);
							contentReview.append(contentReviewRight);
							contentList.append(contentReview);
							
						}
						//$(addListHtml).appendTo($("#list-body")).slideDown();
						
						// 더보기 버튼 삭제
						if(startNum + countNum > data.dataCnt){
							moreBtnDiv.remove();
							//$("#list-body").empty(); // 다 끝나서 버튼 지우면서 비우..면 안되지 않나? 마지막거가 사라짐 - 안사라짐.. 호출할 때 비워야함
						} else{
							// 더보기 버튼 그려보자!
							var moreBtnDiv = $("<div class='review-list showMoreReply'>");
							//var moreBtnInput = "<input type='button' id='moreReply' value='더보기'>";
								moreBtnDiv.append("<input type='button' id='moreReply' value='더보기'>");
							console.log("다시 그렸나요?");
							contentList.append(moreBtnDiv);
						} // 뭐보다 작으면 버튼 삭제...
						
						
						
						
						// 더보기 클릭시
						// 가져올 후기가 더이상 없으면 버튼을 없앤다.
						$("#moreReply").on("click", function(){
							console.log("좋은말로 할때 돼라");
							startNum += countNum; // 누적
							shopReviewAll(startNum); // 초기값 - 위에서 바뀜(누적)
							moreBtnDiv.remove(); // 지워도 상관 없는 듯 - 아님..
						});
					}
				},
				error: function(){
					console.log("실패");
				}
			});
		}
		
		
		// 더보기 - 감사후기 5개
		function drReviewAll(index){

			// 조회 인덱스 (다시 초기화)
			//var startNum = 1; // 인덱스 초기값
			//var countNum = 5; // 5개씩 로딩
			
			startNum = index;
			var endNum = index + countNum - 1;
			console.log(startNum);console.log(endNum);
			
			$.ajax({
				url: "moreDreamReview.dz",
				type: "GET",
				dataType: "json",
				data: {
					shopNo: '${shop.shopNo}',
					startNum: index,
					endNum: endNum
				},
				success: function(data){
				$("#list-body").empty(); // 비우기
					
				if(data == null){
						$(".review-yet").html("등록된 후기가 없습니다.");
				} else{
					console.log(startNum); console.log(endNum); 
					console.log(data);
					console.log(data.dataCnt);
					
					var contentList = $("#list-body");
					
					for(i=0; i<data.drList.length; i++){
						var contentReview = $("<div id='list' class='review-list rContent'>");
							var contentReviewLeft = $("<div class='rContent left'>");
							var contentReviewRight = $("<div class='rContent right'>");
							
							contentReviewLeft.append("<img src='/resources/images/shopMainImg/realPasta.jpeg' alt='shopMain'>");
							contentReviewRight.append("<span class='review-title'>" + data.drList[i].drmReviewTitle + "</span>&nbsp;&nbsp;")
												.append("<span class='review-type'>감사후기</span><br>")
												.append("<span>" + data.drList[i].drmReviewContent + "</span><br>");
							contentReview.append(contentReviewLeft);
							contentReview.append(contentReviewRight);
							contentList.append(contentReview);
					}
					/* // 더보기 버튼 그려보자!
						var moreBtnDiv = $("<div class='review-list showMoreReply'>");
						//var moreBtnInput = "<input type='button' id='moreReply' value='더보기'>";
							moreBtnDiv.append("<input type='button' id='moreReply' value='더보기'>");
						
						contentList.append(moreBtnDiv); */
						

						// 더보기 버튼 삭제
						if(startNum + countNum > data.dataCnt){
							moreBtnDiv.remove();
							//$("#list-body").empty();
						} else{
							// 더보기 버튼 그려보자!
							var moreBtnDiv = $("<div class='review-list showMoreReply'>");
								moreBtnDiv.append("<input type='button' id='moreReply' value='더보기'>");
							
							contentList.append(moreBtnDiv);
						} // startNum+countNum(페이징 수) 이 데이터 수보다 크면 버튼 삭제
						
					
						// 더보기 클릭시
		 				$("#moreReply").on("click", function(){
		 					console.log("좋은말로 할때 돼라");
		 					startNum += countNum; // 누적
		 					drReviewAll(startNum); // 초기값 - 위에서 바뀜(누적)
		 					moreBtnDiv.remove();
		 				});
					}
				}, 
				error: function(){
					console.log("실패");
				}
			});
		}
		
		

		// 더보기 - 맛집후기 5개
		function mzReviewAll(index){

			/* // 조회 인덱스 (다시 초기화)
			var startNum = 1; // 인덱스 초기값
			var countNum = 5; // 5개씩 로딩 */
			
			startNum = index;
			var endNum = index + countNum - 1;
			console.log(startNum); console.log(endNum); 
			
			$.ajax({
				url: "moreMzReview.dz",
				type: "GET",
				dataType: "json",
				data: {
					shopNo: '${shop.shopNo}',
					startNum: index,
					endNum: endNum
				},
				success: function(data){
				$("#list-body").empty(); // 비우기
					
				if(data == null){
						$(".review-yet").html("등록된 후기가 없습니다.");
				} else{
					console.log("맛집시작no ", startNum); console.log("맛집끝no ", endNum); 
					console.log(data);
					console.log("맛집 dataNum ", data.dataCnt);
					
					var contentList = $("#list-body");
					
					for(i=0; i<data.mzList.length; i++){
						var contentReview = $("<div id='list' class='review-list rContent'>");
							var contentReviewLeft = $("<div class='rContent left'>");
							var contentReviewRight = $("<div class='rContent right'>");
							
							contentReviewLeft.append("<img src='/resources/images/shopMainImg/realPasta.jpeg' alt='shopMain'>");
							contentReviewRight.append("<span class='review-title'>" + data.mzList[i].mReviewTitle + "</span>&nbsp;&nbsp;")
												.append("<span class='review-type'>맛집후기</span><br>")
												.append("<span>" + data.mzList[i].mReviewContent + "</span><br>");
							contentReview.append(contentReviewLeft);
							contentReview.append(contentReviewRight);
							contentList.append(contentReview);
					}
						// 더보기 버튼 삭제
						if(startNum + countNum > data.dataCnt){
							moreBtnDiv.remove();
						} else{
							// 더보기 버튼 그려보자!
							var moreBtnDiv = $("<div class='review-list showMoreReply'>");
								moreBtnDiv.append("<input type='button' id='moreReply' value='더보기'>");
							
							contentList.append(moreBtnDiv);
						} /// startNum+countNum(페이징 수) 이 데이터 수보다 크면 버튼 삭제
						
						
						// 더보기 클릭시
		 				$("#moreReply").on("click", function(){
		 					console.log("좋은말로 할때 돼라");
		 					startNum += countNum; // 누적
		 					mzReviewAll(startNum); // 초기값 - 위에서 바뀜(누적) - 다시 실행 - 계속 누적
		 					console.log("더보기 클릭시 startNum", startNum);
		 					moreBtnDiv.remove();
		 				});
					}
				}, 
				error: function(){
					console.log("실패");
				}
			});
			
		}
		
		
		

</script>
</body>
</html>