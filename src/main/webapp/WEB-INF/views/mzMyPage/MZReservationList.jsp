<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/mzmypage/mzreservationlist.css">
<title>마이페이지</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/mypagemenubar.jsp"></jsp:include>
	<main>
		<div id="main-title">예약 목록</div>
		<div class="frame">
			<div class="my-info">
				<div class="info-btn-frame">
					<a class="info-btn" href="mzMyPage.dz">돌아가기</a>
				</div>
			</div>
		</div>
		<div class="my-list reserv-list">
			<div class="frame">
				<table>
					<thead>
						<tr>
							<th>No</th>
							<th>가게이름</th>
							<th>예약일시</th>
							<th>예약인원</th>
							<th>예약취소</th>
							<th>후기작성</th>
						</tr>
					</thead>
					<tbody>
					<c:if test="${ !empty rList }">
					<c:forEach items="${rList }" var="reservation" varStatus="status">
						<tr>
							<td>${status.count }</td>
							<td><a class="table-link-title" href="shopDetail.dz?shopNo=${reservation.shopNo }"><p>${reservation.shopName }</p></a></td>
							<td>${reservation.reserveDate } / ${reservation.reserveTime }시</td>
							<td class="reserv-membercount${ reservation.reservationNo }">${reservation.reserveCount }명</td>
							
							<c:if test="${reservation.rState eq 'O' }">
								<td class="reserv-stay${ reservation.reservationNo }"><a class="reserv-btn reserv-btn${ reservation.reservationNo }" href="#" >예약취소</a></td>
								<input type="hidden" class="reservationNo" value="${ reservation.reservationNo }">
								<td><a class="reserv-btn disable-btn">후기작성</a></td>
							</c:if>
							<c:if test="${reservation.rState eq 'X' }">
								<td class="reserv-cancle">취소완료</td>
								<td><a class="reserv-btn disable-btn">후기작성</a></td>
							</c:if>
							<c:if test="${reservation.rState eq 'Y' }">
								<td class="reserv-confirm">예약확정</td>
								<td><a class="reserv-btn disable-btn">후기작성</a></td>
							</c:if>
							<c:if test="${reservation.rState eq 'C' }">
								<td class="reserv-confirm">방문완료</td>
								<td><a class="reserv-btn review-btn" href="mReviewWriteView.dz?shopNo=${reservation.shopNo }&reservationNo=${reservation.reservationNo }">후기작성</a></td>
							</c:if>
							<c:if test="${reservation.rState eq 'H' }">
								<td class="reserv-confirm">방문완료</td>
								<td>후기작성완료</td>
							</c:if>
						</tr>
					</c:forEach>
						<!-- 페이징 처리 -->
						<tr align="center" height="20">
							<td colspan="6">
								<!-- 이전 -->
								<c:url var="before" value="rListDetailAllByDream.dz">
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
									<c:url var="pagination" value="rListDetailAllByDream.dz">
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
								<c:url var="after" value="rListDetailAllByDream.dz">
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
</body>
<script type="text/javascript">
	$(document).ready(function() {
		// a href='#' 클릭 무시 스크립트
		$('a[href="#"]').click(function(ignore) {
            ignore.preventDefault();
        });
		
		// 예약취소 aJax
		$('.reserv-btn').on('click', function() {
			var reservationNo = $(this).parent().next().val();
			$.ajax({
				url : "cancelMZReservation.dz",
				data : {"reservationNo" : reservationNo},
				success : function(result) {
					if(result == 'ok'){
						$('.reserv-stay'+reservationNo).remove();
						$('.reserv-membercount'+reservationNo).after('<td class="reserv-cancle">취소완료</td>');
					}
				},
				error : function() {
					console.log("전송실패");
				}
			});
		}); //end of $('.reserv-btn').click

		// 후기작성버튼 비활성화 되어있을때 누르면 alert창 띄우기
		$('.disable-btn').on('click', function() {
			alert('작성 가능한 기간이 아닙니다.');
		});
	});
</script>
</html>