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
			editable : true,
			selectable : true,
			businessHours : true,
			dayMaxEvents : true, // allow "more" link when too many events
			dateClick : function(arg) {
				dataClick(arg.date); // 얘는 펑션이야!!!
				// use *local* methods on the native Date Object
				// will output something like 'Sat Sep 01 2018 00:00:00 GMT-XX:XX (Eastern Daylight Time)'
			}
		});

		calendar.render();
	 });

	function getFormatDate(date) {
		var year = date.getFullYear(); //yyyy
		var month = (1 + date.getMonth()); //M
		month = month >= 10 ? month : '0' + month; //month 두자리로 저장
		var day = date.getDate(); //d
		day = day >= 10 ? day : '0' + day; //day 두자리로 저장
		return year + '-' + month + '-' + day;
	}
	
	function dataClick(arg) {
		var date = new Date(arg); // arg의 값을 데이터베이스 날짜로 형변환한다!
		var date2 = getFormatDate(date); // date에 서식 포맷을 변경!
		$("input[name='reserveDate']").val(date2);
		if(date2 != null){
			$("#selectAreaFirst").show(500);
			$("#selectAreaSecond").hide();
			$("#selectAreaThird").hide();
		}
	}

	// 요거는 체인지가 어울려!
	function timeChange(time){
		$("input[name='reserveTime']").val(time);
		//날짜클릭시 예약시간선택 보임 나머지 숨김
		console.log(time);
		if(time != null){
			$("#selectAreaSecond").show();
			$("#countDiv").show(500);
			$("#pointDiv").hide();
			$("#selectAreaThird").hide();
		}
	}
	
	function countChange(count){
		$("input[name='reserveCount']").val(count);
		if(count != null){
			$("#selectAreaSecond").show();
			$("#countDiv").show();
			$("#pointDiv").show(500);
			$("#selectAreaThird").hide();
		}
	}
	
	
	
	function pointchecked(){
		//$('input[name="point"]:checked').val()
		var chk = $('input[name="point"]:checked').val();
		$("input[name='pointYn']").val(chk);
 		if(chk == 'Y'){
			//div 보임
			$("#pointUseDiv").show(500);
			$("#selectAreaThird").show(500);
		}else if(chk=='N'){
			//div 숨김
			$("#pointUseDiv").hide();
			$("#selectAreaThird").show(500);
		} 
	}
	

 
/* 	function ajax(){
		$.ajax{
			체크할때 쿼리는 무조건 select로 받아야한다!
			data = $(#reservationInsert.kh)
			su : fucntion(str)
			    //성공 
			   if(str == ok){
				    저장 $
				    $(#reservationInsert.kh).submit();
			   }else{
				   alert( str);
			   }
		}
	} */
	
	
	
	
	
</script>
<title>예약 입력 페이지</title>
</head>
<body>
	<!-- 헤더 시작-->
	<jsp:include page="/WEB-INF/views/common/menubar.jsp"></jsp:include>
	<!-- 헤더 끝 -->
	<script>
	$(document).ready(function() {
		$("#pButton").on("click",function(){
			var paymentPoint = $("#pointText").val();
			var userPoint = ${loginUser.userPoint}
			if(paymentPoint > userPoint){
				alert("욕심을 냈군요");
				return false;
			}
			$("#paymentPoint").html(paymentPoint);
			$("input[name='paymentPoint']").val(paymentPoint);
		});
	});
	</script>

	<!-- 본문 시작 -->
	<main align="center">
		<form action="reservationInsert.dz" method="post">
			<div class="header-background-area">
				<img src="/resources/images/mapListMain.png" alt="뒷배경이미지">
			</div>
			<div id="main-title">${shop.shopName }</div>
			<div class="frame">

				<div id='calendar'></div>

				<div id="selectAreaFirst" style="display:none">
					<h1>예약시간 선택</h1>
					<select name="selectTime" class="select" onchange="timeChange(this.value);">
						<c:forEach var="i" begin="${startTime }" end="${endTime }">
							<option value="${i }">${i }시</option>
						</c:forEach>
					</select>
				</div>

				<div id="selectAreaSecond" style="display:none">
					<div id="countDiv" style="display:none">
					<h1>인원수 선택</h1>
					<select name="selectCount" class="select" onchange="countChange(this.value);">
						<c:forEach var="i" begin="1" end="${shop.shopMaxReserv }">
							<option value="${i }">${i }명</option>
						</c:forEach>
					</select>
					</div>

					<div class="poinUse" id="pointDiv" style="display:none">
						<h1>포인트 사용여부</h1>
						<div>
							<input type="radio" name="point" id="pointY" value="Y" onclick="pointchecked();">
							<label for="pointY">포인트 사용</label>
						</div>
						<div>
							<input type="radio" name="point" id="pointN" value="N" onclick="pointchecked();"> 
							<label for="pointN">포인트사용거부</label>
						</div>
						<div id="pointUseDiv" style="display:none">
							<h1>사용할 포인트 금액</h1>
							<input type="text" id="pointText" name="point"  value="${user.userPoint }">
							<input type="button" value="포인트사용" id="pButton" class="button">
						</div>
					</div>
				</div>

				<div id="selectAreaThird" style="display:none">
					<div class="allInformation">
						선택한 모든 정보 출력 <br> 
						날짜 : <input type="text" name="reserveDate" value=""><br>
						시간: <input type="text" name="reserveTime" value=""> <br> 
						인원수: <input type="text" name="reserveCount" value=""><br> 
						포인트사용여부: <input type="text" name="pointYn" value=""><br>

						가게번호: <input type="text" name="shopNo" value="${shop.shopNo }"><br> 
						
						회원번호: <input type="text" name="userNo" value="${loginUser.userNo }"><br>
						시작시간: <input type="text" name="startTime" value="${shop.startTime }"><br> 
						마감시간: <input type="text" name="endTime" value="${shop.endTime }"><br>
						영업날짜: <input type="text" name="businessDay" value="${shop.businessDay }"><br> 
						고객보유포인트: <input type="text"  name="userPoint" value="${loginUser.userPoint }"><br>
						
						
						사용할 포인트 : <span id="paymentPoint"></span>
						<input type="text" name="paymentPoint" value="${reservation.paymentPoint}">
					</div>
					<div class="reservationArea">
						<input type="submit" value="예약하기" id="rButton" class="button">
					</div>
				</div>
			</div>
		</form>
	</main>
	<!-- 본문 끝 -->


	<!-- 푸터 시작 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	<!-- 푸터 끝 -->

</body>
</html>