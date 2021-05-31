<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/mypagemenubar.jsp"></jsp:include>
	<!-- <aside>
		<div class="aside-area">
			<div claass="top-btn">위로</div>
			<div class="bottom-btn">아래로</div>
		</div>
	</aside> -->
	<main>
		<div class="header-admin-nick-area">
				관리자님 안녕하세요!
		</div>
		<!-- 	<div class="admin-menu">
				<ul>
					<li>포인트관리</li>
					<li>사업자관리</li>
					<li>회원관리</li>
					<li>게시판관리</li>
					<li>관리자프로필</li>
				</ul>
			</div> -->
			
        <!-- 포인트관리 -->
        <div class="point-area">
            <div class="point-center">
                <div class="my-title">
					<span>포인트관리</span>
					<div class="more-btn-frame">
						<a class="more-btn b-btn" href="adminPointList.dz">더보기</a>
					</div>
				</div>
                <div class="point-chart">
				   <canvas id="myChart" style="height: inherit; width: inherit; margin: 0 auto;"></canvas>
                </div>
				<div class="point-count-area">
					<c:if test="${ ! empty YearDon }">
							<div class="all-point point-ck"><span>총 기부금액 : </span> <span> ${ YearDon } 원</span></div>
					</c:if>
					<c:if test="${ empty YearDon }">
						${ Dmsg }
					</c:if>
				</div>

            </div>
        </div>
        
        <!-- 사업자관리 -->
        <div class="partner-area">
			<div class="center-area">
				<div class="my-title">
					<span>사업자관리</span>
					<div class="more-btn-frame">
						<a class="more-btn b-btn" href="adminShopList.dz">더보기</a>
					</div>
				</div>
				<div class="center-table-area">
					<table>
						<thead>
							<tr>
								<th width=100>No</th>
								<th>업체명</th>
								<th>사업자아이디</th>
								<th>업체등록번호</th>
								<th>승인상태</th>
								<th>탈퇴요청</th>
								<th>탈퇴</th>
							</tr>
						</thead>
						<tbody>
						<c:if test="${ !empty shopList }">
							<c:forEach items="${ shopList }" var="shop" varStatus="status" end="2">
							
								<tr>
									<td>${ status.count }</td>
									<td><a class="table-link-title" href="shopDetail.dz?shopNo=${shop.shopNo}"><span>${ shop.shopName }</span></a></td>
									<td>${ shop.userId }</td>
									<td>${ shop.partnerVerify }</td>
									<c:if test="${ shop.showShopYN eq 'Y' or shop.showShopYN eq 'y' }">
										<td>승인</td>
									</c:if>
									<c:if test="${ shop.showShopYN eq 'N' or shop.showShopYN eq 'n' }">
										<td>미승인</td>
									</c:if>
									<td>${ shop.partnerWithdraw }</td>										
									<td><a class="btn btn-secondary reserv-btn">탈퇴</a></td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${ empty shopList }">
							<tr>
								<td colspan="7">${ Smsg }</td>
							</tr>
						</c:if>
							
						</tbody>
					</table>
				</div>
			</div>
        </div>
        
        <!-- 회원관리 -->
        <div class="user-area">
            <div class="center-area">
				<div class="my-title">
					<span>회원관리</span>
					<div class="more-btn-frame">
						<a class="more-btn b-btn" href="adminUserList.dz">더보기</a>
					</div>
				</div>
				<div class="center-table-area">
					<table>
						<thead>
							<tr>
								<th width=100>No</th>
								<th>회원이름</th>
								<th>회원아이디</th>
								<th>전화번호</th>
								<th>구분</th>
								<th width=200px>탈퇴</th>
							</tr>
						</thead>
						<tbody>
						<c:if test="${!empty userList }">
						
							<c:forEach items="${ userList }" var="user" end="2" varStatus="status">
								<tr>
									<td>${ status.count }</td>
									<td>${ user.userName }</td>
									<td><a class="table-link-title" href="adminUserInfo.dz?userNo=${user.userNo}"><span>${ user.userId }</span></a></td>
									<td>${ user.userPhone }</td>
									<c:if test="${ user.userType eq '1' }">
										<td>꿈나무</td>
									</c:if>
									<c:if test="${ user.userType eq '2' }">
										<td>일반</td>
									</c:if>
									<c:if test="${ user.userType eq '3' }">
										<td>사업자</td>
									</c:if>
									<td><a class="btn btn-secondary reserv-btn">탈퇴</a></td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${ empty userList }">
							<tr>
								<td colspan="6">${ Umsg }</td>
							</tr>
						</c:if>
						</tbody>
					</table>
				</div>
			</div>
        </div>
        
        <!-- 게시판관리 -->
        <div class="board-area">
            <div class="center-area">
				<div class="my-title">
					<span>게시판관리</span>
					<div class="more-btn-frame">
						<a class="more-btn b-btn" href="adminMReviewList.dz">더보기</a>
					</div>
				</div>
				<div class="center-table-area">
					<table>
						<thead>
							<tr>
								<th width=100px>No</th>
								<th width=500px>제목</th>
								<th>작성자</th>
								<th>작성일</th>
								<th>삭제</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>1</td>
								<td><a class="table-link-title" href="#"><span>감자맛집</span></a></td>
								<td>papapa01</td>
								<td>123457-456-7897</td>
								<td><a class="btn btn-secondary reserv-btn">탈퇴</a></td>
							</tr>
							<tr>
								<td>1</td>
								<td><a class="table-link-title" href="#"><span>감자맛집</span></a></td>
								<td>papapa01</td>
								<td>123457-456-7897</td>
								<td><a class="btn btn-secondary reserv-btn">탈퇴</a></td>
							</tr>
							<tr>
								<td>1</td>
								<td><a class="table-link-title" href="#"><span>감자맛집</span></a></td>
								<td>papapa01</td>
								<td>123457-456-7897</td>
								<td><a class="btn btn-secondary reserv-btn">탈퇴</a></td>
							</tr>
							
						</tbody>
					</table>
				</div>
			</div>
        </div>
	</main>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
<link rel="stylesheet" href="/resources/css/adminpage/adminHeader.css"> 
<link rel="stylesheet" href="/resources/css/adminpage/adminpage.css"> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.3.0/chart.min.js" integrity="sha512-yadYcDSJyQExcKhjKSQOkBKy2BLDoW6WnnGXCAkCoRlpHGpYuVuBqGObf3g/TdB86sSbss1AOP4YlGSb6EKQPg==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script>
	var ctx = document.getElementById('myChart');
	var dataArr = [];
	<c:forEach items="${ monthSum }" var="month" varStatus="status">
		var test = '${month}';
		console.log('test : ' + test)
		dataArr['${status.index}'] = test.substring(test.indexOf('=', 1)+1, test.length);
	</c:forEach>
		console.log("오이잉 : " + dataArr);
	var myChart = new Chart(ctx, {
		type: 'bar',
		data: {
			labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
			datasets: [{
				label: '2021년 돈쭐포인트',
				data: [
					dataArr[0], dataArr[1], dataArr[2], dataArr[3], dataArr[4], dataArr[5], dataArr[6], dataArr[7], dataArr[8], dataArr[9], dataArr[10], dataArr[11]
				],
				backgroundColor: [
					'rgba(255, 99, 132, 0.2)',
					'rgba(54, 162, 235, 0.2)',
					'rgba(255, 206, 86, 0.2)',
					'rgba(75, 192, 192, 0.2)',
					'rgba(153, 102, 255, 0.2)',
					'rgba(255, 159, 64, 0.2)'
				],
				borderColor: [
					'rgba(255, 99, 132, 1)',
					'rgba(54, 162, 235, 1)',
					'rgba(255, 206, 86, 1)',
					'rgba(75, 192, 192, 1)',
					'rgba(153, 102, 255, 1)',
					'rgba(255, 159, 64, 1)'
				],
				borderWidth: 2
			}]
		},
		options: {
			responsive: false,
			<%-- scaleLabel: "<%=new Intl.NumberFormat().format(value) %> 원", --%>
			scales: {
				yAxes: [{
					ticks: {
						beginAtZero: true
					}
				}]
			},
		}
	});

</script>
</html>