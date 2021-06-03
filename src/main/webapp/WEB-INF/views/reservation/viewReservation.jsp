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
         locale : "ko",
         dayMaxEvents : false,
         dateClick : function(arg) {
            dataClick(arg.date); // 얘는 펑션이야!!!
         },
          eventSources : [{
        	 events: function(info, successCallback, failureCallback){
        		 $.ajax({
        			 url: "printReservation.dz",
        			 type : 'POST',
        			 dataType : 'json',
        			 data : {"shopNo": "${shop.shopNo }"},
        			 success: function(data){
        				 successCallback(data);
        				 console.log(data);
        			 }
        		 });
        	 }
          	, color : "#FDB504"
            , textColor : "#000000"
         }] 
         
      });

      calendar.render();
    });

   function getFormatDate(date) {
      var year = date.getFullYear(); //yyyy
      var month = (1 + date.getMonth()); //M
      month = month >= 10 ? month : '0' + month; //month 두자리로 저장
      var day = date.getDate(); //d
      day = day >= 10 ? day : '0' + day; //day 두자리로 저장
      return year + '-' + month + '-' + day; // 스트링
   }
   
   function dataClick(arg) {
	  var userId = '${loginUser.userId }';
	  if(userId != '') {
		  var date = new Date(arg); // arg의 값을 데이터베이스 날짜로 형변환한다!
	      var date2 = getFormatDate(date); // date에 서식 포맷을 변경!
	      
	      var today = new Date(); // 문자랑 문자는 비교가 안된다! 날짜랑 날짜만 대소비교가 된다;
	      today.setHours(0,0,0,0);
	      if(today > date){
	    	  alert("오늘 이전 날짜는 예약이 불가합니다. 다른 날짜를 선택해주세요.");
	    	  return false;
	      }
	      /* 한번 더 생각해보기 
	      	$.ajax({
	    	  url:"",
	    	  type:"POST",
	    	  data : {},
	    	  success: 
	      }); */
	      
	      $("#selectTime").val("0");  //선택으로 초기화
	      $("input[name='reserveDate']").val(date2);
	      $("#rDateSpan").text(date2);
	      timeChange("0");  // 최초엔 눌렀던값을 또 눌렀을경우 나머지 창이 안열리니깐 그걸 보완한거
	  }else{
		  alert("로그인 후 이용 부탁드립니다.");
	  }
      
   }
	
   // 요거는 체인지가 어울려!
   function timeChange(time){
      //날짜클릭시 예약시간선택 보임 나머지 숨김
      if(!(time == null || time == 0)){
    	 $("input[name='reserveTime']").val(time);
         var reserveDate = $("input[name='reserveDate']").val();
         var reserveTime = $("input[name='reserveTime']").val();
         var shopNo = $("input[name='shopNo']").val();
         if(reserveTime != 0){
        	 $("#rTimeSpan").text(reserveTime);
         }
         $.ajax({
        	 url : 'rCountCheck.dz',
        	 type : "GET",
        	 data : {"reserveDate":reserveDate,"reserveTime":reserveTime,"shopNo":shopNo},
        	 success : function(data) {
        		 if(data < 1){
        			 alert('해당 시간에 예약이 불가합니다.');
        			 timeChange(0);
        			 return false; // 자바 스크립트에서는 return false면 아래 구문으로 내려가지 않는다!
        		 }
        		 $('#selectCount').empty();
        		 var option = $("<option value='0'>선택</option>");
        		 $('#selectCount').append(option);

        		 for(var i = 1; i <= data; i++){                
        		     var option = $("<option value='" + i + "'>"+i +"명"+"</option>");
        		     $('#selectCount').append(option);
        		 }
        		 countChange(0);
        	 },error : function(request,status,error) {
        		 alert('서버 오류가 발생했습니다\n\n 잠시후 이용해주시길 바랍니다.');
			 }
         })
      }else{
    	  //초기화 부분
    	  $("input[name='reserveTime']").val(""); //숨겨진 부분 값 초기화
    	  $("#rTimeSpan").text(""); //보여지는 부분 초기화
    	  $("#selectTime").val("0");
    	  countChange(0);
      }
   }
   
   function countChange(count){
	   if(!(count == null || count == 0)){
		   $("input[name='reserveCount']").val(count);
		   $("#rCountSpan").text(count);
	   }else{
		   //초기화 부분
		   $("input[name='reserveCount']").val(""); //value 초기화
		   $("#rCountSpan").text(""); //보여주는 부분 초기화
		   $('#selectCount').val("0");
	   }
   }
    
   function pointchecked(){
      //$('input[name="point"]:checked').val()
      var chk = $('input[name="point"]:checked').val();
      $("input[name='pointYn']").val(chk);
      $("#pointYnSpan").text(chk);
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

   function finalCheck(){
	   var finalDate = $("#rDateSpan").html();
	   var finalTime = $("#rTimeSpan").html();
	   var finalCount = $("#rCountSpan").html();
	   console.log(finalDate);
	   console.log(finalTime);
	   console.log(finalCount);
	   if(finalDate === "" || finalTime === "" || finalCount === ""){
		   alert('예약 옵션이 선택되지 않았습니다.\n\n 예약 옵션을 선택해주세요.');
		   return false;
	   }else(finalDate !== "" || finalTime !== "" || finalCount !== "")
			return true;
   }
	   


   
</script>
<title>예약 입력 페이지</title>
</head>
<body>
   <!-- 헤더 시작-->
   <jsp:include page="/WEB-INF/views/common/menubar.jsp"></jsp:include>
   <!-- 헤더 끝 -->
   <script>
   $(document).ready(function() {
	  var pointDivResult = $("input[name='userPoint']").val();
	  $("#userPointDiv").text(pointDivResult);
	  
	  
      $("#pButton").on("click",function(){
         var paymentPoint = parseInt($("#pointText").val());
         var userPoint = parseInt("${loginUser.userPoint}");
         if(paymentPoint < userPoint){
        	 if(paymentPoint >= 500 && paymentPoint <= 50000 && paymentPoint > 0){
        		$("#paymentPoint").html(paymentPoint);
	         	$("input[name='paymentPoint']").val(paymentPoint);
	         	$("#paymentSpan").text(paymentPoint);
         	}else{
         		alert("사용가능포인트는 500원 이상 5000원 이하입니다.");
       		 	return false;
         	}
         }else{
        	 alert("보유 금액 이상 사용은 불가능 합니다.");
             return false;
         }
      });
   });
   </script>

   <!-- 본문 시작 -->
   <main align="center">
      <form action="reservationInsert.dz" method="post">
         <div class="header-background-area">
            <img src="/resources/images/mapListMain.png" alt="뒷배경이미지">
         </div>
         
         <div id="main-title">예약하기&nbsp;&nbsp;<span id="main-title-font">${shop.shopName }</span></div>
         
         <div class="frame">
			<div id="calendarSection">
				<div id="calendarText"><h1 class="titleH1">예약날짜 선택</h1></div>
            	<div id='calendar'></div>
			</div>
			
			
            <div id="selectAreaFirst">
               <div id="rTimeText"><h1 class="titleH1">예약시간 선택</h1></div>
               <div id="selectTSection">
               	<select name="selectTime" id="selectTime" class="select" onchange="timeChange(this.value);">
                  	 <option value="0">선택</option>
                  <c:forEach var="i" begin="${startTime }" end="${endTime }">
                     <option value="${i }">${i }시</option>
                  </c:forEach>
               	</select>
               </div>
               
               <div id="countDiv">
               	<div id="rCountText"><h1 class="titleH1">인원수 선택</h1></div>
               	<div id="selectCSection">
               		<select name="selectCount" id="selectCount" class="select" onchange="countChange(this.value);">
                  			<option value="0">선택</option>
                  		<c:forEach var="i" begin="1" end="${shop.shopMaxReserv }">
                     		<option value="${i }">${i }명</option>
                  		</c:forEach>
               		</select>
               	</div>
               </div>
               
               <div class="pointUse" id="pointDiv">
               	<div id="rPointText"><h1 class="titleH1">포인트 사용여부</h1></div>
               	  <div class="pRadion">
                     <input type="radio" name="point" id="pointY" value="Y" onclick="pointchecked();">
                     <label for="pointY" class="label">포인트 사용</label>
                  </div>
                  <div class="pRadion">
                     <input type="radio" name="point" id="pointN" value="N" onclick="pointchecked();"> 
                     <label for="pointN" class="label">포인트사용거부</label>
                  </div>
                  <div id="pointUseDiv" style="display:none">
                     <h1 class="titleH1">사용할 포인트 금액</h1>
                     <div id="pointBox">
                     <input type="text" id="pointText" name="point"  value="${user.userPoint }">
                     <input type="button" value="포인트사용" id="pButton" class="button">
                  	 </div>
                  </div>
               </div>
            </div>
            
            <div id="selectAreaThird">
		     <div id="rImfomationPrint">
		     	<div class="resultBox">
		     		<input type="hidden" name="reserveDate" value=""><br>
		            <h2>선택한 예약 날짜 : <span id="rDateSpan"></span></h2>
		            
		            <input type="hidden" name="reserveTime" value=""> <br> 
		            <h2>선택한 예약 시간 : <span id="rTimeSpan"></span></h2>
		            
		            <input type="hidden" name="reserveCount" value=""><br> 
		            <h2>선택한 인원 : <span id="rCountSpan"></span></h2>
		     	</div>
		            
		        <div class="resultBox">
		        	<input type="hidden" name="pointYn" value=""><br>
		            <h2>포인트 사용 여부 : <span id="pointYnSpan"></span></h2><br>
		            
		            <h2>소지한 포인트 : <span id="userPointDiv"></span></h2><br>
		            <span id="paymentPoint"></span>
                  	<input type="hidden" name="paymentPoint" value="${reservation.paymentPoint}">
                  		<h2>포인트 사용 금액 : <span id="paymentSpan"></span></h2>
                  	</div>
		        </div>    
		            
		            
		
				    <input type="hidden" name="shopName" value="${shop.shopName }"><br>
		            <input type="hidden" name="shopNo" value="${shop.shopNo }"><br> 
		                  
		            <input type="hidden" name="userNo" value="${loginUser.userNo }"><br>
		            <input type="hidden" name="startTime" value="${shop.startTime }"><br> 
		            <input type="hidden" name="endTime" value="${shop.endTime }"><br>
		            <input type="hidden" name="businessDay" value="${shop.businessDay }"><br> 
		            <input type="hidden"  name="userPoint" value="${loginUser.userPoint }">
	             	<div class="reservationArea">
	                	<input type="submit" value="예약하기" id="rButton" class="button" onclick="return finalCheck();">
	             	</div>
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