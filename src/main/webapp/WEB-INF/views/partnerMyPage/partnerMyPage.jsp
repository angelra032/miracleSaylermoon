<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사업자 마이페이지</title>
<link rel="stylesheet"
	href="/resources/css/partnermypage/partnerMyPage.css">
<!-- <link rel="stylesheet" href="/resources/css/mzmypage/mzmypage.css">  -->
<!-- <link href="/static/bootstrap.min.css" rel="stylesheet"> -->

<!-- fullcander -->
<link href='/resources/css/partnermypage/main.css' rel='stylesheet' />
<script src='/resources/css/partnermypage/main.js'></script>
<script src='/resources/css/partnermypage/ko.js'></script>
<!-- modal -->
<script	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />
</head>
<body>

	<jsp:include page="/WEB-INF/views/common/mypagemenubar.jsp"></jsp:include>
	<main>
		<div id="main-title">${ loginUser.userName }님안녕하세요!</div>
		<div class="frame">
			<div class="my-info">
				<span>보유포인트 : <b>${shop.shopPoint }</b>원</span> 
				<c:if test="${ !empty shop }">
					<a class="refund-btn" href="refundsPartnerPoint.dz">환급신청</a>
				</c:if>
				<div class="info-btn-frame">
					<c:if test="${ !empty shop }">
						<a class="info-btn" href="shopUpdateView.dz">가게 수정</a>
					</c:if>
					<c:if test="${ empty shop }">
						<a class="info-btn" href="shopRegisterView.dz">가게 등록</a>
					</c:if>
					<!-- 등록/수정 -->
					<a class="info-btn" href="myINfo.dz?userNo=${loginUser.userNo }">나의
						정보</a> <a class="info-btn" href="partnerWritePwView.dz">탈퇴 요청</a>
					<!-- 사업자userDeleteRequest.dz -->
				</div>
			</div>
		</div>

		<!-- 달력 시작 -->
		<div class="my-list w-list reserv-calendar">
			<div class="frame">
				<div class="my-title">
					<span id="reserve-title">예약 현황</span>
					<div class="more-btn-frame">
						<a class="more-btn b-btn" id="reserve-cancle">예약취소</a> 
						<a class="more-btn b-btn" id="reserve-comfirm">예약확정</a> 
						<a class="more-btn b-btn" id="reserve-default">예약대기</a>
					</div>
				</div>
				<div class="calendar_frame">
					<div id='calendar'></div>
				</div>
			</div>
		</div>
		<!-- 달력 끝 -->

		<div class="my-list  reserv-list">
			<div class="frame">
				<div class="my-title">
					<span>예약 관리</span>
					<div class="more-btn-frame">
						<a class="more-btn b-btn" href="partnerReserveList.dz">더보기</a>
					</div>
				</div>
				<table>
					<thead>
						<tr>
							<th>No</th>
							<th>회원닉네임</th>
							<th>예약인원</th>
							<th>예약날짜</th>
							<th>예약상태</th>
							<th>방문완료</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${ !empty rList }">
							<c:forEach items="${rList }" var="reservation" varStatus="status">
								<tr>
									<td>${status.count }</td>
									<input type="hidden" name="reservationNo" value="${reservation.reservationNo }">
									<input type="hidden" name="shopNo" value="${reservation.shopNo }">
									<td><a class="table-link-title" href="#"><p>${reservation.userNick }</p></a></td>
									<td>${reservation.reserveCount }명</td>
									<td>${reservation.reserveDate }</td>
									<td>
										<c:choose>
											<c:when test="${reservation.rState eq 'O'}">
												<select name="rState" class="select-rstate" id="rState">
													<option value="O" selected disabled>예약대기</option>
													<option value="Y"
														${reservation.rState eq 'Y' ? 'selected="selected"' : '' }>예약승인</option>
													<!-- reservation.rState eq 'Y' 결과값이 true일때 실행되는 구간 : false일때 실행되는 구간(false일때는 아무 액션도 없기때문에 빈칸) -->
													<option value="X"
														${reservation.rState eq 'X' ? 'selected="selected"' : '' }>예약거부</option>
												</select>
											</c:when>
											<c:otherwise>
											${reservation.rState eq 'Y' ? '예약승인' : reservation.rState eq 'X' ? '예약거부' : reservation.rState eq 'C' ? '완료된 예약' : '' }
										</c:otherwise>
										</c:choose>
									</td>
	
									<c:if test="${reservation.rState eq 'O'}">
										<td>예약대기</td>
									</c:if>
									<c:if test="${reservation.rState eq 'X'}">
										<td>취소된예약</td>
									</c:if>
									<c:if test="${reservation.rState eq 'Y'}">
										<td><a class="reserv-btn"
											href="completeReservation.dz?reservationNo=${reservation.reservationNo }&rState=${reservation.rState }">방문완료</a></td>
									</c:if>
									<c:if test="${reservation.rState eq 'C'}">
										<td>완료된 예약</td>
									</c:if>
	
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${ empty rList }">
							<tr>
								<td colspan="6">${Rmsg }</td>
							</tr>
						</c:if>

					</tbody>
				</table>
			</div>
		</div>

		<div class="my-list w-list recommend-list">
			<div class="frame">
				<div class="my-title">
					<span>내가 쓴 문의글</span>
					<div class="more-btn-frame">
						<a class="more-btn b-btn" href="allQnaListByPartner.dz">더보기</a>
					</div>
				</div>
				<table>
					<thead>
						<tr>
							<th>No</th>
							<th>제목</th>
							<th>작성날짜</th>
							<th>수정</th>
							<th>삭제</th>
						</tr>
					</thead>
					<tbody class="qTbody">
						<c:if test="${ !empty qList }">
							<c:forEach items="${qList }" var="qna" varStatus="status">
								<tr>
									<td>${status.count }</td>
									<td><a class="table-link-title" href="qaDetail.dz?qnaNo=${qna.qnaNo }"><p>${ qna.qnaTitle}${qna.qnaNo }</p></a></td>
									<td class="qnaDate">${ qna.qanUploadDate }</td>
									<td><a class="modify-btn"
										href="qaUpdateForm.dz?qnaNo=${qna.qnaNo }">수정</a></td>
									<td><input type="hidden" value="${qna.qnaNo }"><a class="delete-btn" href="#">삭제</a></td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty qList }">
							<tr>
								<td colspan="5">${Qmsg }</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
		
		<!-- modal section -->
		<!-- <div id="ex1" class="ex1 modal">
		  <p>안녕하세요. 모달창안의 내용부분입니다.</p>
		  <a href="#" rel="modal:close">닫기</a>
		</div> -->
	</main>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>

	<script>
		$(".select-rstate").on(
				"change",
				function() {
					var rState = $(this).val();
					var reservationNo = parseInt($(this).closest("tr").find(
							"input[name='reservationNo']").val());
					var shopNo = parseInt($(this).closest("tr").find(
							"input[name='shopNo']").val());
					console.log(rState)
					$.ajax({
						url : "updateReservation.dz",
						type : "GET",
						data : {
							"rState" : rState,
							"reservationNo" : reservationNo,
							"shopNo" : shopNo
						},
						success : function() {
							location.reload();
						},
						error : function() {
							alert('예약 상태 변경이 실패하였습니다.');

						}
					});
				});
		document.addEventListener('DOMContentLoaded', function() {
			var calendarEl = document.getElementById('calendar');
			var calendar = new FullCalendar.Calendar(calendarEl, {
				editable : true,
				selectable : true,
				businessHours : true,
				locale : "ko",
				dayMaxEvents : 2,
				eventClick : function(info){
					alert('Event: ' + info.event.title);
					/* $(".ex1").modal({
						fadeDuration: 250
					}) */
				   
				},
				dayRender: function (date, cell) {
			       var check = $.fullCalendar.formatDate(date,'yyyy-MM-dd');
			                    var today = $.fullCalendar.formatDate(new Date(),'yyyy-MM-dd');
			                    if (check < today) {
			                        cell.css("background-color", "red");
			                    }
			    },
				eventSources : [ {
					events : function(info, successCallback, failureCallback) {
						$.ajax({
							url : "reservationStatue.dz",
							type : 'GET',
							dataType : 'json',
							data : { "shopNo" : "${shop.shopNo }", "rState" : "O"},
							success : function(data) {
								successCallback(data);
								console.log(data);
							}
						});
					}
				, color : "#fdb504"
		        , textColor : "#000000"
				}]
			});
			calendar.render();
		});
		
		
		$("#reserve-default").on("click",function(){
			reserveState("O", "#fdb504");
		});
		
		$("#reserve-comfirm").on("click",function(){
			reserveState("C", "#0160ff");
		});
		
		$("#reserve-cancle").on("click",function(){
			reserveState("X", "#888");
		});
		
		function reserveState(val, color){
			var calendarEl = document.getElementById('calendar');
			var calendar = new FullCalendar.Calendar(calendarEl, {
				editable : true,
				selectable : true,
				businessHours : true,
				locale : "ko",
				dayMaxEvents : 2,
				//themeSystem:'bootstrap3',
				//contentHeight: "auto",
				dayRender: function (date, cell) {
			       var check = $.fullCalendar.formatDate(date,'yyyy-MM-dd');
			                    var today = $.fullCalendar.formatDate(new Date(),'yyyy-MM-dd');
			                    if (check < today) {
			                        cell.css("background-color", "red");
			                    }
			    },
				eventSources : [ {
					events : function(info, successCallback, failureCallback) {
						$.ajax({
							url : "reservationStatue.dz",
							type : 'GET',
							dataType : 'json',
							data : { "shopNo" : "${shop.shopNo }", "rState" : val},
							success : function(data) {
								successCallback(data);
								console.log(data);
							}
						});
					}
				, color : color
		        , textColor : "#000000"
				}]
			});
			calendar.render();
		}
		
		$("#reserve-default").on("click",function(){
			reserveDefault();
		});
		
		//예약관리 모달창 열기
		function eventClick(arg){
			console.log(arg);
		}
		
		
		
		///// 어펜드 작업 시작
		var qnaDate = document.querySelectorAll('.qnaDate');
		for(var i = 0; i < qnaDate.length; i++){
			var qnaDateResult = qnaDate[i].innerHTML.substr(0,10);
			qnaDate[i].innerHTML = qnaDateResult;
		}
		
		
		$(document).on("click",".delete-btn",function(){
			var qnaNo =$(this).prev().val();
			$.ajax({
				url : "partnerMainQnaDelete.dz",
				type : "GET",
				data : {"qnaNo" : qnaNo},
				success : function(data){
					$('.qTbody').empty();
					for(var i in data){
						var dateResult = data[i].qanUploadDate.substr(0,10);
						
						var tr = $("<tr>");
						var count = $("<td>").html(Number(i)+1);
						var qnaTitle = $("<td>").append("<a class='table-link-title' href='qaDetail.dz?qnaNo="+data[i].qnaNo+"'><p>"+data[i].qnaTitle+data[i].qnaNo+"</p></a>");
						var date = $("<td class='qnaDate'>").html(dateResult);
						var modifybtn = $("<td>").append("<a class='modify-btn' href='qaUpdateForm.dz?qnaNo="+data[i].qnaNo+"'>수정</a>");
						var hiddenNo = $("<td>").append("<input type='hidden' value="+data[i].qnaNo+"'><a class='delete-btn' href='#'>삭제</a>");		
						
						tr.append(count);
						tr.append(qnaTitle);
						tr.append(date);
						tr.append(modifybtn);
						tr.append(hiddenNo);
						
						$(".qTbody").append(tr);
					}
				},
				error : function(){
					console.log("전송실패");
				}
			});
		});
		
	</script>



</body>
</html>