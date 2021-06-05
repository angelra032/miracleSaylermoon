<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="/resources/css/adminpage/ListPagination.css">
<link rel="stylesheet"
	href="/resources/css/dreammypage/dreamqnadetail.css">
<title>꿈나무회원 문의목록 페이지</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/mypagemenubar.jsp"></jsp:include>
	<main>
		<div id="main-title">문의 목록</div>
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
							<th>가게이름</th>
							<th>예약날짜</th>
							<th>예약취소</th>
							<th>후기작성</th>
						</tr>
					</thead>
					<tbody class="tbody">
						<c:if test="${ !empty qList }">
							<c:forEach items="${qList }" var="qna" varStatus="status">
								<tr>
									<td>${status.count }</td>
									<td><a class="table-link-title" href="qaDetail.dz?qnaNo=${qna.qnaNo }">${qna.qnaTitle }</a></td>
									<td class="qnaDate">${qna.qanUploadDate }</td>
									<td><a class="modify-btn" href="qaUpdateForm.dz?qnaNo=${qna.qnaNo}">수정</a></td>
									<td>
										<a class="delete-btn" href="#">삭제</a>
										<input type="hidden" class="qna" value="${ qna.qnaNo}">
										<input type="hidden" class="page-ajax" value="${ pi.currentPage }">
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${ empty qList }">
							<tr>
								<td colspan="5">${ msg }</td>
							</tr>
						</c:if>
					</tbody>
					<tbody class="paging-navi">
						<c:if test="${ !empty qList }">
							<tr align="center" height="20">
								<td colspan="6">
									<!-- 이전 --> <c:url value="allQnaListByDream.dz" var="before">
										<c:param name="page" value="${ pi.currentPage-1 }"></c:param>
									</c:url> <c:if test="${ pi.currentPage <= 1 }">
										[이전]&nbsp;
									</c:if> <c:if test="${ pi.currentPage > 1 }">
										<td class="page-td" width=35px><a href="${ before }">&lt;</a></td>
									</c:if> <!-- 이전끝 --> <!-- 페이징 --> <c:forEach var="p"
										begin="${ pi.startPage }" end="${ pi.endPage }">
										<c:url var="pagination" value="allQnaListByDream.dz">
											<c:param name="page" value="${ p }"></c:param>
										</c:url>
										<c:if test="${ p eq pi.currentPage }">
											<font color="red" size="4">[${p }]</font>
										</c:if>
										<c:if test="${ p ne pi.currentPage }">
											<a href="${pagination }">${p }</a>&nbsp;
										</c:if>
									</c:forEach> <!-- 페이징 끝 --> <c:url var="after" value="allQnaListByDream.dz">
										<c:param name="page" value="${ pi.currentPage+1 }"></c:param>
									</c:url> <c:if test="${pi.currentPage >= pi.maxPage }">
										[다음]&nbsp;
									</c:if> <c:if test="${pi.currentPage < pi.maxPage }">
										<a href="${ after }">[다음]</a>&nbsp;
									</c:if>
								</td>
							</tr>
						</c:if>
						<!-- 다음 끝 -->
						<c:if test="${ empty qList }">
							<tr>
								<td colspan="6">${ msg }</td>
							</tr>
						</c:if>
					</tbody>
					</tbody>
				</table>
			</div>
		</div>
	</main>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
	var qnaDate = document.querySelectorAll('.qnaDate');
	for (var i = 0; i < qnaDate.length; i++) {
		var qnaDateResult = qnaDate[i].innerHTML.substr(0, 10);
		qnaDate[i].innerHTML = qnaDateResult;
		console.log("qnaDateResult"+qnaDateResult);
	}

	$(document).on('click', '.delete-btn', function() {
		var qnaNo = $(this).next().val();
		var page = $(this).next().next().val();
		console.log("qnaNo"+qnaNo+"page"+page);
		$.ajax({
			url : "removeMyPageQnalist.dz",
			data : {"qnaNo" : qnaNo, "page" : page},
			type : "GET",
			success : function(data){
				if(data != null){
					console.log("여기 들어왔니"+data);
					$('tbody').empty();
					$('paging-navi').empty();
					for(var i in data.list){
						var dateResult = data.list[i].qanUploadDate.substr(0,10);
						
						var tr = $("<tr>");
						var count = $("<td>").html(Number(i)+1);
						var qnaTitle = $("<td>").append("<a class='table-link-title' href='qaDetail.dz?qnaNo="+data.list[i].qnaNo+"'>"+data.list[i].qnaTitle+"</a>");
						var date = $("<td class='qnaDate'>").html(data.list[i].qanUploadDate);
						var modifybtn = $("<td>").append("<a class='modify-btn' href='qaUpdateForm.dz?qnaNo"+data.list[i].qnaNo+"'>수정</a>");
						var hiddenNo = $("<input type='hidden' class='qna' value="+data.list[i].qnaNo+"'>");
						var page =$("<input type='hidden' class='page-ajax' value="+data.pi.currentPage+"'>");
						var td = $("</td>");
						
						tr.append(count);
						tr.append(qnaTitle);
						tr.append(date);
						tr.append(modifybtn);
						tr.append(hiddenNo);
						tr.append(page);
						tr.append(td);
						
						$('.tbody').append(tr);
						
					}
					// 네비
					var trNavi = $("<tr align='center' height='20'>");
					var tdNavi = $("<td colspan='6'>");
					if(data.pi.currentPage > 1) { //현재페이지가 1보다 크면 (2, 3, 4...)- 이전버튼 활성화
						var prev = Number(data.pi.currentPage)-1;
						tdNavi.append("<a href='allQnaListByDream.dz?page="+prev+"'>[이전]</a>&nbsp;");
					}else {
						tdNavi.append("[이전]&nbsp;");
					}
					for(var i = data.pi.startPage; i <= data.pi.endPage; i++) { // for문으로 네비버튼
						if(i == data.pi.currentPage) { // 현재페이지는 빨간색
							tdNavi.append("<font color='red' size='4'>["+i+"]</font>");
						}else if(i != data.pi.currentPage) {
							tdNavi.append("<a href='allQnaListByDream.dz?page="+i+"'>"+i+"</a>&nbsp;");
						}
					}//end of for()
					if(data.pi.currentPage < data.pi.maxPage) {//현재페이지가 마지막페이지번호보다 작으면 - 다음버튼 활성화
						var next = Number(data.pi.currentPage)+1;
						tdNavi.append("<a href='allQnaListByDream.dz?page="+next+"'>[다음]</a>&nbsp;");
					}else {
						tdNavi.append("[다음]&nbsp;");
					}
					trNavi.append(tdNavi);
					$('.paging-navi').append(trNavi);
				}else {
					$('.tbody').empty();
					$('.paging-navi').empty();
					$('.tbody').append("<td colspan='6'>${ msg }</td>");
				}
				
			},//end of success
			error : function() {
				console.log("전송실패");
			}
		});
	});
</script>
</html>