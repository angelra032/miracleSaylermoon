<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/payment/pointRoulette.css"> 
<!-- <link href="/static/bootstrap.min.css" rel="stylesheet"> -->

<!-- <link rel="stylesheet" href="resources/js/roulette/jQueryRotateCompressed.js"> -->
<script type="text/javascript" src="resources/js/roulette/jquery-1.11.3.min.js"></script>
<!-- <script type="text/javascript" src="resources/js/roulette/jQueryRotateCompressed.js"></script>
 -->
<script type="text/javascript" src="http://jqueryrotate.googlecode.com/svn/trunk/jQueryRotate.js"></script>


<style>

#image {
	margin:50px 50px;
	z-index:10;
}
#n_id {
	position:absolute;
	left:286px;
	top:75px;
	z-index:20;
}

</style>

<title>돈쭐내기</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menubar.jsp"></jsp:include>
	
	<main>
	

		
		
	
	
	
		<div class="header-background-area">
	        <img src="/resources/images/mapListMain.png" alt="뒷배경이미지">
	    </div>
		<div id="main-title">포인트 룰렛</div>
		<div class="frame">
			<h1>진짜파스타</h1>
			<div class="roulette">
				<h2>포인트 룰렛</h2>
				<h4>최대 5000원 상당의 포인트를 지급받으세요!</h4>
				
				<div id="point-roulette">
					
					<img src="/resources/images/roulette/roulette.png" id="image">
					<img src="/resources/images/roulette/niddle.png" id="n_id">
					<br />
					<input type='button' value='시작' id='start_btn'></input>
					<div id="result_id"></div>
					<div id="result_id2"></div>
					<div id="result_id3"></div>
					
					
					<script>
						window.onload = function(){
							
							var pArr = ["0","1","2","3","4:꽝","5","6","7","8","9"];
						
							$('#start_btn').click(function(){
								rotation();
							});
						
							function rotation(){
								$("#image").rotate({
								  angle:0, 
								  animateTo:360 * 5 + randomize(0, 360),
								  center: ["50%", "50%"],
								  easing: $.easing.easeInOutElastic,
								  callback: function(){ 
												var n = $(this).getRotateAngle();
												endAnimate(n);
											},
								  duration:5000
							   });
							}
						
							function endAnimate($n){
								var n = $n;
								$('#result_id').html("<p>움직인각도:" + n + "</p>");
								var real_angle = n%360 +18;
								var part = Math.floor(real_angle/36);
							
								$('#result_id2').html("<p>상품범위:" + part + "</p>");
						
								if(part < 1){
									$('#result_id3').html("<p>당첨내역:" + pArr[0] + "</p>");
									return;
								}
						
								if(part >= 10){
									$('#result_id3').html("<p>당첨내역:" + pArr[pArr.length-1] + "</p>");
									return;
								}
						
								$('#result_id3').html("<p>당첨내역:" + pArr[part] + "</p>");
							}
						
							function randomize($min, $max){
								return Math.floor(Math.random() * ($max - $min + 1)) + $min;
							}
						};
					</script>
					
					
					
					
				</div>
				<button id="point-save-btn">포인트 받기</button>
			</div>
			
		</div>
		
	</main>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>