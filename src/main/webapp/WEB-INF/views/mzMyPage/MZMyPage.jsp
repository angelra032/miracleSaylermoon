<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/mzmypage/mzmypage.css">
<title>마이페이지</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/mypagemenubar.jsp"></jsp:include>
	<main>
		<c:if test="${ !empty sessionScope.loginUser && (sessionScope.loginUser.userType == '2' || sessionScope.loginUser.userType == '5')}">
			<div id="main-title">${ loginUser.userName }님 안녕하세요!</div>
		</c:if>
		<c:if test="${ empty sessionScope.loginUser && !empty sessionScope.kakaoId}">
			<div id="main-title">${ kakaoNickname }님 안녕하세요!</div>
		</c:if>
		<c:if test="${ empty sessionScope.loginUser && !empty sessionScope.googleId}">
			<div id="main-title">${ googleName }님 안녕하세요!</div>
		</c:if>
		<div class="frame">
			<div class="my-info">
				<span>보유포인트 : <b>${ userPoint }</b>원</span>
				<%-- <span>보유포인트 : <b>${ loginUser.userPoint }</b>원</span> --%>
				<c:if test="${ !empty sessionScope.loginUser && sessionScope.loginUser.userType == '2'}">
				<div class="info-btn-frame">
					<a class="info-btn" href="myINfo.dz?userNo=${ loginUser.userNo }">나의 정보</a>
					<a class="info-btn" href="userWritePwView.dz">회원 탈퇴</a>
				</div>
				</c:if>
			</div>
		</div>
		<div class="my-list reserv-list">
			<div class="frame">
				<div class="my-title">
					<span>예약 목록</span>
					<div class="more-btn-frame">
						<c:if test="${ !empty rList }">
							<a class="more-btn b-btn" href="mzReservationList.dz">더보기</a>
						</c:if>
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
									<c:if test="${reservation.rState eq 'C' }"><!-- 방문완료/후기작성가능 -->
										<td class="reserv-confirm">방문완료</td>
										<td><a class="reserv-btn review-btn" href="mReviewWriteView.dz?shopNo=${reservation.shopNo }&reservationNo=${reservation.reservationNo }">후기작성</a></td>
									</c:if>
									<c:if test="${reservation.rState eq 'H' }"><!-- 방문완료/후기작성완료 -->
										<td class="reserv-confirm">방문완료</td>
										<td>후기작성완료</td>
									</c:if>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${ empty rList }">
							<tr>
								<td colspan="6">${ Rmsg }</td>
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
						<c:if test="${ !empty pList }">
							<a class="more-btn b-btn" href="mzPickList.dz">더보기</a>
						</c:if>
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
					<tbody class="pick-tbody">
						<c:if test="${ !empty pList }">
						<c:forEach items="${ pList }" var="pick" varStatus="status">
							<tr>
								<td>${ status.count }</td>
								<td><a class="table-link-title" href="shopDetail.dz?shopNo=${pick.shopNo }"><p>${ pick.shopName }</p></a></td>
								<td>${ pick.shopShortAddr }</td>
								<td>
									<a class="delete-btn delete-btn-pick" href="#">삭제</a>
									<input type="hidden" class="pickNo" value="${ pick.pickNo }">
								</td>
							</tr>
						</c:forEach>
						</c:if>
						<c:if test="${ empty pList }">
							<tr>
								<td colspan="4">${ Pmsg }</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
		<div class="my-list donation-list">
			<div class="frame">
				<div class="my-title">
					<span>돈쭐 목록</span>
					<div class="more-btn-frame">
						<c:if test="${ !empty dList }">
							<a class="more-btn b-btn" href="printDonAllList.dz">더보기</a>
						</c:if>
					</div>
				</div>
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
						<c:if test="${ !empty dList }">
						<c:forEach items="${dList }" var="donList" varStatus="status">
							<tr>
								<td>${status.count}</td>
								<td><a class="table-link-title" href="shopDetail.dz?shopNo=${donList.shopNo }"><p>${donList.shopName }</p></a></td>
								<td>${donList.paymentDate }</td>
								<td>${donList.donPrice }</td>
							</tr>
						</c:forEach>
						</c:if>
						<c:if test="${ empty dList }">
							<tr>
								<td colspan="5">${ Dmsg }</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
		<div class="my-list w-list review-list">
			<div class="frame">
				<div class="my-title">
					<span>내가 쓴 후기</span>
					<div class="more-btn-frame">
						<c:if test="${ !empty mList }">
							<a class="more-btn b-btn" href="printMZReviewAllListToMyPage.dz">더보기</a>
						</c:if>
					</div>
				</div>
				<table id="review-list-table">
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
					<tbody class="mzreview-tbody">
						<c:if test="${ !empty mList }">
						<c:forEach items="${mList }" var="mzreviewList" varStatus="status">
							<tr>
								<td>${status.count}</td>
								<td><a class="table-link-title" href="mReviewDetail.dz?mzReviewNo=${ mzreviewList.mReviewNo }"><p>${ mzreviewList.mReviewTitle }</p></a></td>
								<td>${ mzreviewList.shopName }</td>
								<td>${ mzreviewList.mReviewUploadDate }</td>
								<td><a class="modify-btn modify-btn-mzreview" href="mReviewUpdateView.dz?mReviewNo=${ mzreviewList.mReviewNo }">수정</a></td>
								<td>
									<a class="delete-btn delete-btn-mzreview" href="#">삭제</a>
									<input type="hidden" class="mReviewNo" value="${ mzreviewList.mReviewNo }">
								</td>
							</tr>
						</c:forEach>
						</c:if>
						<c:if test="${ empty mList }">
							<tr>
								<td colspan="6">${ Mmsg }</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
		<div class="my-list recommend-list">
			<div class="frame">
				<div class="my-title">
					<span>내가 쓴 추천</span>
					<div class="more-btn-frame">
						<c:if test="${ !empty reList }">
							<a class="more-btn b-btn" href="printRecommendAllListToMyPage.dz">더보기</a>
						</c:if>
					</div>
				</div>
				<table id="recommend-list-table">
					<thead>
						<tr>
							<th>No</th>
							<th>제목</th>
							<th>작성날짜</th>
							<th>수정</th>
							<th>삭제</th>
						</tr>
					</thead>
					<tbody class="recommend-tbody">
						<c:if test="${ !empty reList }">
							<c:forEach items="${reList }" var="recommendList" varStatus="status">
								<tr>
									<td>${status.count}</td>
									<td><a class="table-link-title" href="recommendDetail.dz?recommendNo=${ recommendList.recommendNo }"><p>${ recommendList.recommendTitle }</p></a></td>
									<td>${ recommendList.recommendUploadDate }</td>
									<td><a class="modify-btn" href="recommendUpdateForm.dz?recommendNo=${ recommendList.recommendNo }">수정</a></td>
									<td>
										<a class="delete-btn delete-btn-recommend" href="#">삭제</a>
										<input type="hidden" class="recommendNo" value="${ recommendList.recommendNo }">
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${ empty reList }">
							<tr>
								<td colspan="5">${ REmsg }</td>
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
						<c:if test="${ !empty qList }">
							<a class="more-btn b-btn" href="printQnaAllListToMyPage.dz">더보기</a>
						</c:if>
					</div>
				</div>
				<table id="qna-list-table">
					<thead>
						<tr>
							<th>No</th>
							<th>제목</th>
							<th>작성날짜</th>
							<th>수정</th>
							<th>삭제</th>
						</tr>
					</thead>
					<tbody class="qna-tbody">
						<c:if test="${ !empty qList }">
							<c:forEach items="${qList }" var="qnaList" varStatus="status">
								<tr>
									<td>${status.count}</td>
									<td><a class="table-link-title" href="qaDetail.dz?qnaNo=${ qnaList.qnaNo }"><p>${ qnaList.qnaTitle }</p></a></td>
									<fmt:parseDate value="${ qnaList.qanUploadDate }" var="parseDateValue" pattern="yyy-MM-dd"/>
									<td class="qnaDate1"><fmt:formatDate value="${parseDateValue}" pattern="yyyy-MM-dd"/></td>
									<td><a class="modify-btn" href="qaUpdateForm.dz?qnaNo=${ qnaList.qnaNo }">수정</a></td>
									<td>
										<a class="delete-btn delete-btn-qna" href="#">삭제</a>
										<input type="hidden" class="qnaNo" value="${ qnaList.qnaNo }">
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${ empty qList }">
							<tr>
								<td colspan="5">${ Qmsg }</td>
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
	

	$(document).on('click','a[href="#"]', function(ignore) {
	    ignore.preventDefault();
	});
		
	// 예약취소 aJax
	$(document).on('click','.reserv-btn', function() {
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
	$(document).on('click','.disable-btn', function() {
		alert('작성 가능한 기간이 아닙니다.');
	});
		
	// 가고싶다 삭제 aJax
	$(document).on('click','.delete-btn-pick', function() {
		var pickNo = $(this).next().val();
		$.ajax({
			url : "myPageMainPickDelete.dz",
			data : { "pickNo" : pickNo },
			success : function(data){ 
				if(data != null){
					$('.pick-tbody').empty();
					for(var i in data){
						var tr = $("<tr>");
						var count = $("<td>").html(Number(i)+1);
						var shopName = $("<td>").append("<a class='table-link-title' href='shopDetail.dz?shopNo="+data[i].shopNo+"'><p>"+data[i].shopName+"</p></a>");
						var shopShortAddr = $("<td>").html(data[i].shopShortAddr);
						var td = $("<td>");
						var deleteBtn = $("<a class='delete-btn delete-btn-pick' href='#'>삭제</a>");
						var hiddenNo = $("<input type='hidden' class='pickNo' value='"+data[i].pickNo+"'>");
						
						tr.append(count);
						tr.append(shopName);
						tr.append(shopShortAddr);
						td.append(deleteBtn);
						td.append(hiddenNo);
						tr.append(td);
						
						$('.pick-tbody').append(tr);
					}
				}else {
					console.log("전송실패");
					
				}
				
			},//end of success
			error : function() {
				console.log("전송실패");
			}
		});
	});

	// 내가쓴후기 삭제 aJax
	$(document).on('click','.delete-btn-mzreview', function() {
		var mReviewNo = $(this).next().val();
		$.ajax({
			url : "mReviewDelete.dz",
			data : { "mReviewNo" : mReviewNo },
			success : function(data){ 
				if(data == "success"){
					reloadReviewList();					
				}else { // 남은 데이터 없을때
					alert("삭제 실패했습니다");
				}
			}, //end of success
			error : function() {
				console.log("전송실패");
			}
		});//end of ajax
		
		function reloadReviewList() {
			$("#review-list-table").load("mzMyPage.dz #review-list-table");
			// $("특정 #id").load("해당페이지주소  특정#id") 
		}
	});
	
	// 내가쓴추천 삭제 aJax
	$(document).on('click','.delete-btn-recommend', function() {
		var recommendNo = $(this).next().val();
		$.ajax({
			url : "recommendDelete.dz",
			data : { "recommendNo" : recommendNo },
			success : function(data){ 
				if(data == "success"){
					reloadRecommendList();
				} else if(result == "fail") {
					alert('추천삭제가 실패했습니다');
				}
			},//end of success
			error : function() {
				console.log("전송실패");
			}
		});
		
		// 리로드
		function reloadRecommendList() {
			$("#recommend-list-table").load("mzMyPage.dz #recommend-list-table");
			// $("특정 #id").load("해당페이지주소  특정#id") 
		}
	});
	
	// 내가쓴문의글 삭제 aJax
	$(document).on('click','.delete-btn-qna', function() {
		var qnaNo = $(this).next().val();
		$.ajax({
			url : "qaDelete.dz",
			data : { "qnaNo" : qnaNo },
			success : function(data){ 
				if(data == "success"){
					reloadQnaList();
				} else {
					alert('문의글삭제가 실패했습니다');
				}
			},//end of success
			error : function() {
				console.log("전송실패");
			}
		});
		
		// 리로드
		function reloadQnaList() {
			$("#qna-list-table").load("mzMyPage.dz #qna-list-table");
			// $("특정 #id").load("해당페이지주소  특정#id") 
		}
		
	});
</script>
</html>