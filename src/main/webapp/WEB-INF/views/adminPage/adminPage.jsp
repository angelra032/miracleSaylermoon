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
	<main>
		<div class="header-admin-nick-area">
				관리자님 안녕하세요!
			</div>
			<div class="admin-menu">
				<ul>
					<li>포인트관리</li>
					<li>사업자관리</li>
					<li>회원관리</li>
					<li>게시판관리</li>
					<li>관리자프로필</li>
				</ul>
			</div>
        <!-- 포인트관리 -->
        <div class="point-area">
            <div class="point-center">
                <div class="my-title">
					<span>포인트관리</span>
					<div class="more-btn-frame">
						<a class="more-btn b-btn" href="#">더보기</a>
					</div>
				</div>
                <div class="point-chart">
				   <canvas id="myChart" style="height: 50vh; width: 45vw; margin: 0 auto;"></canvas>
                </div>
				<div class="point-count-area">
					<div class="all-point point-ck"><span>총 포인트 : </span> <span> 50,000 원</span></div>
					<div class="month-point point-ck"><span>이번달 포인트 : </span> <span>30,000 원</span></div>
				</div>

            </div>
        </div>
        <!-- 사업자관리 -->
        <div class="partner-area">
			<div class="center-area">
				<div class="my-title">
					<span>사업자관리</span>
					<div class="more-btn-frame">
						<a class="more-btn b-btn" href="#">더보기</a>
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
							<tr>
								<td>1</td>
								<td><a class="table-link-title" href="#"><span>감자맛집</span></a></td>
								<td>papapa01</td>
								<td>123457-456-7897</td>
								<td>N</td>
								<td>N</td>
								<td><a class="btn btn-secondary reserv-btn">탈퇴</a></td>
							</tr>
							<tr>
								<td>1</td>
								<td><a class="table-link-title" href="#"><span>감자맛집</span></a></td>
								<td>papapa01</td>
								<td>123457-456-7897</td>
								<td>N</td>
								<td>N</td>
								<td><a class="btn btn-secondary reserv-btn">탈퇴</a></td>
							</tr>
							<tr>
								<td>1</td>
								<td><a class="table-link-title" href="#"><span>감자맛집</span></a></td>
								<td>papapa01</td>
								<td>123457-456-7897</td>
								<td>N</td>
								<td>N</td> <!-- N일때는 버튼 비활성화 -->
								<td><a class="btn btn-secondary reserv-btn">탈퇴</a></td>
							</tr>
							
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
						<a class="more-btn b-btn" href="#">더보기</a>
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
								<th>탈퇴</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>1</td>
								<td>감자맛집</td>
								<td><a class="table-link-title" href="#"><span>papapa01</span></a></td>
								<td>123457-456-7897</td>
								<td>꿈나무</td>
								<td><a class="btn btn-secondary reserv-btn">탈퇴</a></td>
							</tr>
							<tr>
								<td>1</td>
								<td>감자맛집</td>
								<td><a class="table-link-title" href="#"><span>papapa01</span></a></td>
								<td>123457-456-7897</td>
								<td>일반</td>
								<td><a class="btn btn-secondary reserv-btn">탈퇴</a></td>
							</tr>
							<tr>
								<td>1</td>
								<td>감자맛집</td>
								<td><a class="table-link-title" href="#"><span>papapa01</span></a></td>
								<td>123457-456-7897</td>
								<td>사업자</td>
								<td><a class="btn btn-secondary reserv-btn">탈퇴</a></td>
							</tr>
							
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
						<a class="more-btn b-btn" href="#">더보기</a>
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
							<tr>
								<td>1</td>
								<td><a class="table-link-title" href="#"><span>감자맛집</span></a></td>
								<td>papapa01</td>
								<td>123457-456-7897</td>
								<td>N</td>
								<td>N</td>
								<td><a class="btn btn-secondary reserv-btn">탈퇴</a></td>
							</tr>
							<tr>
								<td>1</td>
								<td><a class="table-link-title" href="#"><span>감자맛집</span></a></td>
								<td>papapa01</td>
								<td>123457-456-7897</td>
								<td>N</td>
								<td>N</td>
								<td><a class="btn btn-secondary reserv-btn">탈퇴</a></td>
							</tr>
							<tr>
								<td>1</td>
								<td><a class="table-link-title" href="#"><span>감자맛집</span></a></td>
								<td>papapa01</td>
								<td>123457-456-7897</td>
								<td>N</td>
								<td>N</td> <!-- N일때는 버튼 비활성화 -->
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
	var myChart = new Chart(ctx, {
		type: 'bar',
		data: {
			labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
			datasets: [{
				label: '2021년 돈쭐포인트',
				data: [120000, 190000, 300000, 550000, 20000, 30000, 0, 0, 0, 0, 0, 0],
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