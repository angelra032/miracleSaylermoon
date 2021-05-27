<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/adminpage/ListPagination.css">
<link rel="stylesheet" href="/resources/css/adminpage/viewListdetail.css">
<title>꿈나무회원 리뷰목록 페이지</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/mypagemenubar.jsp"></jsp:include>
	<main>
		<div id="main-title">회원 목록</div>
		<div class="frame">
			<div class="my-info">
				<div class="info-btn-frame">
					<a class="info-btn" href="javascript:history.back();">돌아가기</a>
				</div>
			</div>
		</div>
		<div class="my-list reserv-list">
			<div class="frame">
				<table class="list-table">
					<thead>
						<tr>
							<th>No</th>
							<th>구분</th>
							<th>이름</th>
							<th>아이디</th>
							<th>핸드폰 번호</th>
							<th>이메일</th>
							<th>탈퇴</th>
						</tr>
					</thead>
					<tbody>
					<c:if test="${ !empty userList }">
					<c:forEach items="${userList }" var="user" varStatus="status">
						<tr>
							<td>${status.count }</td>
							<c:if test="${ user.userType eq '1' }">
								<td>꿈나무</td>							
							</c:if>
							<c:if test="${ user.userType eq '2' }">
								<td>일반</td>							
							</c:if>
							<c:if test="${ user.userType eq '3' }">
								<td>사업자</td>							
							</c:if>
							<c:if test="${ user.userType eq '4' }">
								<td>관리자</td>							
							</c:if>
							<td>${user.userName }</td>
							<td>${user.userId }</td>
							<td>${user.userPhone }</td>
							<td>${user.userEmail }</td>
							<td><a class="delete-btn" href="#">탈퇴</a></td>
							
						</tr>
					</c:forEach>
					
						<!-- 페이징 처리 -->
						<%-- <tr align="center" height="20">
							<td colspan="5">
								<!-- 이전 -->
								<c:url var="before" value="allReviewListByDream.dz">
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
									<c:url var="pagination" value="allReviewListByDream.dz">
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
								<c:url var="after" value="allReviewListByDream.dz">
									<c:param name="page" value="${pi.currentPage + 1 }"></c:param>
								</c:url>
								<c:if  test="${pi.currentPage >= pi.maxPage }">
									[다음]&nbsp;
								</c:if>
								<c:if test="${pi.currentPage < pi.maxPage }">
									<a href="${after }">[다음]</a>&nbsp;
								</c:if>
							</td>
						</tr> --%>
						</c:if>
						<c:if test="${ empty userList }">
							<tr>
								<td colspan="6">${ msg }</td>
							</tr>
						</c:if>
					</tbody>
				</table>
				<c:if test="${ !empty userList }">
					<table class="page-table">
							<tbody>
								<tr>
								<!-- 이전 -->
										<td class="page-td" width=35px><a href="${ before }">&lt;</a></td>
									<!-- 이전끝 -->
									<!-- 페이징 -->
										<td class="page-td page-selected" width=35px>1</td>
										<td class="page-td" width=35px><a href="${ pagination }">2</a></td>
										<td class="page-td" width=35px><a href="${ pagination }">3</a></td>
										<td class="page-td" width=35px><a href="${ pagination }">4</a></td>
										<td class="page-td" width=35px><a href="${ pagination }">5</a></td>
									<!-- 페이징 끝 -->
										<td class="page-td" width=35px><a href="${ after }">&gt;</a></td>
									<!-- 다음 끝 -->
								</tr>
							</tbody>
						</table>
				</c:if>
			</div>
		</div>
	</main>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>