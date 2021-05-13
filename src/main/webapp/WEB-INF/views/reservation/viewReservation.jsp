<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/reservation/reservation.css">
<!-- fullcalendar -->
<link href='/resources/css/fullcalendar/main.css' rel='stylesheet' />
<script src='/resources/css/fullcalendar/main.js'></script>
<script src='/resources/css/fullcalendar/ko.js'></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script>
<script>
	document.addEventListener('DOMContentLoaded', function() {
		var calendarEl = document.getElementById('calendar');

		var calendar = new FullCalendar.Calendar(calendarEl, {
			initialDate : '2020-09-12',
			editable : true,
			selectable : true,
			businessHours : true,
			dayMaxEvents : true, // allow "more" link when too many events
			dateClick : function(arg) {
				console.log('dataclick');
				dataClick(arg.date);
				// use *local* methods on the native Date Object
				// will output something like 'Sat Sep 01 2018 00:00:00 GMT-XX:XX (Eastern Daylight Time)'
			}
		});

		calendar.render();
	});

	function dataClick(arg) {
		var date = new Date(arg);
		console.log('date');
		console.log(date);
		var date2 = getFormatDate(date);
		console.log('date2');
		console.log(date2);
		location.href = "reservationInsert.kh?reserveDate=" + date2;
	}

	function getFormatDate(date) {
		var year = date.getFullYear(); //yyyy
		var month = (1 + date.getMonth()); //M
		month = month >= 10 ? month : '0' + month; //month 두자리로 저장
		var day = date.getDate(); //d
		day = day >= 10 ? day : '0' + day; //day 두자리로 저장
		return year + '-' + month + '-' + day;
	}
</script>
<title>예약 입력 페이지</title>
</head>
<body>
	<!-- 헤더 시작-->
	<jsp:include page="/WEB-INF/views/common/menubar.jsp"></jsp:include>
	<!-- 헤더 끝 -->



	<main align="center">
		<div class="header-background-area">
			<img src="/resources/images/mapListMain.png" alt="뒷배경이미지">
		</div>
		<div id="main-title">예약할 가게 이름 출력</div>
		<div class="frame">

			<div id='calendar'></div>

			<div id="selectAreaFirst">
				<h1>예약시간 선택</h1>
				<select name="selectTime" class="select">
					<option value="9">9-10</option>
					<option value="10">10-11</option>
					<option value="11">11-12</option>
					<option value="12">12-13</option>
					<option value="13">브레이크타임(13-14)</option>
					<option value="14">14-15</option>
					<option value="15">15-16</option>
					<option value="16">16-17</option>
					<option value="17">17-18</option>
				</select>
			</div>

			<div id="selectAreaSecond">
				<h1>인원수 선택</h1>
				<select name="selectCount" class="select">
					<option value="1">1명</option>
					<option value="2">2명</option>
					<option value="3">3명</option>
					<option value="4">4명</option>
					<option value="5">5명</option>
				</select>

				<div class="poinUse">
					<h1>포인트 사용여부</h1>
					<div>
						<input type="radio" name="point" id="pointY" value="Y"> <label
							for="pointY">포인트 사용</label>
					</div>
					<div>
						<input type="radio" name="point" id="pointN" value="N"> <label
							for="pointN">포인트사용거부</label>
					</div>
					<div>
						<h1>사용할 포인트 금액</h1>
						<input type="text" id="pointText" name="point">
						<input type="button" value="포인트사용" id="button">
					</div>
				</div>
			</div>

			<div id="selectAreaThird">
				<div class="allInformation">
					선택한 모든 정보 출력 <br> 
					날짜 : ${reservation.reserveDate}<br> 
					시간: ${reservation.reserveTime} <br> 
					인원수: ${reservation.reserveCount }<br> 
					포인트사용 여부:${reservation.pointYn }<br> 
					사용할 포인트: ${reservation.point }<br>
					
					가게번호:${shop.shopNo }<br>
					회원번호:${shop.userNo }<br>
					시작시간:${shop.startTime }<br>
					마감시간:${shop.endTime }<br>
					영업날짜:${shop.businessDay }<br>
					
					포인트:${user.userPoint }
				</div>
				<div class="reservationArea">
					<input type="button" value="예약하기" id="button">
				</div>
			</div>
		</div>
	</main>


	<!-- 푸터 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	<!-- 푸터 -->

</body>
</html>