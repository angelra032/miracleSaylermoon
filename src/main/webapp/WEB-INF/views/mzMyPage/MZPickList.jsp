<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/mzmypage/mzpicklist.css">
<title>마이페이지</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/mypagemenubar.jsp"></jsp:include>
	<main>
		<div id="main-title">가고싶다 목록</div>
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
							<th>가게위치</th>
							<th>삭제</th>
						</tr>
					</thead>
					<tbody class="tbody">
					<c:if test="${ !empty pList }">
							<c:forEach items="${ pList }" var="pick" varStatus="status">
								<tr>
									<td>${ status.count }</td>
									<td><a class="table-link-title" href="shopDetail.dz?shopNo=${ pick.shopNo }"><p>${ pick.shopName }</p></a></td>
									<td>${ pick.shopShortAddr }</td>
									<td>
										<a class="delete-btn" href="#">삭제</a>
										<input type="hidden" class="pickNo" value="${ pick.pickNo }">
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${ empty pList }">
							<tr>
								<td colspan="4">${msg }</td>
							</tr>
						</c:if>
						</tbody>
						<!-- 페이징 처리 -->
						<tbody>
						<c:if test="${ !empty pList }">
						<tr align="center" height="20">
							<td colspan="4">
								<!-- 이전 -->
								<c:url var="before" value="mzPickList.dz">
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
									<c:url var="pagination" value="mzPickList.dz">
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
								<c:url var="after" value="mzPickList.dz">
									<c:param name="page" value="${pi.currentPage + 1 }"></c:param>
								</c:url>
								<c:if  test="${pi.currentPage >= pi.maxPage }">
									[다음]&nbsp;
								</c:if>
								<c:if test="${pi.currentPage < pi.maxPage }">
									<a href="${ after }">[다음]</a>&nbsp;
								</c:if>
							</td>
						</tr>
						</c:if>
						<c:if test="${ empty pList }">
							<tr>
								<td colspan="4">${ msg }</td>
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
		// a href='#' 클릭 무시 스크립트
		
		
		// 삭제 aJax
	$(document).on('click','.delete-btn', function() {
		var pickNo = $(this).next().val();
		var pickThis = $(this);
		$.ajax({
			url : "removeMyPagePick.dz",
			data : {"pickNo" : pickNo},
			type : 'POST',
			success : function(data) {
				if(data != null){
					pickThis.parent().parent().remove();
					$('.tbody').empty();
					for(var i in data){
						var tr = $("<tr>");
						var count = $("<td>").html(Number(i)+1);
						var shopName = $("<td>").append("<a class='table-link-title' href='shopDetail.dz?shopNo="+data[i].shopNo+"'>"+data[i].shopName+"</a>");
						var shopShortAddr = $("<td>").html(data[i].shopShortAddr);
						var td = $("<td>");
						var deleteBtn = $("<a class='delete-btn' href='#'>삭제</a>");
						var hiddenNo = $("<input type='hidden' class='pickNo' value='"+data[i].pickNo+"'>");
						
						tr.append(count);
						tr.append(shopName);
						tr.append(shopShortAddr);
						td.append(deleteBtn);
						td.append(hiddenNo);
						tr.append(td);
						
						$('.tbody').append(tr);
					}
				}else {
					console.log("전송실패1");
				}
				
			},//end of success
			error : function() {
				console.log("전송실패");
			}
		});
	});
	$(document).on('click','a[href="#"]', function(ignore) {
        ignore.preventDefault();
    });
</script>
</html>