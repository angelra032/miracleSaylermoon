<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/dreammypage/dreampickdetail.css">
<title>꿈나무회원 찜목록 페이지</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/mypagemenubar.jsp"></jsp:include>
	<main>
		<div id="main-title">찜 목록</div>
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
							<th>가게위치</th>
							<th>삭제</th>
						</tr>
					</thead>
					<tbody>
					<c:if test="${ !empty pList }">
							<c:forEach items="${pList }" var="pick" varStatus="status">
								<tr>
									<td>${status.count }</td>
									<td><a class="table-link-title" href="#"><p>${pick.shopName }</p></a></td>
									<td>${ pick.shopShortAddr}</td>
									<td><a class="delete-btn" href="#">삭제</a></td>
								</tr>
							</c:forEach>
						<!-- 페이징 처리 -->
						<tr align="center" height="20">
							<td colspan="5">
								<!-- 이전 -->
								<c:url var="before" value="pickListByDream.dz">
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
									<c:url var="pagination" value="pickListByDream.dz">
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
								<c:url var="after" value="pickListByDream.dz">
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
						<c:if test="${ empty pList }">
							<tr>
								<td colspan="4">${msg }</td>
							</tr>
						</c:if>
					</tbody>
				</table>
				<!-- 왜 안먹히냐 -->
			</div>
		</div>
	</main>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>