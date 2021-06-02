<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/dreammypage/dreammypage.css">
<title>꿈나무회원 마이페이지</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/mypagemenubar.jsp"></jsp:include>
	<main>
		<div id="main-title">${ loginUser.userName }님안녕하세요!</div>
		<div class="frame">
			<div class="my-info">
				<div class="info-btn-frame">
					<a class="info-btn" href="myINfo.dz?userNo=${ loginUser.userNo }">나의
						정보</a> <a class="info-btn" href="userWritePwView.dz">회원 탈퇴</a>
				</div>
			</div>
		</div>
		<div class="my-list reserv-list">
			<div class="frame">
				<div class="my-title">
					<span>예약 목록</span>
					<div class="more-btn-frame">
						<a class="more-btn b-btn" href="allRListDetailByDream.dz">더보기</a>
					</div>
				</div>
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
									<td><a class="table-link-title"
										href="shopDetail.dz?shopNo=${reservation.shopNo }"><p>${reservation.shopName }</p></a></td>
									<td>${reservation.reserveDate }</td>
									<td>${reservation.reserveCount }명</td>


									<c:if test="${reservation.rState eq 'O' }">
										<td><a id="cancle-btn"
											href="cancelReservation.dz?reservationNo=${ reservation.reservationNo }&mainPage=Y">예약취소</a></td>
										<td><a id="review-btn">후기작성</a></td>
									</c:if>

									<c:if test="${reservation.rState eq 'X' }">
										<td><a id="cancle-complete">취소완료</a></td>
										<td><a id="review-btn">후기작성</a></td>
									</c:if>


									<c:if test="${reservation.rState eq 'Y' }">
										<td><a class="confirm-btn">예약확정</a></td>
										<td><a id="review-btn">후기작성</a></td>
									</c:if>


									<c:if test="${reservation.rState eq 'C' }">
										<td><a class="confirm-btn">방문완료</a></td>
										<td><a id="visiti-comfirm"
											href="dReviewWriteView.dz?shopNo=${ reservation.shopNo }&reservationNo=${reservation.reservationNo}">후기작성</a></td>
									</c:if>

									<c:if test="${reservation.rState eq 'H' }">
										<td><a class="confirm-btn">방문완료</a></td>
										<td><a>후기작성완료</a></td>
									</c:if>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${ empty rList }">
							<tr>
								<td colspan="6">${ Rmsg}</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
		<div class="my-list w-list wish-list">
			<div class="frame">
				<div class="my-title">
					<span>가고싶다 목록</span>
					<div class="more-btn-frame">
						<a class="more-btn b-btn" href="pickListByDream.dz">더보기</a>
					</div>
				</div>
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
									<td><a class="table-link-title"
										href="shopDetail.dz?shopNo=${pick.shopNo }"><p>${pick.shopName }</p></a></td>
									<td>${ pick.shopShortAddr}</td>
									<td><a class="delete-btn"
										href="removePick.dz?pickNo=${pick.pickNo }">삭제</a></td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${ empty pList }">
							<tr>
								<td colspan="4">${Pmsg }</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
		<div class="my-list review-list">
			<div class="frame">
				<div class="my-title">
					<span>내가 쓴 후기</span>
					<div class="more-btn-frame">
						<a class="more-btn b-btn" href="allReviewListByDream.dz">더보기</a>
					</div>
				</div>
				<table>
					<thead>
						<tr>
							<th>No</th>
							<th>제목</th>
							<th>가게이름</th>
							<th>작성날짜</th>
							<th>수정</th>
							<th>삭제</th>
						</tr>
					</thead>
					<tbody id="tbody">
						<c:if test="${ !empty drList }">
							<c:forEach items="${drList }" var="dreamreview"
								varStatus="status">
								<tr id="tr" class="tr">
									<td id="count">${status.count }</td>
									<td id="drmReviewTitle"><a class="table-link-title"
										href="dReviewDetail.dz?drmRviewNo=${ dreamreview.drmRviewNo}"><p>${dreamreview.drmReviewTitle }</p></a></td>
									<td id="shopName">${dreamreview.shopName }</td>
									<td id="drmReviewUploadDate">${dreamreview.drmReviewUploadDate }</td>
									<td id="modify-btn"><a class="modify-btn drmodify-btn"
										id="drmodify-btn"
										href="dReviewUpdateForm.dz?drmRviewNo=${ dreamreview.drmRviewNo}">수정</a></td>
									<td id="delete-btn"><input type="hidden"
										value="${ dreamreview.drmRviewNo}"> <a
										class="delete-btn dreview-btn" 
										id="dreview-btn" href="#">삭제${ dreamreview.drmRviewNo}</a></td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${ empty drList }">
							<tr>
								<td colspan="6">${DRmsg}</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
		<div class="my-list w-list recommend-list">
			<div class="frame">
				<div class="my-title">
					<span>내가 쓴 문의글</span>
					<div class="more-btn-frame">
						<a class="more-btn b-btn" href="allQnaListByDream.dz">더보기</a>
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
						<c:if test="${ !empty qList}">
							<c:forEach items="${ qList}" var="qna" varStatus="status">
								<tr>
									<td>${status.count }</td>
									<td><a class="table-link-title" href="#"><p>${ qna.qnaTitle}</p></a></td>
									<td>${ qna.qanCreateDate }</td>
									<td><a class="modify-btn"
										href="qaUpdateForm.dz?qnaNo=${qna.qnaNo }">수정</a></td>
									<td><a class="delete-btn"
										href="qaDelete.dz?qnaNo=${qna.qnaNo }">삭제</a></td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${ empty qList }">
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
</body>

<script type="text/javascript">
	 $('.dreview-btn').on('click',function(){
		var drmRviewNo = $(this).prev().val();
		
		$.ajax({
			url: "drmMyPageDelete2.dz",
			type : "GET",
			data : {"drmRviewNo":drmRviewNo},
			success : function(data){
				$('.tr').empty();
				
				
				 for(var i in data.list){
					var tr = $("<tr id='tr' class='tr'>")
					var count = $("<td id='count'>").html(i+1)
					var drmReviewTitle = $("<td class='drmReviewTitle'>").html("<a class='table-link-title' href='dReviewDetail.dz?drmRviewNo="+data.list[i].drmReviewNo+"><p>"+data.list[i].drmReviewTitle+"</p></a>");
					var shopName = $("<td class='shopName'>").html(data.list[i].shopName);
					var drmReviewUploadDate = $("<td class='drmReviewUploadDate'>").html(data.list[i].drmReviewUploadDate);
					var drmodifyBtn = $("<td id='modify-btn'>").html("<a class='modify-btn drmodify-btn' href='dReviewUpdateForm.dz?drmRviewNo="+data.list[i].drmRviewNo +">수정</a>");
					var dreviewBtn = $("<td id='delete-btn'>").html("<input type='hidden' value="+data.list[i].drmRviewNo+"> <a class='delete-btn dreview-btn' href='#'>삭제${ dreamreview.drmRviewNo}</a>");
					console.log("여기 들어왔니"+i);
					console.log("야야"+data.list[i].drmReviewTitle);
					
						/* count.append(i+1);
						drmReviewTitle.append("<a class='table-link-title' href='dReviewDetail.dz?drmRviewNo="+data.list[i].drmReviewNo+"><p>"+data.list[i].drmReviewTitle+"</p></a>");
						shopName.append(data.list[i].shopName);
						drmReviewUploadDate.append(data.list[i].drmReviewUploadDate);
						drmodifyBtn.append("<a class='modify-btn drmodify-btn' href='dReviewUpdateForm.dz?drmRviewNo="+data.list[i].drmRviewNo +">수정</a>");
						dreviewBtn.append("<input type='hidden' value="+data.list[i].drmRviewNo+"> <a class='delete-btn dreview-btn' href='#'>삭제${ dreamreview.drmRviewNo}</a>"); */
						
						
						tr.append(count);
						tr.append(drmReviewTitle)
						tr.append(shopName)
						tr.append(drmReviewUploadDate)
						tr.append(drmodifyBtn)
						tr.appned(dreviewBtn);
						
						$('#tbody').append(tr);
				} 
			}
		})
		return false;
		
		/* <c:forEach items="${drList }" var="dreamreview"
			varStatus="status">
			<tr class="tr">
				<td id="count">${status.count }</td>
				<td class="drmReviewTitle"><a class="table-link-title"
					href="dReviewDetail.dz?drmRviewNo=${ dreamreview.drmRviewNo}"><p>${dreamreview.drmReviewTitle }</p></a></td>
				<td class="shopName">${dreamreview.shopName }</td>
				<td class="drmReviewUploadDate">${dreamreview.drmReviewUploadDate }</td>
				<td><a class="modify-btn drmodify-btn"
					href="dReviewUpdateForm.dz?drmRviewNo=${ dreamreview.drmRviewNo}">수정</a></td>
				<td><input type="hidden"
					value="${ dreamreview.drmRviewNo}"> <a
					class="delete-btn dreview-btn" href="#">삭제${ dreamreview.drmRviewNo}</a></td>
			</tr>
		</c:forEach> */
	}); 

</script>
</html>