<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/dreammypage/drmreservationdetail.css">
<title>꿈나무회원 예약목록 페이지</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/mypagemenubar.jsp"></jsp:include>
	<main>
		<div id="main-title">예약 목록</div>
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
							<th>가게이름</th>
							<th>예약날짜</th>
							<th>예약취소</th>
							<th>후기작성</th>
						</tr>
					</thead>
					<tbody>
					<c:if test="${ !empty rList }">
					<c:forEach items="${rList }" var="reservation" varStatus="status">
						<tr>
							<td>${status.count }</td>
							<td><a class="table-link-title" href="#"><p>${reservation.shopName }</p></a></td>
							<td>${reservation.reserveDate }</td>
							
							<c:if test="${reservation.rState eq 'O' }">
							<td><a class="reserv-btn" href="cancelReservation.dz?reservationNo=${ reservation.reservationNo }">예약취소</a></td>
							</c:if>
							<c:if test="${reservation.rState eq 'X' }">
							<td><a class="btn btn-secondary">취소완료</a></td>
							</c:if>
							<c:if test="${reservation.rState eq 'Y' }">
							<td><a class="confirm-btn" >예약확정</a></td>
							</c:if>
							
							<td><a class="btn btn-secondary">후기작성</a></td>
							<c:if test="${reservation.rState eq 'C' }">
							<td><a class="reserv-btn" href="dReviewWriteView.dz?shopNo=${ reservation.shopNo }">후기작성</a></td>
							</c:if>
							<c:if test="${reservation.rState eq 'H' }">
							<td><a class="reserv-btn">후기작성</a></td>
							</c:if>
						</tr>
					</c:forEach>
						<!-- 페이징 처리 -->
						<tr align="center" height="20">
							<td colspan="5">
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
</html>