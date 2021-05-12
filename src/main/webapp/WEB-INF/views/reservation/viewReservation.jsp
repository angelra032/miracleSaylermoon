<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
      initialDate: '2020-09-12',
      editable: true,
      selectable: true,
      businessHours: true,
      dayMaxEvents: true, // allow "more" link when too many events
      dateClick: function(arg) {
    	  	console.log('dataclick');
    	  	dataClick(arg.date);
    	     // use *local* methods on the native Date Object
    	    // will output something like 'Sat Sep 01 2018 00:00:00 GMT-XX:XX (Eastern Daylight Time)'
    	  }
    });

    calendar.render();
  });
  
  function dataClick(arg){
	  var date = new Date(arg);
	  console.log('date');
	  console.log(date);
	  var date2 = getFormatDate(date);
	  console.log('date2');
	  console.log(date2);
	  location.href = "reservationInsert.kh?reserveDate="+date2;
  }
  
  function getFormatDate(date){
	    var year = date.getFullYear();              //yyyy
	    var month = (1 + date.getMonth());          //M
	    month = month >= 10 ? month : '0' + month;  //month 두자리로 저장
	    var day = date.getDate();                   //d
	    day = day >= 10 ? day : '0' + day;          //day 두자리로 저장
	    return  year + '-' + month + '-' + day;
	}

</script>
<style>
	#calendar{
		height:500px;
		width:500px;
	}

	  body {
    margin: 40px 10px;
    padding: 0;
    font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
    font-size: 14px;
  }

	.frame{
	width:1440px;
	margin:0 auto;
	background-color: yellow;
	margin-top: 300px;
	}
</style>
<title>예약 입력 페이지</title>
</head>
<body>
	<!-- 헤더 시작-->
  	<jsp:include page="/WEB-INF/views/common/menubar.jsp"></jsp:include>
	<!-- 헤더 끝 -->
	<main align="center">
		<div id="main-title">캘린더</div> 
		<div class="frame">
		<div id='calendar'></div>
		</div>
	</main>
	

	<!-- 푸터 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	<!-- 푸터 -->

</body>
</html>