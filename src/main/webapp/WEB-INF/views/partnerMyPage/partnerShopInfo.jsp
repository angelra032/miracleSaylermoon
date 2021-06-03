<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/partnermypage/partnerShopJoin.css"> 
<!-- <link href="/static/bootstrap.min.css" rel="stylesheet"> -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<title>돈쭐 회원가입</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menubar.jsp"></jsp:include>
	<main>
		<div class="header-background-area">
        	<img src="/resources/images/mapListMain.png" alt="뒷배경이미지">
   		</div>	
		<div id="main-title">가게등록</div>
		<div class="frame">
			<h4>가게 상세페이지 내용을 입력하세요</h4>
			<div class="tabcontent">
				<div class="content-body">
					<div id="dreamEnrollView" class="tab-content current">
						<form action="shopRegister.dz" method="post"  enctype="multipart/form-data">
							<div class="form-head form-head2">
								가게이름&nbsp;
								<span class="required">*</span>&nbsp;&nbsp;
							</div>
							<div class="form-body">
								<input name="shopName" class="form-elem shopName" type="text" maxlength="40" placeholder="간이사업자명 입력" value="${ shop.shopName }" readonly>
							</div>
							
							
							<div class="form-head form-head2">
								가게 위치&nbsp;
								<span class="required">*</span>&nbsp;&nbsp;
								<div class="form-noti cardnoti card_noti_0">카드번호를 입력해주세요.</div>
								<div class="form-noti cardnoti card_noti_1">유효하지 않는 카드번호입니다.</div>
								<div class="form-noti cardnoti card_noti_2">이미 등록된 카드번호입니다.</div>
								<div class="form-noti cardnoti card_suc">본인인증성공</div>
							</div>
							<div class="form-body">
								<input type="text" id="zip" name="zip" class="form-elem" placeholder="우편번호">
                                <button id="addr-search-btn" type="button"  onclick="openZipSearch()">입력</button>
                                <div id="aadr1-input" class="form-body">
                                    <input type="text" name="addr1" id="addr1" class="form-elem" placeholder="기본주소" readonly>
                                </div>
                                <div id="addr2-input" class="form-body">
                                    <input type="text" name="addr2" id="addr2" class="form-elem" placeholder="상세주소">
                                </div>
							</div>
							
							<div class="form-head form-head2">
								가게 간단위치&nbsp;
								<span class="required">*</span>&nbsp;&nbsp;
								<div class="form-noti namenoti name_noti_0">이름을 입력해 주세요.</div>
								<div class="form-noti namenoti name_noti_1">한글 2자 이상 입력</div>
							</div>
							<div class="form-body">
								<input name="shopShortAddr" class="form-elem shopShortAddr" type="text" maxlength="20" value="${ shop.shopShortAddr }" placeholder="한눈에 확인할 수 있는 주소, 한글 2자 이상 입력">
							</div>
							
							<div class="form-head form-head2">
								가게 타겟층&nbsp;
								<span class="required">*</span>&nbsp;&nbsp;
								<div class="form-noti nicknoti nick_noti_0">닉네임을 입력해 주세요.</div>
								<div class="form-noti nicknoti nick_noti_1">한글 2자 이상 입력</div>
								<div class="form-noti nicknoti nick_noti_2">이미 사용중인 닉네임입니다.</div>
							</div>
							<div class="form-body">
								<input name="shopTarget" class="form-elem shopTarget" type="text" maxlength="20" value="${ shop.shopTarget }" placeholder="타켓 입력">
							</div>
							
							<div class="form-head form-head2">
								제공메뉴&nbsp;
								<span class="required">*</span>&nbsp;&nbsp;
								<div class="form-noti phonenoti phone_noti_0">휴대폰번호를 입력해 주세요.</div>
								<div class="form-noti phonenoti phone_noti_2">이미 등록된 번호입니다.</div>
							</div>
							<div class="form-body">
								<input name="shopProduct" id="shopProduct" class="form-elem shopProduct" type="text" maxlength="25" value="${ shop.shopProduct }" placeholder="무상제공하는 메뉴 입력">
							</div>

							<div class="form-head form-head2">
								전화번호&nbsp;
								<span class="required">*</span>&nbsp;&nbsp;
								<div class="form-noti phonenoti phone_noti_0">휴대폰번호를 입력해 주세요.</div>
								<div class="form-noti phonenoti phone_noti_2">이미 등록된 번호입니다.</div>
							</div>
							<div class="form-body">
								<input name="shopPhone" id="shopPhone" class="form-elem shopPhone" type="text" maxlength="13" value="${ shop.shopPhone }" placeholder="숫자만 입력">
							</div>
							
							<div class="form-head form-head2">
								가게 분류&nbsp;
								<span class="required">*</span>&nbsp;&nbsp;
								<div class="form-noti emailnoti email_noti_0">분류를 선택해 주세요.</div>
								<div class="form-noti emailnoti email_noti_1">이메일 형식이 올바르지 않습니다.</div>
								<div class="form-noti emailnoti email_noti_2">이미 사용중인 이메일입니다.</div>
							</div>
							<div class="form-body form-select-body">
								<select id="shopTypeNum" name="shopTypeNum" class="form-select">
										<option value="0" disabled>선택</option>
									<c:if test="${ shop.shopType eq '한식' }">
										<option value="1" selected>한식</option>
										<option value="2">분식</option>
										<option value="3">일식</option>
										<option value="4">중식</option>
										<option value="5">양식</option>
										<option value="6">기타</option>
									</c:if>
									<c:if test="${ shop.shopType eq '분식' }">
										<option value="1">한식</option>
										<option value="2" selected>분식</option>
										<option value="3">일식</option>
										<option value="4">중식</option>
										<option value="5">양식</option>
										<option value="6">기타</option>
									</c:if>
									<c:if test="${ shop.shopType eq '일식' }">
										<option value="1">한식</option>
										<option value="2">분식</option>
										<option value="3" selected>일식</option>
										<option value="4">중식</option>
										<option value="5">양식</option>
										<option value="6">기타</option>
									</c:if>
									<c:if test="${ shop.shopType eq '중식' }">
										<option value="1">한식</option>
										<option value="2">분식</option>
										<option value="3">일식</option>
										<option value="4" selected>중식</option>
										<option value="5">양식</option>
										<option value="6">기타</option>
									</c:if>
									<c:if test="${ shop.shopType eq '양식' }">
										<option value="1">한식</option>
										<option value="2">분식</option>
										<option value="3">일식</option>
										<option value="4">중식</option>
										<option value="5" selected>양식</option>
										<option value="6">기타</option>
									</c:if>
									<c:if test="${ shop.shopType eq '기타' }">
										<option value="1">한식</option>
										<option value="2">분식</option>
										<option value="3">일식</option>
										<option value="4">중식</option>
										<option value="5">양식</option>
										<option value="6" selected>기타</option>
									</c:if>
								</select>
							</div>
							
							<div class="form-head form-head2">
								주차유무&nbsp;
								<span class="required">*</span>&nbsp;&nbsp;
								<div class="form-noti emailnoti email_noti_0">주차유무를 선택해 주세요.</div>
								<div class="form-noti emailnoti email_noti_1">이메일 형식이 올바르지 않습니다.</div>
								<div class="form-noti emailnoti email_noti_2">이미 사용중인 이메일입니다.</div>
							</div>
							<div class="form-body">
								<div class="form-body form-select-body">
									<select id="shopParking" name="shopParking" class="form-select">
										<option value="0" disabled>선택</option>
										<c:if test="${ shop.shopParking eq 'Y' || shop.shopParking eq 'y' }">
											<option value="Y" selected>가능</option>
											<option value="N">불가능</option>
										</c:if>
										<c:if test="${ shop.shopParking eq 'N' || shop.shopParking eq 'n' }">
											<option value="Y">가능</option>
											<option value="N" selected>불가능</option>
										</c:if>
									</select>
								</div>
							</div>
							
							<div class="form-head form-head2">
								최대예약가능인원&nbsp;
								<span class="required">*</span>&nbsp;&nbsp;
								<div class="form-noti emailnoti email_noti_0">이메일을 입력해 주세요.</div>
								<div class="form-noti emailnoti email_noti_1">이메일 형식이 올바르지 않습니다.</div>
								<div class="form-noti emailnoti email_noti_2">이미 사용중인 이메일입니다.</div>
							</div>
							<div class="form-body">
								<input name="shopMaxReserv" class="form-elem shopMaxReserv" type="text" maxlength="20" value="${ shop.shopMaxReserv }" placeholder="시간당 예약가능한 인원수">
							</div>
							
							<div class="form-head form-head2">
								시작시간-끝내는시간&nbsp;
								<span class="required">*</span>&nbsp;&nbsp;
								<div class="form-noti emailnoti email_noti_0">시간을 선택해 주세요.</div>
								<div class="form-noti emailnoti email_noti_1">이메일 형식이 올바르지 않습니다.</div>
								<div class="form-noti emailnoti email_noti_2">이미 사용중인 이메일입니다.</div>
							</div>
							<div class="form-body form-select-body-2">
								<select name="startTime" id="startTime" class="form-select-2">
									<option value="0" disabled>여는시간</option>
									<c:forEach begin="1" end="24" varStatus="status">
										<c:if test="${ shop.startTime ne status.count }">
											<option value="${ status.count }">${ status.count }시</option>
										</c:if>
										<c:if test="${ shop.startTime eq status.count }">
											<option value="${ status.count }" selected>${ status.count }시</option>
										</c:if>
									</c:forEach>
								</select>
							</div>
							<div class="form-body form-select-body-2 form-select-body-2-1">
								<select name="endTime" id="endTime" class="form-select-2">
									<option value="0" disabled>닫는시간</option>
									<c:forEach begin="1" end="24" varStatus="status">
										<c:if test="${ shop.endTime eq status.count }">
											<option value="${ status.count }" selected>${ status.count }시</option>
										</c:if>
										<c:if test="${ shop.endTime ne status.count }">
											<option value="${ status.count }">${ status.count }시</option>
										</c:if>
										<option value="${ status.count }">${ status.count }시</option>
									</c:forEach> 
								</select>
							</div>
							
							<div class="form-head form-head2 form-business">
								영업일&nbsp;
								<span class="required">*</span>&nbsp;&nbsp;
								<div class="form-noti emailnoti email_noti_0">이메일을 입력해 주세요.</div>
								<div class="form-noti emailnoti email_noti_1">이메일 형식이 올바르지 않습니다.</div>
								<div class="form-noti emailnoti email_noti_2">이미 사용중인 이메일입니다.</div>
							</div>
							<div class="form-body">
								<label class="label-business" for="business-1">월</label> <input type="checkbox" name="businessNum" id="business-1" value="1" >
								<label class="label-business" for="business-2">화</label> <input type="checkbox"  name="businessNum" id="business-2" value="2" >
								<label class="label-business" for="business-3">수</label> <input type="checkbox"  name="businessNum" id="business-3" value="3" >
								<label class="label-business" for="business-4">목</label> <input type="checkbox"  name="businessNum" id="business-4" value="4" >
								<label class="label-business" for="business-5">금</label> <input type="checkbox"  name="businessNum" id="business-5" value="5" >
								<label class="label-business" for="business-6">토</label> <input type="checkbox"  name="businessNum" id="business-6" value="6" >
								<label class="label-business" for="business-7">일</label> <input type="checkbox"  name="businessNum" id="business-7" value="7" >
							</div>
							
							
							<div class="form-head form-head2">
								가게소개&nbsp;
								<span class="required">*</span>&nbsp;&nbsp;
								<div class="form-noti emailnoti email_noti_0">이메일을 입력해 주세요.</div>
								<div class="form-noti emailnoti email_noti_1">이메일 형식이 올바르지 않습니다.</div>
								<div class="form-noti emailnoti email_noti_2">이미 사용중인 이메일입니다.</div>
							</div>
							<div class="form-body">
								<textarea name="shopContent" id="" class="form-textarea shopContent" cols="30" rows="5" placeholder="소개 입력"></textarea>
							</div>

							<div class="form-head form-head2">
								사진업로드&nbsp;
								<span class="required">*</span>&nbsp;&nbsp;
								<div class="form-noti emailnoti email_noti_0">이메일을 입력해 주세요.</div>
								<div class="form-noti emailnoti email_noti_1">이메일 형식이 올바르지 않습니다.</div>
								<div class="form-noti emailnoti email_noti_2">이미 사용중인 이메일입니다.</div>
							</div>
							<div class="form-body">
								<input name="uploadFile" class="form-elem uploadFile" type="file" maxlength="20" placeholder="영문, 숫자 또는 혼합 6~20자">
							</div>
							
							<input type="hidden" name="userNo" value="${userNo}" />
							<button class="submit-btn" type="submit">등록하기</button>
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						</form>
					</div>
				</div>
			</div>
		</div>
	</main>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
<script>
	$(function() {
		
		///휴대폰번호@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		//자동 - 생성
		var autoHypenPhone = function(str){
		      str = str.replace(/[^0-9]/g, '');
		      var tmp = '';
		      if( str.length < 4){
		          return str;
		      }else if(str.length < 7){
		          tmp += str.substr(0, 3);
		          tmp += '-';
		          tmp += str.substr(3);
		          return tmp;
		      }else if(str.length < 11){
		          tmp += str.substr(0, 3);
		          tmp += '-';
		          tmp += str.substr(3, 3);
		          tmp += '-';
		          tmp += str.substr(6);
		          return tmp;
		      }else{              
		          tmp += str.substr(0, 3);
		          tmp += '-';
		          tmp += str.substr(3, 4);
		          tmp += '-';
		          tmp += str.substr(7);
		          return tmp;
		      }
		      return str;
		}
		var phoneNum = document.querySelector('#shopPhone');

		phoneNum.onkeyup = function(){
			
		  console.log(this.value);
		  this.value = autoHypenPhone( this.value ) ;  
		}
		
		
		
		// 탭별로 양식 다르게 출력
		$('.tabmenu').click(function() {
			var activeTab = $(this).attr('data-tab');
			$('.tabmenu').css('background-color', '#f4f4f4');
			$('.tabmenu').css('color', '#333');
			$(this).css('background-color', '#0160ff');
			$(this).css('color', '#fff');
			$('.ul.tabs li').removeClass('current');
			$('.tab-content').removeClass('current');
			$(this).addClass('current');
			$("#"+activeTab).addClass('current');
		
			// 아이디 유효성검사@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			var userId = $(".current .idelem");
			var regExp = /^[a-z][a-z0-9]{5,11}$/;
			$('.current .idelem').keyup(function() {
				$('.current .idnoti').css('display', 'none');
				/* var userId = $(".current .idelem").val(); */
				$.ajax({
					url : "dupId.dz",
					data : { "userId" : userId.val() },
					success : function(result) {
						//2이미 사용중인 아이디입니다.
						if(result != 0){
							$('.current .idnoti').css('display', 'none');
							$('.current .userid-noti-2').css('color', '#ff5442');
							$('.current .userid-noti-2').css('display', 'block');
							$('.current .idelem').css('border', '1px solid #ff5442');
						} else if (!regExp.test(userId.val())) {
								$('.current .userid-noti-1').css('color', '#ff5442');
								$('.current .userid-noti-1').css('display', 'block');
								$('.current .idelem').css('border', '1px solid #ff5442');
						}else{
							$('.current .idnoti').css('display', 'none');
							$('.current .userid_suc').css('display', 'block');
							$('.current .userid_suc').css('color', '#0160ff');
							$('.current .idelem').css('border', '0');
						}
					},
					error : function() {
						console.log("전송실패");
					}
				});
			});
			$(".current .idelem").on("blur", function() {
				if(userId.val() ==""){ 
					$('.current .idnoti').css('display', 'none'); 
					$('.current .userid-noti-0').css('color', '#ff5442');
					$('.current .userid-noti-0').css('display', 'block');
					$('.current .idelem').css('border', '1px solid #ff5442');
				}
		 });  
			
			///비밀번호 찾기 유효성검사@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			var userPwd = $(".current .pwdelem");
			var regExpPw = /^[a-z0-9][a-z0-9]{5,11}$/;
			userPwd.keyup(function() {
				$('.current .pwnoti').css('display', 'none');
				if (!regExpPw.test(userPwd.val())) {
					$('.current .password_noti_1').css('color', '#ff5442');
					$('.current .password_noti_1').css('display', 'block');
					$('.current .pwdelem').css('border', '1px solid #ff5442');
				}else{
					$('.current .pwnoti').css('display', 'none');
					$('.current .pwdelem').css('border', '0');
				}
			});
			$(".current .re_pwd").keyup(function() {
				$('.current .pwnoti').css('display', 'none');
				if($(".current .re_pwd").val()!=userPwd.val()){
					$('.current .pwnoti').css('display', 'none');
					$('.current .password_noti_2').css('color', '#ff5442');
					$('.current .password_noti_2').css('display', 'block');
					$('.current .re_pwd').css('border', '1px solid #ff5442');
				}else{
					$('.current .pwnoti').css('display', 'none');
					$('.current .pwdelem').css('border', '0');
				}
			});
			/////비밀번호 입력해주세요
			userPwd.on("blur", function() {
				$('.current .pwnoti').css('display', 'none');
				if (userPwd.val() =="") {
					$('.current .password_noti_0').css('color', '#ff5442');
					$('.current .password_noti_0').css('display', 'block');
					$('.current .pwdelem').css('border', '1px solid #ff5442');
				}else if(!regExpPw.test(userPwd.val())){
					$('.current .pwnoti').css('display', 'none');
					$('.current .password_noti_1').css('color', '#ff5442');
					$('.current .password_noti_1').css('display', 'block');
					$('.current .pwdelem').css('border', '1px solid #ff5442');
				}else if($(".current .re_pwd").val()==""){
					$('.current .re_pwd').css('border', '1px solid #ff5442');
				}else if($(".current .re_pwd").val()!=userPwd.val()){
					$('.current .pwnoti').css('display', 'none');
					$('.current .password_noti_2').css('color', '#ff5442');
					$('.current .password_noti_2').css('display', 'block');
					$('.current .re_pwd').css('border', '1px solid #ff5442');
				}else{
					$('.current .pwnoti').css('display', 'none');
					$('.current .pwdelem').css('border', '0');
				}
			});
			
			//이름 유효성 검사@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			var userName = $(".current .nameelem");
			var regExpName = /^[가-힣]{2,11}$/;
			userName.keyup(function() {
				$('.current .namenoti').css('display', 'none');
				if (!regExpName.test(userName.val())) {
					$('.current .name_noti_1').css('color', '#ff5442');
					$('.current .name_noti_1').css('display', 'block');
					$('.current .nameelem').css('border', '1px solid #ff5442');
				}else{
					$('.current .namenoti').css('display', 'none');
					$('.current .nameelem').css('border', '0');
				}
			});
			userName.on("blur", function() {
				$('.current .namenoti').css('display', 'none');
				if (userName.val() =="") {
					$('.current .name_noti_0').css('color', '#ff5442');
					$('.current .name_noti_0').css('display', 'block');
					$('.current .nameelem').css('border', '1px solid #ff5442');
				}else if(!regExpName.test(userName.val())){
					$('.current .namenoti').css('display', 'none');
					$('.current .name_noti_1').css('color', '#ff5442');
					$('.current .name_noti_1').css('display', 'block');
					$('.current .nameelem').css('border', '1px solid #ff5442');
				}else{
					$('.current .namenoti').css('display', 'none');
					$('.current .nameelem').css('border', '0');
				}
			});
			
			//간이사업자명 영어허용@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			userSimpleName = $(".current .simplenameelem");
			var regExpSimpleName = /^[가-힣,a-z,A-Z]{2,11}$/; //undefind를 소문자로 받아들인다
			userSimpleName.keyup(function() {
				$('.current .simplenamenoti').css('display', 'none');
				if (!regExpSimpleName.test(userSimpleName.val())) {
					$('.current .simplename_noti_1').css('color', '#ff5442');
					$('.current .simplename_noti_1').css('display', 'block');
					$('.current .simplenameelem').css('border', '1px solid #ff5442');
				}else{
					$('.current .simplenamenoti').css('display', 'none');
					$('.current .simplenameelem').css('border', '0');
				}
			});
			userSimpleName.on("blur", function() {
				$('.current .simplenamenoti').css('display', 'none');
				if (userSimpleName.val() =="") {
					$('.current .simplename_noti_0').css('color', '#ff5442');
					$('.current .simplename_noti_0').css('display', 'block');
					$('.current .simplenameelem').css('border', '1px solid #ff5442');
				}else if(!regExpSimpleName.test(userSimpleName.val())){
					$('.current .simplenamenoti').css('display', 'none');
					$('.current .simplename_noti_1').css('color', '#ff5442');
					$('.current .simplename_noti_1').css('display', 'block');
					$('.current .simplenameelem').css('border', '1px solid #ff5442');
				}else{
					$('.current .siplenamenoti').css('display', 'none');
					$('.current .simplenameelem').css('border', '0');
				}
			});
			
			//사업자명-(주)괄호 들어가도 됨, 영어허용 유효성 검사@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			var userPName = $(".current .pnameelem");
			var regExpPName = /^[가-힣,a-z,A-Z()]{2,11}$/;
			userPName.keyup(function() {
				$('.current .pnamenoti').css('display', 'none');
				if (!regExpPName.test(userPName.val())) {
					$('.current .pname_noti_1').css('color', '#ff5442');
					$('.current .pname_noti_1').css('display', 'block');
					$('.current .pnameelem').css('border', '1px solid #ff5442');
				}else{
					$('.current .pnamenoti').css('display', 'none');
					$('.current .pnameelem').css('border', '0');
				}
			});
			$(".current .pnameelem").on("blur", function() {
				$('.current .pnamenoti').css('display', 'none');
				if (userPName.val() =="") {
					$('.current .pname_noti_0').css('color', '#ff5442');
					$('.current .pname_noti_0').css('display', 'block');
					$('.current .pnameelem').css('border', '1px solid #ff5442');
				}else if(!regExpPName.test(userPName.val())){
					$('.current .pnamenoti').css('display', 'none');
					$('.current .pname_noti_1').css('color', '#ff5442');
					$('.current .pname_noti_1').css('display', 'block');
					$('.current .pnameelem').css('border', '1px solid #ff5442');
				}else{
					$('.current .pnamenoti').css('display', 'none');
					$('.current .pnameelem').css('border', '0');
				}
			});
			
			///카드번호@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			var userCard = $(".current .cardelem");
			var dreamCardno = $(".current .cardelem");
			//자동 - 생성
			var autoHypenCard = function(str){
			      str = str.replace(/[^0-9]/g, '');
			      var tmp = '';
			      if( str.length < 5){
			          return str;
			      }else if(str.length < 9){
			          tmp += str.substr(0, 4);
			          tmp += '-';
			          tmp += str.substr(4);
			          return tmp;
			      }else if(str.length < 12){
			          tmp += str.substr(0, 4);
			          tmp += '-';
			          tmp += str.substr(4, 4);
			          tmp += '-';
			          tmp += str.substr(8);
			          return tmp;
			      }else{              
			          tmp += str.substr(0, 4);
			          tmp += '-';
			          tmp += str.substr(4, 4);
			          tmp += '-';
			          tmp += str.substr(8, 4);
			          tmp += '-';
			          tmp += str.substr(12, 4);
			          return tmp;
			      }
			}
			var cardNum = document.getElementById('cardelem');
			cardNum.onkeyup = function(){
			  console.log(this.value);
			  this.value = autoHypenCard( this.value ) ;  
			}
			
			// 카드데이터와 일치여부 - 유효하지 않은카드번호입니다.
			$('.current .cardelem').keyup(function() {
				$('.current .cardnoti').css('display', 'none');
				$.ajax({
					url : "dupCard.dz",
					type : "get",
					data : { "userName" : userName.val(), "dreamCardno" : dreamCardno.val() },
					success : function(result) {
						if(result == 0){
							$('.current .cardnoti').css('display', 'none');
							$('.current .card_noti_1').css('color', '#ff5442');
							$('.current .card_noti_1').css('display', 'block');
							$('.current .cardelem').css('border', '1px solid #ff5442');
						}else if(result == 1){
							$('.current .cardnoti').css('display', 'none');
							$('.current .card_noti_2').css('color', '#ff5442');
							$('.current .card_noti_2').css('display', 'block');
							$('.current .cardelem').css('border', '1px solid #ff5442');
						}else if(result == 2){
							$('.current .cardnoti').css('display', 'none');
							$('.current .card_suc').css('display', 'block');
							$('.current .card_suc').css('color', '#0160ff');
							$('.current .cardelem').css('border', '0');
						}
					},
					error : function() {
						console.log("전송실패");
					}
				});
			});
			// 안적었을때 - 카드번호를입력해주세요
			$(".current .cardelem").on("blur", function() {
				//$('.current .cardnoti').css('display', 'none');
				if (userCard.val() =="") {
					$('.current .card_noti_0').css('color', '#ff5442');
					$('.current .card_noti_0').css('display', 'block');
					$('.current .cardelem').css('border', '1px solid #ff5442');
				}else{
					/* $('.current .cardnoti').css('display', 'none');
					$('.current .cardelem').css('border', '0'); */
				}
			});
			
			//닉네임 유효성 검사@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			var userNick = $(".current .nickelem");
			var regExpNick = /^[가-힣,a-z,A-Z]{2,11}$/;
			userNick.keyup(function() {
				$('.current .nicknoti').css('display', 'none');
				$.ajax({
					url : "dupNick.dz",
					type : "get",
					data : { "userNick" : userNick.val()},
					success : function(result) {
						if(result != 0){
							$('.current .nick_noti_2').css('color', '#ff5442');
							$('.current .nick_noti_2').css('display', 'block');
							$('.current .nickelem').css('border', '1px solid #ff5442');
						}else if(!regExpNick.test(userNick.val())){
							$('.current .nick_noti_1').css('color', '#ff5442');
							$('.current .nick_noti_1').css('display', 'block');
							$('.current .nickelem').css('border', '1px solid #ff5442');
						}else{
							$('.current .nicknoti').css('display', 'none');
							$('.current .nickelem').css('border', '0');
						}
					},
					error : function() {
						console.log("전송실패");
					}
				});
				
			});
			$(".current .nickelem").on("blur", function() {
				if (userNick.val() =="") {
					$('.current .nicknoti').css('display', 'none');
					$('.current .nick_noti_0').css('color', '#ff5442');
					$('.current .nick_noti_0').css('display', 'block');
					$('.current .nickelem').css('border', '1px solid #ff5442');
				}
			});
			
			
			
			////////////////mz휴대폰번호끝
			
			// 중복(2) - 이미 등록된 번호입니다
			var userPhone = $(".current .phoneelem");
			userPhone.keyup(function() {
				$('.current .phonenoti').css('display', 'none');
				$.ajax({
					url : "dupPhone.dz",
					data : { "userPhone" : userPhone.val() },
					success : function(result) {
						if(result != 0){
							//$('.current .phonenoti').css('display', 'none');
							$('.current .phone_noti_2').css('color', '#ff5442'); // 에러메시지:이미 등록된 휴대폰번호
							$('.current .phone_noti_2').css('display', 'block');
							$('.current .phoneelem').css('border', '1px solid #ff5442');
						}else {
							$('.current .phonenoti').css('display', 'none');
							$('.current .phoneelem').css('border', '0');
						}
					},
					error : function() {
						console.log("전송실패");
					}
				});
			});
			// 안적었을때(0) - 휴대폰번호를입력해주세요
			$(".current .phoneelem").on("blur", function() {
				if (userPhone.val() =="") {
					$('.current .phonenoti').css('display', 'none');
					$('.current .phone_noti_0').css('color', '#ff5442');
					$('.current .phone_noti_0').css('display', 'block');
					$('.current .phoneelem').css('border', '1px solid #ff5442');
				}
			});
			
			///사업자 연락처 -위치 수정@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			//자동 - 생성
			var autoHypenPPhone = function(str){
			      str = str.replace(/[^0-9]/g, '');
			      var tmp = '';
			      if( str.length < 3){
			          return str;
			      }else if(str.length < 6){
			          tmp += str.substr(0, 2);
			          tmp += '-';
			          tmp += str.substr(2);
			          return tmp;
			      }else if(str.length < 10){
			          tmp += str.substr(0, 2);
			          tmp += '-';
			          tmp += str.substr(2, 3);
			          tmp += '-';
			          tmp += str.substr(5);
			          return tmp;
			      }else{              
			          tmp += str.substr(0, 2);
			          tmp += '-';
			          tmp += str.substr(2, 4);
			          tmp += '-';
			          tmp += str.substr(6);
			          return tmp;
			      }
			  
			      return str;
			}
	
			var pphoneNum = document.getElementById('pphoneelem');
	
			pphoneNum.onkeyup = function(){
			  console.log(this.value);
			  this.value = autoHypenPPhone( this.value ) ;  
			}
			
			/// 사업자번호@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			userPVeri = $(".current .pverielem");
			//자동 - 생성
			var autoHypenPVeri = function(str){
			      str = str.replace(/[^0-9]/g, '');
			      var tmp = '';
			      if( str.length < 4){
			          return str;
			      }else if(str.length < 7){
			          tmp += str.substr(0, 3);
			          tmp += '-';
			          tmp += str.substr(3);
			          return tmp;
			      }else{              
			          tmp += str.substr(0, 3);
			          tmp += '-';
			          tmp += str.substr(3, 2);
			          tmp += '-';
			          tmp += str.substr(5);
			          return tmp;
			      }
			}
			var pveriNum = document.getElementById('pverielem');
			pveriNum.onkeyup = function(){
			  console.log(this.value);
			  this.value = autoHypenPVeri( this.value ) ;  
			}
			
			// 회원데이터와 일치여부 - 이미 등록된 번호입니다
			$('.pverielem').keyup(function() {
				$('.pverinoti').css('display', 'none');
				$.ajax({
					url : "dupPveri.dz",
					data : { "partnerVerify" : userPVeri.val() },
					success : function(result) {
						if(result != 0){
							$('.pveri_noti_2').css('color', '#ff5442');
							$('.pveri_noti_2').css('display', 'block');
							$('.pverielem').css('border', '1px solid #ff5442');
						}else{
							$('.pverinoti').css('display', 'none');
							$('.pverielem').css('border', '0');
						}
					},
					error : function() {
						console.log("전송실패");
					}
				});
			});
			// 안적었을때 - 사업자번호를입력해주세요
			$(".pverielem").on("blur", function() {
				/* $('.pverinoti').css('display', 'none'); */
				if (userPVeri.val() =="") {
					$('.pveri_noti_0').css('color', '#ff5442');
					$('.pveri_noti_0').css('display', 'block');
					$('.pverielem').css('border', '1px solid #ff5442');
				}else{
					/* $('.pverinoti').css('display', 'none');
					$('.pverielem').css('border', '0'); */
				}
			});
			
			// 이메일 유효성검사@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			userEmail = $(".current .emailelem");
			var emailReg = /[a-zA-Z0-9]{4,12}\@/;
			$(".current .emailelem").keyup(function() {
				$('.current .emailnoti').css('display', 'none');
				$.ajax({
					url : "dupEmail.dz",
					data : { "userEmail" : userEmail.val() },
					success : function(result) {
						if(result != 0){
							$('.current .email_noti_2').css('color', '#ff5442');
							$('.current .email_noti_2').css('display', 'block');
							$('.current .emailelem').css('border', '1px solid #ff5442');
						}else if(!emailReg.test(userEmail.val())) {
							$('.current .emailnoti').css('display', 'none');
							$('.current .email_noti_1').css('color', '#ff5442');
							$('.current .email_noti_1').css('display', 'block');
							$('.current .emailelem').css('border', '1px solid #ff5442');
						}else {
							$('.current .emailnoti').css('display', 'none');
							$('.current .emailelem').css('border', '0');
						}
					},
					error : function() {
						console.log("전송실패");
					}
				});
			});
			// 안적었을때
			$(".current .emailelem").on("blur", function() {
				if (userEmail.val() =="") {
					$('.current .emailnoti').css('display', 'none');
					$('.current .email_noti_0').css('color', '#ff5442');
					$('.current .email_noti_0').css('display', 'block');
					$('.current .emailelem').css('border', '1px solid #ff5442');
				}
			});
			
			///////////////제출버튼@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			$('.current .submit-btn').click(function() {
				var rtn = true;
				
				//아이디
				$.ajax({
					url : "dupId.dz",
					data : { "userId" : userId.val() },
					async: false,
					success : function(result) {
						if(result != 0){
							alert("사용중인 아이디입니다. 아이디를 다시 입력해주세요.");
							userId.focus();
							rtn = false;
						}else if(userId.val() =="") {
							alert("아이디를 입력해주세요.");
							userId.focus();
							rtn = false;
						}else if (!regExp.test(userId.val())) {
							alert("아이디가 올바르지 않습니다. 다시 입력해주세요.");
							userId.focus();
							rtn = false;
						}else {
							rtn = true;
						}
					},
					error : function() {
						console.log("전송실패");
					}
				});

				//비밀번호
				if (userPwd.val() =="") {
					alert("비밀번호를 입력해주세요.");
					userPwd.focus();
					rtn = false;
				}else if (!regExpPw.test(userPwd.val())) {
					alert("비밀번호가 올바르지 않습니다. 다시 입력해주세요.");
					userPwd.focus();
					rtn = false;
				}else if($(".current .re_pwd").val()!=userPwd.val()) {
					alert("비밀번호가 올바르지 않습니다. 다시 입력해주세요.");
					userPwd.focus();
					rtn = false;
				}
				
				//이름
				else if (userName.val() =="") {
					alert("이름을 입력해주세요.");
					userName.focus();
					rtn = false;
				}else if(userName.length != 0 && !regExpName.test(userName.val())){
					alert("이름이 올바르지 않습니다. 다시 입력해주세요.");
					userName.focus();
					rtn = false;
				} 
				
				//사업자명
				else if (userPName.val() =="") {
					alert("사업자명을 입력해주세요.");
					userPName.focus();
					rtn = false;
				}else if(userPName.length != 0 && !regExpPName.test(userPName.val())){
					alert("사업자명이 올바르지 않습니다. 다시 입력해주세요.");
					userPName.focus();
					rtn = false;
				}
				
				//간이사업자명//////////////////////
				else if (userSimpleName.val() =="") {
					alert("간이사업자명을 입력해주세요.");
					userSimpleName.focus();
					rtn = false;
				}else if(userSimpleName.length != 0 && !regExpSimpleName.test(userSimpleName.val())){
					alert("간이사업자명이 올바르지 않습니다. 다시 입력해주세요.");
					userSimpleName.focus();
					rtn = false;
				}
				
				///카드번호
				$.ajax({
					url : "dupCard.dz",
					type : "get",
					async: false,
					data : { "userName" : userName.val(), "dreamCardno" : dreamCardno.val() },
					success : function(result) {
						if (userCard.val() =="") {
							alert("카드번호를 입력해주세요.");
							userCard.focus();
							rtn = false;
						}else if(result == 0){
							alert("유효하지 않은 카드번호입니다. 카드번호를 다시 입력해주세요.");
							userCard.focus();
							rtn = false;
						}else if(result == 1){
							alert("이미 존재하는 카드번호입니다. 카드번호를 다시 입력해주세요.");
							userCard.focus();
							rtn = false;
						}
					},
					error : function() {
						console.log("전송실패");
					}
				});
				
				//닉네임
				$.ajax({
					url : "dupNick.dz",
					type : "get",
					data : { "userNick" : userNick.val()},
					success : function(result) {
						if (userNick.val() =="") {
							alert("닉네임을 입력해주세요.");
							userNick.focus();
							rtn = false;
						}else if(result != 0){
							alert("이미 사용중인 닉네임입니다. 다시 입력해주세요.");
							userNick.focus();
							rtn = false;
						}else if(!regExpNick.test(userNick.val())){
							alert("닉네임이 올바르지 않습니다. 다시 입력해주세요.");
							userNick.focus();
							rtn = false;
						}
					},
					error : function() {
						console.log("전송실패");
					}
				});
				
				
				///휴대폰번호
				$.ajax({
					url : "dupPhone.dz",
					data : { "userPhone" : userPhone.val() },
					async: false,
					success : function(result) {
						if (userPhone.val() =="") {
							alert("휴대폰번호를 입력해주세요.");
							userPhone.focus();
							rtn = false;
						}else if(result != 0){
							alert("이미 등록된 휴대폰번호입니다. 다시 입력해주세요.");
							userPhone.focus();
							rtn = false;
						}
					},
					error : function() {
						console.log("전송실패");
					}
				});
				// 사업자 연락처
				
				/// 사업자번호
				$.ajax({
					url : "dupPveri.dz",
					data : { "partnerVerify" : userPVeri.val() },
					async: false,
					success : function(result) {
						if (userPVeri.val() =="") {
							alert("사업자번호를 입력해주세요.");
							userPVeri.focus();
							rtn = false;
						}else if(result != 0){
							alert("이미 등록된 사업자번호입니다. 다시 입력해주세요.");
							userPVeri.focus();
							rtn = false;
						}
					},
					error : function() {
						console.log("전송실패");
					}
				});
				
				// 이메일
				$.ajax({
					url : "dupEmail.dz",
					data : { "userEmail" : userEmail.val() },
					async: false,
					success : function(result) {
						if (userEmail.val() =="") {
							alert("이메일을 입력해주세요.");
							userEmail.focus();
							rtn = false;
						}else if(result != 0){
							alert("이미 등록된 이메일입니다. 다시 입력해주세요.");
							userEmail.focus();
							rtn = false;
						}else if(!emailReg.test(userEmail.val())) {
							alert("이메일이 올바르지 않습니다. 다시 입력해주세요.");
							userEmail.focus();
							rtn = false;
						}
					},
					error : function() {
						console.log("전송실패");
					}
				});
				
				return rtn;
			});
			
		});
	});
	
	// 주소검색 api
	function openZipSearch() {
		new daum.Postcode({
			oncomplete: function(data) {
				$('[name=zip]').val(data.zonecode); // 우편번호 (5자리)
				$('[name=addr1]').val(data.address);
				$('[name=addr2]').val(data.buildingName);
			}
		}).open();
	}
</script>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>
</html>