<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/partnermypage/partnerReservationDetail.css">
<title>사업자회원 예약관리 페이지</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/mypagemenubar.jsp"></jsp:include>
	<main>
		<div id="main-title">예약 관리</div>
		<div class="frame">
			<div class="my-info">
				<div class="info-btn-frame">
					<a class="info-btn" href="javascript:history.back();">돌아가기</a>
				</div>
			</div>
		</div>
		<div class="my-list reserv-list">
			<div class="frame">
				<table>
					<thead>
						<tr>
							<th>No</th>
							<th>회원닉네임</th>
							<th>예약인원</th>
							<th>예약날짜</th>
							<th>예약상태</th>
							<th>후기작성</th>
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
							</td> 
						</tr>
					</c:forEach>
						<!-- 페이징 처리 -->
						<tr align="center" height="20">
							<td colspan="5">
								<!-- 이전 -->
								<c:url var="before" value="partnerReserveList.dz">
									<c:param name="page" value="${pi.currentPage - 1 }"></c:param>
								</c:url>
								<c:if test="${pi.currentPage <= 1 }">
									[이전]&nbsp;
								</c:if>
								<c:if test = "${pi.currentPage > 1 }">
									<a href="${before }">[이전]</a>&nbsp;
								</c:if>
								<!-- 페이지 -->
								<c:forEach var="p" begin="${pi.startPage }" end="${pi.endPage }">
									<c:url var="pagination" value="partnerReserveList.dz">
										<c:param name="page" value="${p }"></c:param>
									</c:url>
									<c:if test="${p eq pi.currentPage }">
										<font color="red" size="4">[${p }]</font>								
									</c:if>
									<c:if test="${p ne pi.currentPage }">
										<a href="${pagination }">${p }</a>&nbsp;
									</c:if>
								</c:forEach>
								<!-- 다음 -->
								<c:url var="after" value="partnerReserveList.dz">
									<c:param name="page" value="${pi.currentPage + 1 }"></c:param>
								</c:url>
								<c:if  test="${pi.currentPage >= pi.maxPage }">
									[다음]&nbsp;
								</c:if>
								<c:if test="${pi.currentPage < pi.maxPage }">
									<a href="${after }">[다음]</a>&nbsp;
								</c:if>
							</td>
						</tr>
						</c:if>
						<c:if test="${ empty rList }">
							<tr>
								<td colspan="5">${ msg }</td>
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
		
</script>

</body>
</html>