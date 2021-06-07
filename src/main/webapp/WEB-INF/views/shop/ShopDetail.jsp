<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- css -->
<link rel="stylesheet" type="text/css" href="/resources/css/shop/ShopDetail.css">

<!-- 모달 -->
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
			
				<!-- 찜버튼 --> 
				<!-- 컨트롤러에서 세션 체크해서 userNo 같이 가져가기 --> 
				<!-- 꿈나무, MZ --> 		
 				<c:if test="${ loginUser.userType == 1 || loginUser.userType == 2 }">
 					<c:if test="${ empty pick.pickNo }"> <!-- 컨트롤러에서 세션 체크해서 찜 가져오기 -->
						<a href="#" id="pick-button-off"></a>
	 				</c:if>
 					<c:if test="${ !empty pick.pickNo }"> <!-- 컨트롤러에서 세션 체크해서 찜 가져오기 -->
						<a href="#" id="pick-button-on"></a>
	 				</c:if>
 				</c:if>
				<!-- 세션 없을시 로그인 연결 -->
 				<c:if test="${ loginUser.userType == null }">
					<a href="javascript:void(0);" onclick="goLogin()" id="pick-button-disabled"></a>
 				</c:if>
 				<c:if test="${ loginUser.userType == 3 }"></c:if>
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
								<table class="menuCon">
									<tbody>
										<c:forEach items="${mainMenu }" var="mainMenu">
											<tr>
												<th class="menuName">${ mainMenu.mainMenuName }</th>
												<td class="menuPrice">${ mainMenu.mainMenuPrice }원</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</c:if>
							<div class="menu-img-frame">
								<c:forEach items="${mPhoto }" var="photo" varStatus="status">
									<div class="detail-right menu-img menu-img-thumb-div"> <!-- 이미지파일 여러개 생성 ( 미리보기 가능 ) ( 최대 몇개 ? ) -->
										<img src="${photo.menuFilePath }/${photo.menuFileName }" id="menu-img-thumb" class="menu-img-thumb" alt="menuImg">
									</div> 
									
									<div id="img-modal" title="X를 클릭하면 창이 닫힙니다." onclick="imgModalClose();" class="detail-right menu-img menu-img-detail-div modal">
									   <span>X</span>
									   <img src="${photo.menuFilePath }/${photo.menuFileName }" id="img-modal-content" class="menu-img-detail" alt="menuImg"  width="300">
									    <!-- <img id="img-modal-content" src="/resources/images/snsPhoto.png" alt="텍스트 예제 1" width="300"> -->
									</div>
								</c:forEach>
							</div>
						</div>
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
					<div class="detail-right detail-end">
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
							<c:if test="${ empty loginUser && empty sessionScope.kakaoId && empty sessionScope.googleId }">
								<ul>
									<li><a href="${ reservation }">예약하기</a></li>
									<li><a href="${ donation }">돈쭐내기</a></li>
									<li><a href="javascript:history.back();">목록으로</a></li>
								</ul>
							</c:if>
						
							<!-- 회원 -->
							<c:if test="${ !empty loginUser || !empty sessionScope.kakaoId || !empty sessionScope.googleId }">
								<!-- 꿈나무회원 -->
								<c:if test="${ loginUser.userType == 1 }">
									<ul class="two-buttons-all">
										<li><a href="${ reservation }" class="two-buttons">예약하기</a></li>
										<li><a href="javascript:history.back();" class="two-buttons">목록으로</a></li>
									</ul>
								</c:if>
								
								<!-- mz -->
								<c:if test="${ loginUser.userType == 2 || !empty sessionScope.kakaoId || !empty sessionScope.googleId }">
									<ul>
										<li><a href="${ reservation }">예약하기</a></li>
										<li><a href="${ donation }">돈쭐내기</a></li>
										<li><a href="javascript:history.back();">목록으로</a></li>
									</ul>
								</c:if>
								
								<!-- 사업자 -->
								<c:if test="${ loginUser.userType == 3 }">
									<ul class="one-button-all">
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
		    	<div id="list-body" class="review-list"></div>
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
			     
			    	 var imageSrc = '/resources/images/map/map_marker_blue.png', // 마커이미지의 주소입니다    
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
						'   <div class="tail">' + 
					    '    <span class="title">${shop.shopName}</span>' +
					    '	</div>' +
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
			
			
		/* 로그인창 이동 */	
		function goLogin() {
			location.href='loginView.dz';
		}
	
		
		/* 찜 등록 */
		var shopNo = '${ shop.shopNo }';
		var userNo = '${ loginUser.userNo }';
		var pickNo = '${ pick.pickNo }';
		
		$("#pick-button-off").on("click", function() {
			$.ajax({
				url: "enrollPick.dz",
				type: "post",
				data: { "shopNo" : shopNo,
						"userNo" : userNo },
				success: function(data) {
					if(data == "success") {
						alert("'가고싶다' 목록에 추가되었습니다.");
					}else {
						alert("'가고싶다' 목록 추가 실패하였습니다.");
					}
					location.href="/shopDetail.dz?shopNo="+shopNo;
				},
				error: function() {
					console.log("연결 실패하였습니다.");
				}
			});
		});
		
		/* 찜 해제 */
		$("#pick-button-on").on("click", function() {
			$.ajax({
				url: "removePick.dz",
				type: "post",
				data: { "pickNo" : pickNo },
				success: function(data) {
					if(data == "success") {
						alert("'가고싶다' 목록에서 삭제되었습니다.");
					}else {
						alert("'가고싶다' 목록 삭제 실패하였습니다.");
					}
					location.href="/shopDetail.dz?shopNo="+shopNo;
				},
				error: function() {
					console.log("연결 실패하였습니다.");
				}
			});
		});
		
		
		// test~~~~~~~~~~~~~~~
		/* 이미지 클릭 시 */
		var imageTagList = document.querySelectorAll(".menu-img-thumb-div img");
		console.log("나오나"+imageTagList);

		for (var i = 0; i < imageTagList.length; i++) {
			imageTagList[i].addEventListener('click', function() {
				var modal = document.getElementById('img-modal');
				var content = document.getElementById('img-modal-content');
				modal.style.display = 'block';
				/* var modal = $('#img-modal');
				var content = $('#img-modal-content');
				modal.show(); */
				content.src = this.src;
			});
		}

		/* close 버튼 클릭시*/
		function imgModalClose() {
			var modal = document.getElementById('img-modal');
			var content = document.getElementById('img-modal-content');
			modal.style.display = "none";
			content.src = '';
			//content.src = '/resources/images/snsPhoto.png';
		}	
	
		
	
		/* 후기 띄우기(전체/감사/맛집) */
		
		// 조회 인덱스
		var startNum = 1; // 인덱스 초기값
		var countNum = 5; // 5개씩 로딩
		
		// 페이지 로딩 시 첫 실행
		shopReviewAll(startNum);
		
		// 더보기 전체 실행함수
		function shopReviewAll(index){
			
			if(index == 1) {
				$("#list-body").empty(); // 비우기
			}
			
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
				if(data == null){
					console.log("data는 null");
					$(".review-yet").html("등록된 후기가 없습니다.");
				} else{
					console.log(data);
					console.log(data.dataCnt);
					
					if(data.dataCnt == 0){
						console.log("data는 0");
						var contentList = $("#list-body");
						var contentReview = $("<span class='review-yet'>");
						contentList.append(contentReview);
						$(".review-yet").html("등록된 후기가 없습니다.");
					}
					
					//var addListHtml = ""; 	// list-body
						var contentList = $("#list-body");
						
						for(i=0; i<data.rList.length; i++){
							
							// += html
							var contentReview = $("<div id='list' class='review-list rContent'>");
							var contentReviewLeft = $("<div class='rContent left'>");
							var contentReviewRight = $("<div class='rContent right'>");
							
							contentReviewRight.append("<span class='review-title'>" + data.rList[i].drmReviewTitle + "</span>&nbsp;&nbsp;");
								
							if(data.rList[i].drmReviewPublicYN == "MZ"){
								contentReviewLeft.append("<img src='/resources/fileupload/' alt='shopMain'>");
	                            contentReviewRight.append("<span class='review-type'>맛집후기</span>")
	                          					  .append("<a href='mReviewDetail.dz?mzReviewNo=" + data.rList[i].drmReviewNo + "' class='review-detail'>더보기</a><br>");
	                        }else{
	                        	contentReviewLeft.append("<img src='/resources/images/shop/reviewEmptyPic.png' alt='shopMain'>");	
	                            contentReviewRight.append("<span class='review-type'>감사후기</span>")
	                          					  .append("<a href='dReviewDetail.dz?drmReviewNo=" + data.rList[i].drmReviewNo + "' class='review-detail'>더보기</a><br>");
	                        }

							contentReviewRight.append("<div class='review-content'>" + data.rList[i].drmReviewContent + "</div><br>");
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

			if(index == 1) {
				$("#list-body").empty(); // 비우기
			}
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
					
				if(data == null){
						$(".review-yet").html("등록된 후기가 없습니다.");
				} else{
					console.log(startNum); console.log(endNum); 
					console.log(data);
					console.log(data.dataCnt);
					
					if(data.dataCnt == 0){
						console.log("data는 0");
						var contentList = $("#list-body");
						var contentReview = $("<span class='review-yet'>");
						contentList.append(contentReview);
						$(".review-yet").html("등록된 후기가 없습니다.");
					}
					
					var contentList = $("#list-body");
					
					for(i=0; i<data.drList.length; i++){
						var contentReview = $("<div id='list' class='review-list rContent'>");
							var contentReviewLeft = $("<div class='rContent left'>");
							var contentReviewRight = $("<div class='rContent right'>");
							
							contentReviewLeft.append("<img src='/resources/images/shop/reviewEmptyPic.png' alt='shopMain'>");
							contentReviewRight.append("<span class='review-title'>" + data.drList[i].drmReviewTitle + "</span>&nbsp;&nbsp;")
											  .append("<span class='review-type'>감사후기</span>")
											  .append("<a href='dReviewDetail.dz?drmReviewNo=" + data.drList[i].drmReviewNo + "' class='review-detail'>더보기</a><br>")
											  .append("<div class='review-content'>" + data.drList[i].drmReviewContent + "</div><br>");
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

			if(index == 1) {
				$("#list-body").empty(); // 비우기
			}
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
					
				if(data == null){
						$(".review-yet").html("등록된 후기가 없습니다.");
				} else{
					console.log("맛집시작no ", startNum); console.log("맛집끝no ", endNum); 
					console.log(data);
					console.log("맛집 dataNum ", data.dataCnt);
					
					if(data.dataCnt == 0){
						console.log("data는 0");
						var contentList = $("#list-body");
						var contentReview = $("<span class='review-yet'>");
						contentList.append(contentReview);
						$(".review-yet").html("등록된 후기가 없습니다.");
					}
					
					var contentList = $("#list-body");
					
					for(i=0; i<data.mzList.length; i++){
						
						var contentReview = $("<div id='list' class='review-list rContent'>");
						var contentReviewLeft = $("<div class='rContent left'>");
						var contentReviewRight = $("<div class='rContent right'>");
						
						/* 맛집 후기 사진 없을 때 */
						/* contentReviewLeft.append("<img src='/resources/images/shop/reviewEmptyPic.png' alt='shopMain'>"); */
						
						contentReviewLeft.append("<img src='/resources/images/shopMainImg/realPasta.jpeg' alt='shopMain'>");
						contentReviewRight.append("<span class='review-title'>" + data.mzList[i].mReviewTitle + "</span>&nbsp;&nbsp;")
										  .append("<span class='review-type'>맛집후기</span>")
										  .append("<a href='mReviewDetail.dz?mzReviewNo=" + data.mzList[i].mReviewNo + "' class='review-detail'>더보기</a><br>")
										  .append("<div class='review-content'>" + data.mzList[i].mReviewContent + "</div><br>");
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