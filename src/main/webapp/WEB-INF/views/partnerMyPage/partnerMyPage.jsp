<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사업자 마이페이지</title>
<link rel="stylesheet" href="/resources/css/partnermypage/partnerMyPage.css">
<!-- <link rel="stylesheet" href="/resources/css/mzmypage/mzmypage.css">  -->
<!-- <link href="/static/bootstrap.min.css" rel="stylesheet"> -->

<!-- fullcander -->
<link href='/resources/css/partnermypage/main.css' rel='stylesheet' />
<script src='/resources/css/partnermypage/main.js'></script>
<script src='/resources/css/partnermypage/ko.js'></script>

</head>
<body>
	<jsp:include page="/WEB-INF/views/common/mypagemenubar.jsp"></jsp:include>
	<main>
		<div id="main-title">${ loginUser.userName }님 안녕하세요!</div>
		<div class="frame">
			<div class="my-info">
				<span>보유포인트 : <b>${shop.shopPoint }</b>원</span>
				<a class="refund-btn" href="refundsPartnerPoint.dz">환급신청</a>
				<div class="info-btn-frame">
					<a class="info-btn" href="#">가게 수정</a> <!-- 등록/수정 -->
					<a class="info-btn" href="myINfo.dz?userNo=${loginUser.userNo }">나의 정보</a>
					<a class="info-btn" href="userWritePwView.dz">탈퇴 요청</a> <!-- 사업자userDeleteRequest.dz -->
				</div>
			</div>
		</div>
		
		<!-- 달력 시작 -->
		<div class="my-list reserv-calendar">
			<div class="frame">
				<div class="my-title">
					<span>예약 현황</span>
					<div class="more-btn-frame">
						<a class="more-btn b-btn" href="partnerReserveList.dz">더보기</a>
					</div>
				</div>
				<div class="calendar_frame">
					<div id='calendar'></div>
				</div>
			</div>
		</div>
		<!-- 달력 끝 -->
		
		<div class="my-list w-list reserv-list">
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
						<c:forEach items="${rList }" var="reservation"  varStatus="status">
							<tr>
								<td>${status.count }</td>
								<input type="hidden" name="reservationNo" value="${reservation.reservationNo }">
								<input type="hidden" name="shopNo" value="${reservation.shopNo }">
								<td><a class="table-link-title" href="#"><p>${reservation.userNick }</p></a></td>
								<td>${reservation.reserveCount }</td>
								<td>${reservation.reserveDate }</td>
								<td>
								<c:choose>
									<c:when test="${reservation.rState eq 'O'}">
											<select name="rState" class="select-rstate" id="rState">
												<option value="O" selected disabled >대기</option>
												<option value="Y" ${reservation.rState eq 'Y' ? 'selected="selected"' : '' } >승인</option>
																	<!-- reservation.rState eq 'Y' 결과값이 true일때 실행되는 구간 : false일때 실행되는 구간(false일때는 아무 액션도 없기때문에 빈칸) -->
												<option value="X" ${reservation.rState eq 'X' ? 'selected="selected"' : '' }>거부</option>
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
									<td><a class="reserv-btn" href="completeReservation.dz?reservationNo=${reservation.reservationNo }&rState=${reservation.rState }" >방문완료</a></td>
								</c:if>
								<c:if test="${reservation.rState eq 'C'}">
									<td>완료된 예약</td>
								</c:if>
								
							</tr>
						</c:forEach>
						
						
					</tbody>
				</table>
			</div>
		</div>
		
		<div class="my-list recommend-list">
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
					<tbody>
						<c:if test="${ !empty qList }">
							<c:forEach items="${qList }" var="qna" varStatus="status">
								<tr>
									<td>${status.count }</td>
									<td><a class="table-link-title" href="#"><p>${ qna.qnaTitle}</p></a></td>
									<td>${ qna.qanCreateDate }</td>
									<td><a class="modify-btn" href="qaUpdateForm.dz?qnaNo=${qna.qnaNo }">수정</a></td>
									<td><a class="delete-btn" href="qaDelete.dz?qnaNo=${qna.qnaNo }">삭제</a></td>
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
	</main>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	
	<script>
		$(".select-rstate").on("change", function(){
	        var rState = $(this).val();
	        var reservationNo = parseInt($(this).closest("tr").find("input[name='reservationNo']").val());
	        var shopNo = parseInt($(this).closest("tr").find("input[name='shopNo']").val());
	        console.log(rState)
	        $.ajax({
	            url : "updateReservation.dz",
	            type : "POST",
	            data : {"rState" : rState, "reservationNo" :reservationNo, "shopNo" :shopNo},
	            success : function() {
	            	location.reload();
	            },
	            error : function() {
	            	alert('예약 상태 변경이 실패하였습니다...');

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
	            dayMaxEvents : false,
	            themeSystem:'bootstrap2'
	        });
	        calendar.render();
	      });

		
		
	</script>

	
	
</body>
</html>