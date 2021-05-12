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
    });

    calendar.render();
  });

$('#calendar').fullCalendar({
    dayClick: function(date){
         var dateFormat = date.format()
         console.log("Clicked on: "+dateFormat); //returns the date
         window.location.href = "newPage.jsp?datepicker="+dateFormat;
    }
});

</script>
<style>
	#calendar{
		height:500px;
		width:500px;
		position:relative;
		top:40%;
		right:30%;
		bottom:20%;
		margin:0 auto;
	}

	  body {
    margin: 40px 10px;
    padding: 0;
    font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
    font-size: 14px;
  }

</style>
<title>예약 입력 페이지</title>
</head>
<body>
	<!-- 헤더 시작-->
  	<%-- <jsp:include page="/WEB-INF/views/common/menubar.jsp"></jsp:include> --%>
	<!-- 헤더 끝 -->



	<main>
		<div id="main-title">
			<div id='calendar'></div>
		</div>

	</main>


	<!-- 푸터 -->
	<%--<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>--%>
	<!-- 푸터 -->

</body>
</html>