<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/payment/paymentForm.css">
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<title>돈쭐내기</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menubar.jsp"></jsp:include>
	
	<main>
		<div class="header-background-area">
	        <img src="/resources/images/payment/donjjulmainbanner.jpg" alt="뒷배경이미지">
	    </div>
		<div id="main-title">돈쭐내기&nbsp;&nbsp;<span id="main-title-shop">${shop.shopName }</span></div>
		<div class="frame">
			<form action="" method="post">
			
				<div id="lay1" class="payment lay1">
					<div class="lay-title">
						<span class="title-span">메뉴 선택</span>
					</div>
					<div class="lay1-content">
						<!-- for each문(가게shop/메뉴mainMenu) -->
						<c:forEach items="${mList }" var="menu">
							<label class="menu-choice"><input type="hidden" name="menu-price" value="${menu.mainMenuPrice }"><input type="radio" name="menu" value="${menu.mainMenuName }" onclick="menuChoice(this)"><span>${menu.mainMenuName }&nbsp;&nbsp;${menu.mainMenuPrice }원</span></label>
						</c:forEach>
					</div>
				</div>
				<div id="lay2" class="payment">
					<div id="lay2-1" class="lay2 amount">
						<div class="lay-title">
							<span class="title-span">수량 선택</span>
						</div>
						<div class="amount-btn-div">
							<!-- 스타일 다시 주기 -->
							<label class="amount-btn"><input type="radio" name="amountC" value="1" onclick="amountChoice(this.value)"><span>X 1</span></label><label class="amount-btn"><input type="radio" name="amountC" value="2" onclick="amountChoice(this.value)"><span>X 2</span></label><label class="amount-btn"><input type="radio" name="amountC" value="3" onclick="amountChoice(this.value)"><span>X 3</span></label>
						</div>		
					</div>
					<div id="lay2-2" class="lay2 point" >
						<div class="lay-title">
							<span class="title-span">포인트 사용</span>
						</div>
						<div>
							<div id="lay2-point-div">
								&nbsp;&nbsp;보유 포인트 : <input type="text" id="userPoint" value="${loginUser.userPoint }" readonly/> 원 <br>
								&nbsp;&nbsp;가용 포인트 : <input type="text" id="userPoint" value="${loginUser.userPoint }" readonly/> 원 <br>
								&nbsp;&nbsp;사용 포인트 : <input type="text" id="usePoint" onkeyup="pointUse()" value="0" placeholder="사용할 포인트 입력"/> 원 <br>
								<!-- <input type="text" id="pCount" placeholder="사용할 포인트 입력"/>원 --> 
							</div>
								&nbsp;&nbsp;<button id="pSubmit">포인트 사용하기</button>
						</div>
					</div>
				</div>
				<div id="lay3" class="payment lay3" >
					<div class="lay-title">
						<span class="title-span">결제 정보</span>
					</div>
					<div>
						<div id="lay3-payment-result">
							메뉴명 : <input type="text" name="menuName" value="" readonly/><!-- <input type="text" name="menu-name" read/> --><br>
							수 량 : <input type="text" name="amount" value="" readonly/> <br>
							메뉴 총액 : <input type="text" name="menu-fin-price" value="0" readonly/> <br>
							포인트 사용 : <input type="text" name="use-point" value="0" readonly/> <br>
							결제 금액 : <input type="text" name="donPrice" value="0" readonly/>
							<!-- 위에 폼 넘기려면 vo이름으로 맞춰야함 맞춰주기 | 가격 어쩔 겨-포인트제외or총(donPrice)-->
							
							<input type="hidden" name="userNo" value="${loginUser.userNo }"/>
							<input type="hidden" name="shopNo" value="${shop.shopNo }" />
							<input type="hidden" name="shopName" value="${shop.shopName }" />
						</div>	
						 <br>
						<input type="submit" id="payment-btn" value="돈쭐내러 가기">
					</div>
				</div>
				
			</form>
		</div>
	</main>

<script type="text/javascript">
	$("#payment-btn").on("click", function(e){
	    e.preventDefault();
	    var usePoint = $("#usePoint").val();
		var userPoint = $("#userPoint").val();
		usePoint = Number(usePoint);
		userPoint = Number(userPoint);
		if(usePoint > userPoint) {
			alert("포인트 사용 가능 범위를 넘었습니다!");
			return false;
		}
		// 돈쭐내기 버튼으로 포인트 까주기
		var sum = $("input[name='menu-fin-price']").val();
		var fin = sum - usePoint;
		$("input[name='donPrice']").val(fin);
	    
		
	    var IMP = window.IMP; // 생략가능
	    IMP.init('imp57766104'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
	    
	    // 돈쭐 총금액
	    var donPrice = $("input[name='menu-fin-price']").val();
	    
	    var finPrice = $("input[name='donPrice']").val();
	    var menuName = $("input[name='menuName']").val();
	    var amount = $("input[name='amount']").val();
	    var usePoint = $("input[name='use-point']").val();
	    var shopNo = '${shop.shopNo }';
	    var shopName = '${shop.shopName }';
	    
	    var msg;
	    
	    /* $.ajax({
            url: "insertDonList.dz", //cross-domain error가 발생하지 않도록 주의해주세요
            			// data 보낼 url
            type: 'POST',
            data: {
                //imp_uid : rsp.imp_uid,
                //기타 필요한 데이터가 있으면 추가 전달
                // 가게 이름, 날짜, 내역(얼마)
                "donPrice" : donPrice,
                "menuName" : menuName,
                "amount" : amount,
                "shopNo" : shopNo,
                "shopName" : shopName,
                "use-point" : usePoint
            }
        }); */
	    
	    IMP.request_pay({
	        pg : 'kakaopay',
	        pay_method : 'card',
	        merchant_uid : 'merchant_' + new Date().getTime(),
	        name : '돈쭐내기 결제 : ${shop.shopName }',
	        amount : finPrice, // 최종 결제 금액 
	        buyer_email : '${loginUser.userEmail }',
	        buyer_name : '${loginUser.userName }',
	        buyer_tel : '${loginUser.userPhone }',
	        buyer_addr : '주소컬럼 없음',
	        buyer_postcode : '123-456', // ??
	        //m_redirect_url : 'http://www.naver.com'
	    }, function(rsp) {
	        console.log(rsp);
	 	   if ( rsp.success ) {
	            //[1] 서버단에서 결제정보 조회를 위해 jQuery ajax로 imp_uid 전달하기
	            $.ajax({
	            	url: "insertDonList.dz", //cross-domain error가 발생하지 않도록 주의해주세요
        			// data 보낼 url
			        type: 'POST',
			        data: {
			            //imp_uid : rsp.imp_uid,
			            //기타 필요한 데이터가 있으면 추가 전달
			            // 가게 이름, 날짜, 내역(얼마)
			            "donPrice" : donPrice,
			            "menuName" : menuName,
			            "amount" : amount,
			            "shopNo" : shopNo,
			            "shopName" : shopName,
			            "use-point" : usePoint
			        }
	            }).done(function(data) {
	                //[2] 서버에서 REST API로 결제정보확인 및 서비스루틴이 정상적인 경우
	                if ( everythings_fine ) {
	                    msg = '결제가 완료되었습니다.';
	                    msg += '\n고유ID : ' + rsp.imp_uid;
	                    msg += '\n상점 거래ID : ' + rsp.merchant_uid;
	                    msg += '\결제 금액 : ' + rsp.paid_amount;
	                    msg += '카드 승인번호 : ' + rsp.apply_num;
	                    
	                    alert(msg);
	                } else {
	                    //[3] 아직 제대로 결제가 되지 않았습니다.
	                    //[4] 결제된 금액이 요청한 금액과 달라 결제를 자동취소처리하였습니다.
	                }
	            });
	            //성공시 이동할 페이지 (룰렛페이지인지 중간에 결제 완료창 있는지)
	            //location.href='<%=request.getContextPath()%>/order/paySuccess?msg='+msg;
	            location.href="rouletteView.dz"; // 룰렛 페이지
	        } else {
	            msg = '결제에 실패하였습니다.';
	            msg += '에러내용 : ' + rsp.error_msg;
	            //실패시 이동할 페이지
	            //location.href="<%=request.getContextPath()%>/order/payFail";
	            alert(msg);
	            location.href="paymentFormView.dz"; 
	        }
	    });
	    
	});

	// 메뉴 선택하면
	function menuChoice(menu){
		var menuName = $(menu).val();
		var menuPrice = $(menu).prev().val();
		
		console.log("메뉴선택-메뉴이름" + menuName);
		console.log("메뉴선택-메뉴가격" + menuPrice);
		
		$("input[name='menuName']").val(menuName); // lay3-메뉴명
		//$("input[name='menu-fin-price']").val(menuPrice); // lay3-메뉴총액
		
		$("input[name='menu-price']").val(menuPrice); // lay3-메뉴총액
		console.log("hidden"+$("input[name='menu-price']").val());
	}

	// 수량 선택하면
	function amountChoice(amount){
		$("input[name='amount']").val(amount); // lay3-수량
		console.log("수량선택-수량"+amount);
		
		
		var mName = $("input[name='menuName']").val(); // lay3의 메뉴이름
		console.log("lay3의 메뉴이름" + mName);
		
		//var mPrice = $("input[name='menu-fin-price']").val(); // lay3의 메뉴가격
		var mPrice = $("input[name='menu-price']").val()
		mPrice = Number(mPrice);
		console.log("lay3의 메뉴가격" + mPrice);
		
		var amount = $("input[name='amount']").val(); // lay3의 수량
		amount = Number(amount);
		console.log("lay3의 메뉴수량" +amount);
		
		var priceSum = mPrice * amount; // 가격*수량
		$("input[name='menu-fin-price']").val(priceSum);
		console.log("lay3 가격*수량" + priceSum);
		
		var usePoint = $("#usePoint").val(); // 사용할 포인트
		usePoint = Number(usePoint);
		
		var finPrice = priceSum - usePoint; // 결제할 가격 = (가격*수량) - 포인트
		console.log("lay3 결제할가격(차감)" + finPrice);
		$("input[name='donPrice']").val(finPrice); // 결제 가격 = 총가격 - 사용포인트
	}

	// 포인트 입력시 자동으로(onkeyup)
	function pointUse(){
		var usePoint = $("#usePoint").val(); // 사용할 포인트
		
		$("input[name='use-point']").val(usePoint); // lay3 포인트사용
	}
	
	// 포인트 버튼 클릭 시
	$(function() {
		$("#pSubmit").on("click", function(e){
			e.preventDefault(); // submit 버튼으로 작동하는 것을 중단.
			
			var usePoint = $("#usePoint").val();
			var userPoint = $("#userPoint").val();
			console.log(userPoint);console.log(usePoint);
			usePoint = Number(usePoint);
			userPoint = Number(userPoint);
			console.log(userPoint);console.log(usePoint);
			
			if(usePoint > userPoint) {
				alert("포인트 사용 가능 범위를 넘었습니다!");
				return false;
			}
			
			/* $("#lay2").show();
			$("#lay2-1").show(500);
			$("#lay2-2").show(500);
			$("#lay3").hide(); */
			// 포인트 사용 클릭 시 
			// lay3 이 보여야하며
			// lay3의 내용 - 총가격(가격*수량), 포인트, 결제 금액 나타내야하고
			// 
			$("#lay3").show(500);
			//$("input[name='menu-price']").val(); // 총가격 = 메뉴가격 * 수량
			
			var mName = $("input[name='menuName']").val(); // lay3의 메뉴이름
			console.log("lay3의 메뉴이름" + mName);
			
			//var mPrice = $("input[name='menu-fin-price']").val(); // lay3의 메뉴가격
			var mPrice = $("input[name='menu-price']").val()
			mPrice = Number(mPrice);
			console.log("lay3의 메뉴가격" + mPrice);
			
			var amount = $("input[name='amount']").val(); // lay3의 수량
			amount = Number(amount);
			console.log("lay3의 메뉴수량" +amount);
			
			var priceSum = mPrice * amount; // 가격*수량
			$("input[name='menu-fin-price']").val(priceSum);
			console.log("lay3 가격*수량" + priceSum);
			
			var usePoint = $("#usePoint").val(); // 사용할 포인트
			usePoint = Number(usePoint);
			
			var finPrice = priceSum - usePoint; // 결제할 가격 = (가격*수량) - 포인트
			console.log("lay3 결제할가격(차감)" + finPrice);
			$("input[name='donPrice']").val(finPrice); // 결제 가격 = 총가격 - 사용포인트
		});
	});

</script>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>