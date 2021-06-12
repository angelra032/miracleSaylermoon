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
						<form id="frm" action="shopRegister.dz" method="post"  enctype="multipart/form-data">
							<div class="form-head form-head2">
								가게이름&nbsp;
								<span class="required">*</span>&nbsp;&nbsp;
							</div>
							<div class="form-body">
								<input name="shopName" class="form-elem shopName" type="text" maxlength="40" placeholder="간이사업자명 입력" value="${ partnerName }" readonly>
							</div>
							
							<div class="form-head form-head2">
								메인 이미지 업로드&nbsp;
								<span class="required">*</span>&nbsp;&nbsp;
								<div class="form-noti emailnoti email_noti_0">이메일을 입력해 주세요.</div>
								<div class="form-noti emailnoti email_noti_1">이메일 형식이 올바르지 않습니다.</div>
								<div class="form-noti emailnoti email_noti_2">이미 사용중인 이메일입니다.</div>
							</div>
							<div class="form-body">
								<input name="shopPhoto" class="form-elem uploadFile" type="file" maxlength="20" placeholder="영문, 숫자 또는 혼합 6~20자">
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
								<input name="shopShortAddr" class="form-elem shopShortAddr" type="text" maxlength="20" placeholder="한눈에 확인할 수 있는 주소, 한글 2자 이상 입력">
							</div>
							
							<div class="form-head form-head2">
								가게 타겟층&nbsp;
								<span class="required">*</span>&nbsp;&nbsp;
								<div class="form-noti nicknoti nick_noti_0">닉네임을 입력해 주세요.</div>
								<div class="form-noti nicknoti nick_noti_1">한글 2자 이상 입력</div>
								<div class="form-noti nicknoti nick_noti_2">이미 사용중인 닉네임입니다.</div>
							</div>
							<div class="form-body">
								<input name="shopTarget" class="form-elem shopTarget" type="text" maxlength="20" placeholder="타켓 입력">
							</div>
							
							<div class="form-head form-head2">
								제공메뉴&nbsp;
								<span class="required">*</span>&nbsp;&nbsp;
								<div class="form-noti phonenoti phone_noti_0">휴대폰번호를 입력해 주세요.</div>
								<div class="form-noti phonenoti phone_noti_2">이미 등록된 번호입니다.</div>
							</div>
							<div class="form-body">
								<input name="shopProduct" id="shopProduct" class="form-elem shopProduct" type="text" maxlength="40" placeholder="무상제공하는 메뉴 입력">
							</div>

							<div class="form-head form-head2">
								연락처&nbsp;
								<span class="required">*</span>&nbsp;&nbsp;
								<div class="form-noti phonenoti phone_noti_0">휴대폰번호를 입력해 주세요.</div>
								<div class="form-noti phonenoti phone_noti_2">이미 등록된 번호입니다.</div>
							</div>
							<div class="form-body">
								<input name="shopPhone" id="shopPhone" class="form-elem shopPhone" type="text" maxlength="13" value="${ userPhone }" placeholder="숫자만 입력">
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
									<option value="0" selected disabled>선택</option>
									<option value="1">한식</option>
									<option value="2">분식</option>
									<option value="3">일식</option>
									<option value="4">중식</option>
									<option value="5">양식</option>
									<option value="6">기타</option>
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
										<option value="0" selected disabled>선택</option>
										<option value="Y">가능</option>
										<option value="N">불가능</option>
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
								<input name="shopMaxReserv" class="form-elem shopMaxReserv" type="text" maxlength="20" placeholder="최대 예약가능한">
							</div>
							
							<div class="form-head form-head2">
								영업시간&nbsp;
								<span class="required">*</span>&nbsp;&nbsp;
								<div class="form-noti emailnoti email_noti_0">시간을 선택해 주세요.</div>
								<div class="form-noti emailnoti email_noti_1">이메일 형식이 올바르지 않습니다.</div>
								<div class="form-noti emailnoti email_noti_2">이미 사용중인 이메일입니다.</div>
							</div>
							<div class="form-body form-select-body-2">
								<select name="startTime" id="startTime" class="form-select-2">
									<option value="0" selected disabled>여는 시간</option>
									<c:forEach begin="1" end="24" varStatus="status">
										<option value="${ status.count }">${ status.count }시</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-body form-select-body-2 form-select-body-2-1">
								<select name="endTime" id="endTime" class="form-select-2">
									<option value="0" selected disabled>닫는 시간</option>
									<c:forEach begin="1" end="24" varStatus="status">
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
								메뉴 입력&nbsp;
								<span class="required">*</span>&nbsp;&nbsp;
								<div class="form-noti emailnoti email_noti_0">이메일을 입력해 주세요.</div>
								<div class="form-noti emailnoti email_noti_1">이메일 형식이 올바르지 않습니다.</div>
								<div class="form-noti emailnoti email_noti_2">이미 사용중인 이메일입니다.</div>
							</div>
							<div class="form-body add-menu-div">
								<div class="first-main-menu">
									<input type="text" name="mainMenuName" class="form-elem menu-name" placeholder="메뉴 이름 입력" />
									<input type="text" name="mainMenuPrice" class="form-elem menu-price" placeholder="메뉴 가격" />
									<a href="#this" id="delMenuBtn" onclick="delMenu(this);">삭제</a>
								</div>
							</div>
								<button type="button" onclick="addMenu();" class="add-menu-btn" >메뉴추가</button>
							
							<div class="form-head form-head2 img-upload">
								메뉴 이미지 업로드&nbsp;
								<span class="required">*</span>&nbsp;&nbsp;
								<div class="form-noti emailnoti email_noti_0">이메일을 입력해 주세요.</div>
								<div class="form-noti emailnoti email_noti_1">이메일 형식이 올바르지 않습니다.</div>
								<div class="form-noti emailnoti email_noti_2">이미 사용중인 이메일입니다.</div>
							</div>
							<div class="form-body">
								<c:forEach varStatus="i" begin="0" end="3">
									<div class="first-menu-photo">
										<input name="mainMenuPhoto" style="display:none;" class="form-elem" type="file" id="file${i.index }" value="${photo.menuFileName }" >
										<label for="file${i.index }" class="uploadFile">파일선택</label>
										<span class="file-name">선택된 파일 없음</span><!-- 선택된 파일 없음 -->
										<a href="#this" id="delPhotoBtn" onclick="delMenu(this);">X</a>
									</div>
								</c:forEach>
							</div>
							
							<h1>유효성검사 수정필요</h1>
							
							<input type="hidden" name="userNo" value="${userNo}" />
							<button type="button" id="btn" class="submit-btn" onclick="waitJoin()">등록하기</button>
							<!-- <button type="submit" id="btn" class="submit-btn" >등록하기</button> -->
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
		
		//////////////////////////////////////////////////////////////////////////
		
	});
	
	

	// 파일명 바꾸기
	$(document).on('change', 'input[type=file]', function(e){
		//$(this).parent().find(".file_name").text(e.target.files[0].name);
		var fileName = $(e.target)[0].files[0].name;
		$(e.target).siblings('.file-name').html(fileName);
		console.log($(".file-name").val());
		console.log(fileName);
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
	
	
	var count = -1;
	// 메뉴 추가 append 그리기
	function addMenu(){
		count++;
		
		var menuDiv = $(".add-menu-div");
		var firstDiv = $(".first-main-menu");
		var addMenu = $("<div class='first-main-menu'>");
		
// 		for()
		
// 		firstDiv.html();
		addMenu.append("<input type='text' name='mainMenuName' class='form-elem menu-name' placeholder='메뉴 이름 입력' />")
				.append("<input type='text' name='mainMenuPrice' class='form-elem menu-price' placeholder='메뉴 가격' />")
				.append("<a href='#this' id='delMenuBtn' onclick='delMenu(this);'>삭제</a>");
		menuDiv.append(addMenu);
		console.log(count);
		console.log($(".menu-name").val());
		console.log($(".menu-price").val());
	}
	///////// 메뉴/사진 삭제 
	function delMenu(e){
		//first-main-menu
		//$()
		$(e).parent().remove();
	}
	
	
	
	function waitJoin() {
		var rtn = true;

		// 메인 이미지 업로드
		var shopPhoto = $("input[name='shopPhoto']");
		if(shopPhoto.val()==""){
			alert("메인 이미지를 업로드하세요.");
			shopPhoto.focus();
			return false;
		}
		
		// 가게 위치
		var zip = $("#zip"); // 우편번호	숫자야? string이야?
		var addr1 = $("#addr1"); // 기본주소
		var addr2 = $("#addr2"); // 상세주소
		if( zip.val()=="" || addr1.val()=="" || addr2.val()=="" ){
			alert("가게 위치를 입력하세요.");
			zip.focus();
			return false;
		}
		
		// 가게 간단 위치
		var shopShortAddr = $("input[name='shopShortAddr']");
		if(shopShortAddr.val()==""){
			alert("가게 간단위치를 입력하세요.");
			shopShortAddr.focus();
			return false;
		}
		
		// 가게 타겟층
		var shopTarget = $("input[name='shopTarget']");
		if(shopTarget.val()==""){
			alert("가게 타겟층을 입력하세요.");
			shopTarget.focus();
			return false;
		}

		// 제공품목
		var shopProduct = $("#shopProduct");
		if(shopProduct.val()==""){
			alert("제공품목을 입력하세요.");
			shopProduct.focus();
			return false;
		}
		
		// 연락처
		var shopPhone = $("#shopPhone");
		if(shopPhone.val()==""){
			alert("연락처를 입력하세요.");
			shopPhone.focus();
			return false;
		}

		// 가게 분류
		var shopTypeNum = $("#shopTypeNum");
			console.log(shopTypeNum.val());
		if(shopTypeNum.val()==null){
			alert("가게 분류를 선택하세요.");
			shopTypeNum.focus();
			return false;
		}
		
		// 주차유무
		var shopParking = $("#shopParking");
		if(shopParking.val()==null){
			alert("주차유무를 선택하세요.");
			shopParking.focus();
			return false;
		}

		// 최대예약가능인원
		var shopMaxReserv = $("input[name='shopMaxReserv']");
		console.log("최대예약가능인원",shopMaxReserv.val());
		if(shopMaxReserv.val()==""){
			alert("최대 예약 가능한 인원을 입력하세요.");
			shopMaxReserv.focus();
			return false;
		}
		
		// 시작 시간
		var startTime = $("#startTime");
		console.log("시간",startTime.val());
		if(startTime.val()==null){
			alert("여는 시간을 선택하세요.");
			startTime.focus();
			return false;
		}
		
		// 끝내는 시간
		var endTime = $("#endTime");
		console.log("끝내는시간",endTime.val());
		if(endTime.val()==null){
			alert("닫는 시간을 선택하세요.");
			endTime.focus();
			return false;
		}

		// 영업일	// 체크박스 비어있을 때 ///////////////////////// 
		var businessNum = $("input[name='businessNum']");
		console.log("영업일",businessNum.val());
		if(businessNum.is(":checked")==false){
			alert("영업일을 체크하세요.");
			businessNum.focus();
			return false;
		}
		
		/* 
		// 가게 소개
		var shopContent = $("input[name='shopContent']");
		if(shopContent.val()==""){		// val 아니면 html 혹은 text
			alert("가게 소개를 입력하세요.");
			shopContent.focus();
			return false;
		}
		*/
		
		// 메뉴 입력
		var mainMenuName = $("input[name='mainMenuName']");
		var mainMenuPrice = $("input[name='mainMenuPrice']");
		if( mainMenuName.val()=="" || mainMenuPrice.val()=="" ){		// 파일 - val 아니면 html 혹은 text
			alert("메뉴와 가격을 입력하세요.");
			mainMenuName.focus();
			return false;
		}
		
		// 메뉴 사진 업로드
		var mainMenuPhoto = $("input[name='mainMenuPhoto']");
		console.log("업로드",mainMenuPhoto.val());
		if(mainMenuPhoto.val()==""){		// 파일 - val 아니면 html 혹은 text
			alert("메뉴 이미지를 업로드하세요.");
			mainMenuPhoto.focus();
			return false;
		}
		
		
		
		var result = confirm('가게 등록 후 관리자의 확인 절차가 진행됩니다. 확인 절차동안 페이지에 노출되지 않습니다.');
		if(result) {
			//document.getElementById('btn').onclick = function() {
				document.getElementById('frm').submit();
				return false;
			//}
		} else {
			
		}
		
	}
	 

	
	
</script>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>
</html>