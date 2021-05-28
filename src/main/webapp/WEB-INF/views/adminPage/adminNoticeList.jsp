<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/adminpage/ListPagination.css">
<link rel="stylesheet" href="/resources/css/adminpage/viewListdetail.css">
<link rel="stylesheet" href="resources/css/adminpage/listDetailNavi.css">
<title>게시판 목록 페이지</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/mypagemenubar.jsp"></jsp:include>
	<main>
		<div id="main-title"></div>
		<div class="frame">
			<div class="my-info">
			<div class="top-board-menu-area">
                <ul id="top-board-menu">
                    <li><a href="#" class='menu-btn' onclick="selectMenu(1)">회원관리</a></li>
                    <li><a href="#" class='menu-btn' onclick="selectMenu(2)">사업자관리</a></li>
                    <li><a href="#" class='menu-btn' onclick="selectMenu(3)">게시판관리</a></li>
                    <li><a href="#" class='menu-btn' onclick="selectMenu(4)">포인트관리</a></li>
                </ul>
    		 </div>
			<div class="top-board-menu-area">
                <ul id="top-board-menu">
                    <li><a href="#" class='menu-btn' onclick="selectMenu(1)">맛집후기</a></li>
                    <li><a href="#" class='menu-btn' onclick="selectMenu(2)">감사후기</a></li>
                    <li><a href="#" class='menu-btn' onclick="selectMenu(3)">가게추천</a></li>
                    <li><a href="#" class='menu-btn' onclick="selectMenu(4)">문의사항</a></li>
                    <li><a href="#" class='menu-btn' onclick="selectMenu(4)">공지사항</a></li>
                </ul>
    		 </div>
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
							<th>제목</th>
							<th>날짜</th>
							<th>공개여부</th>
							<th width=150px>수정</th>
							<th width=150px>삭제</th>
						</tr>
					</thead>
					<tbody>
					<c:if test="${ !empty noticeList }">
					<c:forEach items="${noticeList }" var="notice" varStatus="status">
					 <c:set var="num" value="${ pi.listCount - ((pi.currentPage - 1) * 10) - status.index }"/>
						<tr>
							<td>${ num }</td>
							<td>${notice.noticeTitle }</td>
							<td>${notice.noticeCreateDate }</td>
							<c:if test="${notice.noticePublicYN eq 'y' or notice.noticePublicYN eq 'Y' }">
								<td>공개</td>
							</c:if>
							<c:if test="${notice.noticePublicYN eq 'n' or notice.noticePublicYN eq 'N' }">
								<td>비공개</td>
							</c:if>
							<td><a class="delete-btn" href="#">수정</a></td>
							<td><a class="delete-btn" href="#">삭제</a></td>
							
						</tr>
					</c:forEach>
						</c:if>
						<c:if test="${ empty noticeList }">
							<tr>
								<td colspan="6">${ msg }</td>
							</tr>
						</c:if>
					</tbody>
				</table>
				<c:if test="${ !empty noticeList }">
					<table class="page-table">
							<tbody>
								<tr>
								<!-- 이전 -->
									<c:url value="adminAllNoticeList.dz" var="before">
										<c:param name="page" value="${ pi.currentPage-1 }"></c:param>
									</c:url>
									<c:if test="${ pi.currentPage <= 1 }">
									</c:if>
									<c:if test="${ pi.currentPage > 1 }">
										<td class="page-td" width=35px><a href="${ before }">&lt;</a></td>
									</c:if>
									<!-- 이전끝 -->
									<!-- 페이징 -->
									<c:forEach var="p" begin="${ pi.startPage }" end="${ pi.endPage }">
			                    		<c:url var="pagination" value="adminAllNoticeList.dz">
			                    			<c:param name="page" value="${ p }"></c:param>
			                    		</c:url>
			                    		<c:if test="${ p eq pi.currentPage }">
					                        <td class="page-td page-selected" width=35px>${ p }</td>
			                    		</c:if>
										<c:if test="${ p ne pi.currentPage }">
					                        <td class="page-td" width=35px><a href="${ pagination }">${ p }</a></td>
										</c:if>	                    	
			                    	</c:forEach>
									<!-- 페이징 끝 -->
									<c:url var="after" value="adminAllNoticeList.dz">
			                    		<c:param name="page" value="${ pi.currentPage+1 }"></c:param>
			                    	</c:url>
			                    	<c:if test="${ pi.currentPage >= pi.maxPage }">
			                    	</c:if>
			                    	<c:if test="${ pi.currentPage < pi.maxPage }">
			                    		<td class="page-td" width=35px><a href="${ after }">&gt;</a></td>
			                    	</c:if>
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