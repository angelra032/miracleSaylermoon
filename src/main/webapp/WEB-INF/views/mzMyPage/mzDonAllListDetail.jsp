<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/dreammypage/dreservationdetail.css">
<title>일반회원 마이페이지</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/mypagemenubar.jsp"></jsp:include>
	<main>
		<div id="main-title">돈쭐 목록</div>
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
							<th>돈쭐날짜</th>
							<th>돈쭐금액</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${dList }" var="donList" varStatus="status">
						<tr>
							<td>${status.count }</td>
							<td><a class="table-link-title" href="#"><p>${donList.shopName }</p></a></td> <!-- 가게 상세페이지 -->
							<td>${donList.paymentDate }</td>
							<td>${donList.donPrice }</td>
						</tr>
					</c:forEach>
						<!-- 페이징 처리 -->
						<tr align="center" height="20">
							<td colspan="5">
								<!-- 이전 -->
								<c:url var="before" value="printDonAllList.dz">
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
									<c:url var="pagination" value="printDonAllList.dz">
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
								<c:url var="after" value="printDonAllList.dz">
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
					</tbody>
				</table>
			</div>
		</div>
	</main>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>