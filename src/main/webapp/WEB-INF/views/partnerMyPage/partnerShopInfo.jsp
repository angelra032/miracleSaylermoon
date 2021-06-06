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
								<input name="uploadFile" class="form-elem uploadFile" multiple="multiple" type="file" maxlength="20" placeholder="영문, 숫자 또는 혼합 6~20자">
							</div>
							
							<h1>유효성검사 필요, 메뉴사진(다중업로드) 추가, MAIN_MENU 테이블 입력필요</h1>
							
							<input type="hidden" name="userNo" value="${userNo}" />
							<!-- <button class="submit-btn" type="submit">등록하기</button> -->
							<input type="submit" class="submit-btn" value="등록하기" />
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
													<!-- 이건 뭐야 -->
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
	
	
	
	////////// 등록 버튼 //////////
	$("#submit-btn").click(function (){
		console.log("등록버튼 클릭");
		alert("하 ...");
		return false;
	});
	
	

</script>	
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>
</html>