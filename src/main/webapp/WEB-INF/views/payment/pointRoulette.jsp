<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/payment/pointRoulette.css" />
<title>돈쭐내기</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menubar.jsp"></jsp:include>
	<main>
		<div class="header-background-area">
			<img src="/resources/images/payment/donjjulmainbanner.jpg" alt="뒷배경이미지">
		</div>
		<div id="main-title">포인트 룰렛&nbsp;&nbsp;<span id="main-title-shop">${shopName }</span></div>
		<div class="frame">
			<h4>돈쭐낸 가격의 최대 10% 상당의 포인트를 지급받으세요!</h4>
				
			<c:if test="${rouletteYN == 'Y' }">
				<form action="saveRoulettePoint.dz" method="post">
					<div class="roulette">
						<div id="point-roulette">
							<div>
								<img src="/resources/images/roulette/roulette.png" id="roulette-img"> 
								<img src="/resources/images/roulette/niddle.png" id="needle">
							</div>
							<br> 
								<input type="hidden" name="savePoint" value="0" />
								<input type="hidden" name="donNo" value="${donNo }"/>
								<input type="hidden" name="shopName" value="${shopName }"/>
								<input type="hidden" name="donPrice" value="${donPrice }"/>
								<input type="button" value="시작" id="start_btn" onclick="this.disabled=true"></input> 
						</div>
					</div>
					<div class="result">
						<div class="lay-title">
							<span class="title-span">당첨 결과</span>
						</div>
						<div class="lay-content">
							<div>
								당첨 결과 : <span id="per">&nbsp; &nbsp; &nbsp; 0 </span> %
								<br>
								당첨 포인트 : <span id="won">&nbsp;&nbsp; 0 </span> 원
							</div>
							<input type="submit" id="point-save-btn" value="포인트 받기"">
							 <!-- onclick="this.disabled=true;this.value='받는 중'; this.form.submit(); -->
						</div>
					</div>
				</form>
			</c:if>
			
			<c:if test="${rouletteYN == 'N' }">
				<form action="saveRoulettePoint.dz" method="post">
					<div class="roulette">
						<div id="point-roulette">
							<div>
								<img src="/resources/images/roulette/roulette.png" id="roulette-img-n"> 
								<img src="/resources/images/roulette/niddle.png" id="needle-n">
							</div>
							<br> 
								<input type="hidden" name="savePoint" value="0" />
								<input type="hidden" name="donNo" value="${donNo }"/>
								<input type="hidden" name="shopName" value="${shopName }"/>
								<input type="hidden" name="donPrice" value="${donPrice }"/>
								<input type="button" value="시작" id="start_btn" onclick="this.disabled=true"></input> 
						</div>
					</div>
					<div class="result">
						<div class="lay-title">
							<span class="title-span">당첨 결과</span>
						</div>
						<div class="lay-content">
							<div>
								<span id="per">&nbsp; &nbsp;이미 룰렛을 돌리셨습니다!</span>
								<br>
							</div>
							<button type="button" id="point-save-btn-n" onclick="location.href='/'">포인트 받기 완료</button>
						</div>
					</div>
				</form>
			</c:if>

		</div>
	</main>
	<script>
		window.onload = function() {
			/* var pArr = [ "0", "1", "2", "3", "4:꽝", "5", "6", "7", "8", "9" ]; */
			var pArr = [ 5, 8, 9, 6, 1, 4, 3, 2, 7, 10 ];
			
			$('#start_btn').click(function() {
				rotation();
			});
		
			function rotation() {
				$("#roulette-img").rotate({
					angle : 0,
					animateTo : 360 * 5 + randomize(0, 360),
					center : [ "50%", "50%" ],
					easing : $.easing.easeInOutElastic,
					callback : function() {
						var n = $(this).getRotateAngle();
						endAnimate(n);
					},
					duration : 5000
				});
			}
		
			function endAnimate($n) {
				var n = $n;
				$('#result_id').html("<p>움직인각도:" + n + "</p>");
				var real_angle = n % 360 + 18;
				var part = Math.floor(real_angle / 36);
		
				$('#result_id2').html("<p>상품범위:" + part + "</p>");
		
				if (part < 1) {
					$('#result_id3').html("<p>당첨내역:" + pArr[0] + "</p>");
					return;
				}
		
				if (part >= 10) {
					$('#result_id3').html("<p>당첨내역:" + pArr[pArr.length - 1] + "</p>");
					return;
				}
		
				$('#result_id3').html("<p>당첨내역:" + pArr[part] + "</p>");
				//var winPoint = Number(pArr[part]);
				//console.log("당첨포인트 : " + winPoint);
				
				// 결과 넣어주기
				$("#per").html("&nbsp; &nbsp; &nbsp; "+pArr[part]);
				var persent = pArr[part] * 0.01;
				console.log(persent);
				console.log($("input[name='donNo']").val());
				console.log($("input[name='donPrice']").val());
				var donPrice = $("input[name='donPrice']").val();
				console.log(donPrice);
				var point = donPrice * persent;
				point = point.toFixed(0);
				console.log(point);
				$("#won").html("&nbsp;&nbsp; "+point);
				
				$("input[name='savePoint']").val(pArr[part]);
				console.log("hidden당첨포인트 : " + $("input[name='savePoint']").val());
			}
		
			function randomize($min, $max) {
				return Math.floor(Math.random() * ($max - $min + 1)) + $min;
			}
			
			$("#point-save-btn").click(function() {
				console.log($("input[name='savePoint']").val());
				if($("input[name='savePoint']").val() == 0){
					alert("포인트 룰렛을 돌려 포인트를 지급받으세요!");
					return false;
				}
			});
			
		};
		
		//새로고침 막기
		/* function doNotReload(){
		    if( (event.ctrlKey == true && (event.keyCode == 78 || event.keyCode == 82)) || (event.keyCode == 116) ) {
		        event.keyCode = 0;
		        event.cancelBubble = true;
		        event.returnValue = false;
		        alert("새로고침이 불가능합니다.");
		        return false;
		    } 
		}
		document.onkeydown = doNotReload;
		 */

		// 뒤로 가기 방지
		window.history.forward();
		function noBack() {
			window.history.forward();
		}

	</script>	
	
	<script type="text/javascript" src="/resources/js/roulette/jQueryRotateCompressed.js"></script>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>